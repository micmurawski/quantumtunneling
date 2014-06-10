package gui;

import java.text.DecimalFormat;
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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


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
	OptionPanel options;
	public WaveFunction f;
	boolean isRunning,isReady,isBarrier,isOscillator;
	JPanel panelContainer;
	JFreeChart chart;
	Plotter plotter= new Plotter();
	Timer timer;
	SwingWorker<JFreeChart,Void> worker;
	JButton btnSet;
	JRadioButton rdbtnHarmonicOscillatorPotential, rdbtnBarrieraPotential;
	JMenuItem mntmSquareBarriera, mntmOscillator;
	private Executor executor=Executors.newCachedThreadPool();
	
	
	public MainFrame(){
		
		getContentPane().setLayout(new MigLayout("", "[360.00][grow]", "[435.00][41.00][43.00][][][]"));
		
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
		
		btnSet = new JButton("Reset");
		btnSet.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				isRunning=false;
				isReady=false;
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
		rdbtnBarrieraPotential = new JRadioButton("Barriera potential");
		rdbtnHarmonicOscillatorPotential = new JRadioButton("Harmonic oscillator potential");
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDefaultSettings = new JMenu("Default settings");
		menuBar.add(mnDefaultSettings);
		
		mntmSquareBarriera = new JMenuItem("Square barriera");
		mntmSquareBarriera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnBarrieraPotential.doClick();
				options.setVariables(new int[]{3,25,1},new int[]{100,37,100,10,50});
				btnSet.doClick();
			}
		});
		mnDefaultSettings.add(mntmSquareBarriera);
		
		mntmOscillator = new JMenuItem("Oscillator");
		mntmOscillator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnHarmonicOscillatorPotential.doClick();
				options.setVariables(new int[]{0,15,1},new int[]{100,10,10,15,30});
				btnSet.doClick();
			}
		});
		mnDefaultSettings.add(mntmOscillator);
		
		
		
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
								"time: "+new DecimalFormat("00000.00").format(f.getTime())+" fs", 
								f.getLength(),
								-0.3,
								1,
								"X", 
								"Y"));
						
						chartContainer.setDisplayToolTips(false);
						getContentPane().add(chartContainer, "cell 1 0,grow");
						
						
						lblProbability.setText("Probability of transition: "+new DecimalFormat("0.00").format(f.integration(slider.getValue())));
						
						}
		    		
		    	}; executor.execute(worker);
		    	
		    	
		    	
		    }
		    
		});
		
		
		
		timer.start();
		
		options.setVariables(new int[]{0,0,0},new int[]{100,20,30,7,15});
		btnSet.doClick();
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
		
		
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args){
		 SwingUtilities.invokeLater(new Runnable() {
	          @Override
	          public void run() {
	        	  
	              MainFrame program= new MainFrame();
	              program.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	              program.setSize(1200, 600);
	              program.setTitle("Quantum Tunneling");
	              program.setVisible(true);
	          }
	      });
		
		
		
		
	}
	
	
	
	
	

}
