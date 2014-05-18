package gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class AnimationProviderTest extends SwingWorker<Integer, Integer>{
	
	WaveFunction f;
	Plotter plotter;
	JFreeChart chart;
	ChartPanel panel;
	JFrame frame;
	
	public AnimationProviderTest(WaveFunction f, JFrame frame){
		this.f=f;
		this.frame=frame;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		f.evolution();
		chart=plotter.plot(this.f.seriesWave(),this.f.seriesPotential(),"kupa");
		panel=new ChartPanel(chart);
		Thread.sleep(1000);
		return null;
		
	}
	protected void done(){
		frame.getContentPane().add(panel, "cell 1 0,grow");
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		
	}
	
	
	

}
