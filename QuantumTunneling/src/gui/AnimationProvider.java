package gui;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class AnimationProvider extends SwingWorker<JFreeChart,Void>{
	OptionPanel optionPanel;
	ChartPanel chartPanel;
	WaveFunction f;
	JLabel label;
	Plotter plotter=new Plotter();
	boolean isRunning, isReady;
	double x0;
	
	
	public AnimationProvider(
			OptionPanel optionPanel, 
			ChartPanel chartPanel,WaveFunction f, 
			JLabel label, double x0, 
			boolean isRunning,
			boolean isReady){
		this.optionPanel=optionPanel;
		this.chartPanel=chartPanel;
		this.f=f;
		this.label=label;
		this.x0=x0;
		this.isRunning=isRunning;
		this.isReady=isReady;
		
	}
	@Override
	protected JFreeChart doInBackground() throws Exception {
		doTaskInBackground();
		
		return plotter.plot(f.seriesWave(), f.seriesPotential(), x0, "time: "+Double.toString(f.getTime()),f.getLength(), "X", "Y");
	}
	private void doTaskInBackground(){
		if(isRunning)
		f.evolution();
		}
		
	
	 @Override
     protected void done() {
		 try {
			JFreeChart newchart = get();
			chartPanel=new ChartPanel(newchart);
			chartPanel.repaint();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	 }
	
	
	
	public void StopAnimation(){
		this.isRunning=false;
	}
	public void StartAnimation(){
		this.isRunning=true;
	}
	public void ready(){
		isReady=true;
	}
	public void notReady(){
		isReady=false;
	}
	
	

}
