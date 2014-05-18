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
	
	class AnimationProvider extends SwingWorker<Integer,Integer>{
		public AnimationProvider() {
			// TODO Auto-generated constructor stub
		}

		@Override
		protected Integer doInBackground() throws Exception {
			getContentPane().remove(chartContainer);
			f.evolution();
			return null;
		}
		
		protected void done(){
			chart=plotter.plot(f.seriesWave(),f.seriesPotential(),"time: "+Double.toString(f.getTime()));
			chartContainer = new ChartPanel(chart);
			getContentPane().add(chartContainer, "cell 1 0,grow");
			getContentPane().revalidate();
			getContentPane().repaint();
			
		}
	}

	
	
	public MainWindow() {
		super();
	   	provider= new AnimationProvider();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1200, 500);
		setResizable(false);
		setTitle("Quantum Tunneling");
		panel = new OptionPanel();
		getContentPane().setLayout(new MigLayout("", "[361.00][811.00]", "[419.00]"));
		
		panelContainer = new JPanel();
		getContentPane().add(panelContainer, "cell 0 0,alignx left,aligny top");
		panelContainer.add(panel);
		
		
		timer = new Timer(100, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	//provider.execute();
		    	getContentPane().remove(chartContainer);
				f.evolution();
				chart=plotter.plot(f.seriesWave(),f.seriesPotential(),"time: "+Double.toString(f.getTime()));
				chartContainer = new ChartPanel(chart);
				getContentPane().add(chartContainer, "cell 1 0,grow");
				getContentPane().revalidate();
				getContentPane().repaint();
		    }
		});  
		
		
		
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().remove(chartContainer);
				f=new WaveFunction(1, 1, 1, 10,100);
				f.setPotential(50,10,1);
				timer.start();
				System.out.println("Play...");
				System.out.println(panel.getVariable(0)+" "+panel.getVariable(1)+" "+panel.getVariable(2)+" "+panel.getVariable(3)+" "+panel.getVariable(4)+" "+panel.getVariable(5)+" "+panel.getVariable(6));
			}
		});
		panel.add(btnPlay, "cell 0 14,alignx center");
		
		chartContainer = new ChartPanel(plotter.plot(new XYSeries("Wave"),new XYSeries("Potential"),"t: 0.0"));
		getContentPane().add(chartContainer, "cell 1 0,grow");
		
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
