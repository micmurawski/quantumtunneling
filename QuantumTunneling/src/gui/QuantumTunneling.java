package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;


public class QuantumTunneling extends JFrame {

	/**
	 * 
	 */
	
	Locale currentLocale=new Locale("en","GB");
	ResourceBundle text;
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	
	public double energy;
	public double mass;
	public double sigma;
	public double x0Wave;
	public double x0Potential;
	public double potentialConstant;
	boolean[] checkList= new boolean[6];
	boolean running;
	WaveFunction f;
	Plotter mrP;
	JFreeChart Chart;
	
	Graph x;
	JFreeChart xGraph;
	ChartPanel xGraphPanel;
	Color curveColor_xt=Color.blue;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuantumTunneling frame = new QuantumTunneling();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuantumTunneling() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1200, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		mrP=new Plotter();
		
	/////////RYSOWANIE	
	
			 
	//	 lissajousGraphPanel = new ChartPanel(lissajousGraph);
	//	 lissajousGraphPanel.setSize(chartPanel.getSize());
	//	 chartPanel.add(lissajousGraphPanel);		
		
		
		
		
		
		/********** Podpanele podpanelu Graph **********/
		// Chart
		
		JPanel panelGraph = new JPanel();
		panelGraph.setBounds(260, 11, 924, 426);
		contentPane.add(panelGraph);
		f= new WaveFunction(1,1,50,5,100);
		f.setPotential(50,10,1);
		panelGraph.setLayout(new BoxLayout(panelGraph, BoxLayout.X_AXIS));
		Chart = mrP.plot(f.seriesWave(),f.seriesPotential(),Double.toString(f.getTime()));	
	

		 
		 xGraphPanel = new ChartPanel(Chart);
		 xGraphPanel.setSize(panelGraph.getSize());
		 panelGraph.add(xGraphPanel);
		
		
		

		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(checkList[0])
					System.out.println(true);
			}
		});
		btnPlay.setBounds(372, 445, 91, 23);
		contentPane.add(btnPlay);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(473, 445, 91, 23);
		contentPane.add(btnStop);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(271, 445, 91, 23);
		contentPane.add(btnPause);
		
		final JSlider sliderEnergy = new JSlider();
		sliderEnergy.setMinorTickSpacing(1);
		sliderEnergy.setValue(0);
		sliderEnergy.setSnapToTicks(true);
		sliderEnergy.setPaintTicks(true);
		sliderEnergy.setPaintLabels(true);
		sliderEnergy.setBounds(10, 40, 250, 31);
		contentPane.add(sliderEnergy);
		
		final JLabel lblEnergy = new JLabel("Energy: ... eV");
		lblEnergy .setBounds(25, 22, 100, 14);
		contentPane.add(lblEnergy);
		
		final JSlider sliderMass  = new JSlider();
		sliderMass.setMinorTickSpacing(1);
		sliderMass.setValue(0);
		sliderMass.setSnapToTicks(true);
		sliderMass.setPaintTicks(true);
		sliderMass.setPaintLabels(true);
		sliderMass.setBounds(10, 110, 250, 31);
		contentPane.add(sliderMass);
		
		final JLabel lblMass = new JLabel("Mass: ... GeV");
		lblMass.setBounds(25, 92, 100, 14);
		contentPane.add(lblMass);
		
		final JSlider sliderSigma  = new JSlider();
		sliderSigma.setMinorTickSpacing(1);
		sliderSigma.setValue(0);
		sliderSigma.setSnapToTicks(true);
		sliderSigma.setPaintTicks(true);
		sliderSigma.setPaintLabels(true);
		sliderSigma.setBounds(10, 180, 250, 31);
		contentPane.add(sliderSigma);
		
		final JLabel lblSigma = new JLabel("Sigma: ...");
		lblSigma.setBounds(25, 162, 100, 14);
		contentPane.add(lblSigma);
		
		final JSlider sliderX0Wave  = new JSlider();
		sliderX0Wave.setMinorTickSpacing(1);
		sliderX0Wave.setValue(0);
		sliderX0Wave.setSnapToTicks(true);
		sliderX0Wave.setPaintTicks(true);
		sliderX0Wave.setPaintLabels(true);
		sliderX0Wave.setBounds(10, 250, 250, 31);
		contentPane.add(sliderX0Wave);
		
		final JLabel lblX0Wave = new JLabel("x0:... nm");
		lblX0Wave.setBounds(25, 232, 100, 14);
		contentPane.add(lblX0Wave);
		
		final JSlider sliderPotetialConstant  = new JSlider();
		sliderPotetialConstant.setMinorTickSpacing(1);
		sliderPotetialConstant.setValue(0);
		sliderPotetialConstant.setSnapToTicks(true);
		sliderPotetialConstant.setPaintTicks(true);
		sliderPotetialConstant.setPaintLabels(true);
		sliderPotetialConstant.setBounds(10, 320, 250, 31);
		contentPane.add(sliderPotetialConstant);
		
		final JLabel lblPotetialConstant = new JLabel("V0:... eV");
		lblPotetialConstant.setBounds(25, 302, 100, 14);
		contentPane.add(lblPotetialConstant);
		
		final JSlider sliderX0Potential  = new JSlider();
		sliderX0Potential.setMinorTickSpacing(1);
		sliderX0Potential.setValue(0);
		sliderX0Potential.setSnapToTicks(true);
		sliderX0Potential.setPaintTicks(true);
		sliderX0Potential.setPaintLabels(true);
		sliderX0Potential.setBounds(10, 390, 250, 31);
		contentPane.add(sliderX0Potential);
		
		final JLabel lblX0Potential = new JLabel("X0:... nm");
		lblX0Potential.setBounds(25, 372, 100, 14);
		contentPane.add(lblX0Potential);
		
		sliderEnergy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				energy=(double)sliderEnergy.getValue()/10;
				lblEnergy.setText("Energy: "+energy+" eV");
				checkList[0]=true;
				
			}
		});
		
		sliderMass.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mass=(double)sliderMass.getValue();
				lblMass.setText("Mass: "+mass+" GeV");
				checkList[1]=true;
				
			}
		});
		
		sliderSigma.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sigma=(double)sliderSigma.getValue();
				lblSigma.setText("Sigma: "+sigma+" *10^-20");
				checkList[2]=true;
				
			}
		});
		
		sliderX0Wave.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				x0Wave=(double)sliderX0Wave.getValue();
				lblX0Wave.setText("x0: "+x0Wave+" nm");
				checkList[3]=true;
				
			}
		});
		
		sliderPotetialConstant.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				potentialConstant=(double)sliderPotetialConstant.getValue();
				lblPotetialConstant.setText("V0: "+potentialConstant+" eV");
				checkList[4]=true;
				
			}
		});
		
		sliderX0Potential.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				x0Potential=(double)sliderX0Potential.getValue();
				lblX0Potential.setText("x0: "+x0Potential+" nm");
				checkList[5]=true;
				
			}
		});
	}
}
