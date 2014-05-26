package gui;

import java.awt.EventQueue;
import java.util.Locale;
import java.util.ResourceBundle;






import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainWindow extends JFrame {
	/**
	 * 
	 */
	Locale currentLocale=new Locale("en","GB");
	ResourceBundle text;
	private static final long serialVersionUID = 1L;
	public WaveFunction f;
	JPanel panelContainer;
	JFreeChart chart;
	ChartPanel chartContainer;
	OptionPanel panel;
	Plotter plotter= new Plotter();
	AnimationProvider provider;
	private JButton btnPlay;
	Timer timer;
	boolean isRunning,isSet;
	XYSeriesCollection collection;
	
	
	public MainWindow() {
		super();
	   	//provider= new AnimationProvider();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1200, 600);
		setResizable(false);
		setTitle("Quantum Tunneling");
		panel = new OptionPanel();
		getContentPane().setLayout(new MigLayout("", "[361.00][811.00]", "[419.00][][]"));
		panelContainer = new JPanel();
		getContentPane().add(panelContainer, "cell 0 0,alignx left,aligny top");
		panelContainer.add(panel);
		f=new WaveFunction(new double[]{5.5,3.72,100,15,100});
		f.setPotential(new double[]{10,50,5});
		collection=plotter.collections(f.seriesWave(), f.seriesPotential(), 0);
		
		chart=plotter.plot(collection,"time: "+Double.toString(f.getTime()),100,"X","Y");
		
		
		timer = new Timer(30, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	getContentPane().remove(chartContainer);
				f.evolution();
				collection=plotter.collections(f.seriesWave(), f.seriesPotential(), 0);
				//chart=plotter.plot(f.seriesWave(),f.seriesPotential(),50,"time: "+Double.toString(f.getTime()),100,"X","Y");
				//chartContainer = new ChartPanel(chart);
				//getContentPane().add(chartContainer, "cell 1 0,grow");
				//getContentPane().revalidate();
				//getContentPane().repaint();
		    }
		});  
		
		chartContainer = new ChartPanel(chart);
		getContentPane().add(chartContainer, "cell 1 0,grow");
		
		
		
		btnPlay = new JButton("Play");
		getContentPane().add(btnPlay, "cell 0 2");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().remove(chartContainer);
				//f=new WaveFunction(panel.getVariablesWave());
				//f.setPotential(panel.getVariablesPotential());
				timer.start();
				System.out.println("Play...");
			}
		});
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	
}
