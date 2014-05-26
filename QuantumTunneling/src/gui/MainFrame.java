package gui;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import net.miginfocom.swing.MigLayout;

import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class MainFrame extends JFrame{
	/*
	 * @author Micha³ Murawski
	 * @param
	 * @return
	 * This class provides applying all classes in JFrame.
	 */
	
	
	Locale currentLocale=new Locale("en","GB");
	ResourceBundle text;
	private static final long serialVersionUID = 1L;
	protected static final int EXIT_ON_CLOSE = 0;
	public ChartPanel chartContainer;
	final OptionPanel options;
	public WaveFunction f;
	boolean isRunning,isReady,isBarrier,isOscillator;
	JPanel panelContainer;
	JFreeChart chart;
	Plotter plotter= new Plotter();
	AnimationProvider provider;
	Timer timer;
	SwingWorker<JFreeChart,Void> worker;
	private Executor executor=Executors.newCachedThreadPool();
	
	
	public MainFrame(){
		
		
		f=new WaveFunction(new double[]{10,3,200,10,100});
		f.setPotential(new double[]{0,0,0});
		chart=plotter.plot(f.seriesWave(),
				f.seriesPotential(), 
				0, 
				"time: 0.0 fs", 
				100,
				-0.3,
				1,
				"X", 
				"Y");
		isRunning=false;
		isBarrier=true;
		isReady=true;
		
		
		getContentPane().setLayout(new MigLayout("", "[360.00][grow]", "[435.00][41.00][][][]"));
		
		options = new OptionPanel();
		getContentPane().add(options, "cell 0 0,grow");
		
		chartContainer = new ChartPanel(chart);
		chartContainer.setDisplayToolTips(false);
		getContentPane().add(chartContainer, "cell 1 0,grow");
		
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				if(isReady){
					isRunning=true;
				}else{
					new JOptionPane().showMessageDialog(null, "Set parameters");
				}
				
			}
		});
		getContentPane().add(btnPlay, "flowx,cell 0 1,alignx center");
		
		final JSlider slider = new JSlider();
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(1);
		slider.setValue(0);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		getContentPane().add(slider, "cell 1 1,growx");
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isRunning=false;
			}
		});
		getContentPane().add(btnPause, "cell 0 1,alignx center");
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isRunning=false;
				isReady=false;
			}
		});
		getContentPane().add(btnStop, "cell 0 1,alignx center");
		
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if(!isReady&&options.ready()){
				f=new WaveFunction(options.getVariablesWave());
				if(isBarrier){
				f.setPotential(options.getVariablesPotential());
				}else{
					f.setPotential2(options.getVariablesPotential());
				}
				slider.setMaximum((int)f.getLength());
				isReady=true;
				}else if(!options.ready()){
					new JOptionPane().showMessageDialog(null, "Set parameters propely");
				}
			}
		});
		getContentPane().add(btnSet, "cell 0 1");
		final JRadioButton rdbtnBarrieraPotential = new JRadioButton("Barriera potential");
		final JRadioButton rdbtnHarmonicOscillatorPotential = new JRadioButton("Harmonic oscillator potential");
		rdbtnHarmonicOscillatorPotential.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isOscillator=true;
				isBarrier=false;
				rdbtnBarrieraPotential.setSelected(false);
			}
		});
		getContentPane().add(rdbtnHarmonicOscillatorPotential, "cell 0 2,growx");
		
		
		rdbtnBarrieraPotential.setSelected(true);
		rdbtnBarrieraPotential.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isOscillator=false;
				isBarrier=true;
				rdbtnHarmonicOscillatorPotential.setSelected(false);
			}
		});
		getContentPane().add(rdbtnBarrieraPotential, "flowx,cell 0 2,growx");
		
		final JLabel lblProbability = new JLabel("0.0");
		getContentPane().add(lblProbability, "cell 1 2,alignx center");
		
		
		
		timer = new Timer(25, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	worker = new SwingWorker<JFreeChart,Void>(){

					@Override
					protected JFreeChart doInBackground() throws Exception {
						if(isRunning&&isReady){
							f.evolution();
							}
						getContentPane().revalidate();
						getContentPane().repaint();
						
						
						
						
						
						
						
		
						return null;
					}
					protected void done(){
						getContentPane().remove(chartContainer);
						chartContainer = new ChartPanel(plotter.plot(f.seriesWave(), 
								f.seriesPotential(),slider.getValue(), 
								"time: "+new DecimalFormat("00000.00").format(f.getTime()), 
								f.getLength(),
								-0.3,
								1,
								"X", 
								"Y"));
						
						chartContainer.setDisplayToolTips(false);
						getContentPane().add(chartContainer, "cell 1 0,grow");
						
						
						lblProbability.setText(new DecimalFormat("0.00").format(f.integration(slider.getValue())));
						
						}
		    		
		    	}; executor.execute(worker);
		    	
		    	
		    	
		    }
		    
		});
		
		
		
		timer.start();
		
		
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args){
		 SwingUtilities.invokeLater(new Runnable() {
	          @Override
	          public void run() {
	        	  
	              MainFrame program= new MainFrame();
	              program.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	              program.setSize(1200, 600);
	              program.setVisible(true);
	          }
	      });
		
		
		
		
	}
	
	
	
	
	

}
