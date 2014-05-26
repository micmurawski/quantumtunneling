package gui;

import java.util.Locale;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class OptionPanel  extends JPanel{
	
	/*
	 * @author Micha³ Murawski
	 * @param
	 * @return
	 * This class extends JPanel to provide element of GUI which takes all
	 * parameters of wave function and potential given by user. It consist method
	 * to take given parameters.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double[] parametersWave;
	public double[] parametersPotential;
	public boolean[] checkList;
	
	
	OptionPanel(){
		super();
		parametersWave= new double[5];	//wave function parameters
		parametersPotential = new double[3];	//potential parameters
		checkList = new boolean[8];	//list of boolean which is being use to check if all parameters are set
		checkList[4]=true;
		
		setLayout(new MigLayout("", "[361.00]", "[][][][][][][][][][][][][][][][][]"));
		
		
		//Labels and sliders
		
		//Energy label
		final JLabel lblEnergy = new JLabel("Energy:...MeV");
		add(lblEnergy, "cell 0 0,alignx center");
		//Energy slider
		final JSlider sliderEnergy = new JSlider();
		sliderEnergy.setMinorTickSpacing(1);
		sliderEnergy.setValue(0);
		sliderEnergy.setSnapToTicks(true);
		sliderEnergy.setPaintTicks(true);
		sliderEnergy.setPaintLabels(true);
		add(sliderEnergy, "cell 0 1,growx");
		
		//Mass label
		final JLabel lblMass = new JLabel("Mass:...GeV/c^2");
		add(lblMass, "cell 0 2,alignx center");
		//Mass slider
		final JSlider sliderMass = new JSlider();
		sliderMass.setMinimum(1);
		sliderMass.setValue(0);
		sliderMass.setSnapToTicks(true);
		sliderMass.setPaintTicks(true);
		sliderMass.setPaintLabels(true);
		sliderMass.setMinorTickSpacing(1);
		add(sliderMass, "cell 0 3,growx");
		
		//Sigma label
		final JLabel lblSigma = new JLabel("Sigma:...");
		add(lblSigma, "cell 0 4,alignx center");
		//Sigma slider
		final JSlider sliderSigma = new JSlider();
		sliderSigma.setMaximum(1000);
		sliderSigma.setMinimum(1);
		sliderSigma.setValue(0);
		sliderSigma.setSnapToTicks(true);
		sliderSigma.setPaintTicks(true);
		sliderSigma.setPaintLabels(true);
		sliderSigma.setMinorTickSpacing(1);
		add(sliderSigma, "cell 0 5,growx");
		
		// Initial position of wave label
		final JLabel lblWaveX0 = new JLabel("x0:...fm");
		add(lblWaveX0, "cell 0 6,alignx center");
		//Initial position of wave slider
		final JSlider sliderWaveX0 = new JSlider();
		sliderWaveX0.setValue(0);
		sliderWaveX0.setSnapToTicks(true);
		sliderWaveX0.setPaintTicks(true);
		sliderWaveX0.setPaintLabels(true);
		sliderWaveX0.setMinorTickSpacing(1);
		add(sliderWaveX0, "cell 0 7,growx");
		
		//Length label
		final JLabel lblLength = new JLabel("Length: ... fm");
		add(lblLength, "cell 0 8,alignx center");
		//Length slider
		final JSlider sliderLength = new JSlider();
		sliderLength.setMinimum(20);
		sliderLength.setValue(0);
		sliderLength.setSnapToTicks(true);
		sliderLength.setPaintTicks(true);
		sliderLength.setPaintLabels(true);
		sliderLength.setMinorTickSpacing(1);
		add(sliderLength, "cell 0 9,growx");
		
		//Potential value label
		final JLabel lblV0 = new JLabel("v0:...MeV");
		add(lblV0, "cell 0 10,alignx center");
		//Potential value slider
		final JSlider sliderV0 = new JSlider();
		sliderV0.setMinimum(-10);
		sliderV0.setMaximum(10);
		sliderV0.setValue(0);
		sliderV0.setSnapToTicks(true);
		sliderV0.setPaintTicks(true);
		sliderV0.setPaintLabels(true);
		sliderV0.setMinorTickSpacing(1);
		add(sliderV0, "cell 0 11,growx");
		
		//Initial potential position
		final JLabel lblPotentialX0 = new JLabel("x0:...fm");
		add(lblPotentialX0, "cell 0 12,alignx center");
		//Initial potential position slider
		final JSlider sliderPotentialX0 = new JSlider();
		sliderPotentialX0.setValue(0);
		sliderPotentialX0.setSnapToTicks(true);
		sliderPotentialX0.setPaintTicks(true);
		sliderPotentialX0.setPaintLabels(true);
		sliderPotentialX0.setMinorTickSpacing(1);
		add(sliderPotentialX0, "cell 0 13,growx");
		
		//Range of potential barrier position label
		final JLabel lblA = new JLabel("a:...fm");
		add(lblA, "cell 0 14,alignx center");
		//Range of potential barrier slider
		final JSlider sliderA = new JSlider();
		sliderA.setValue(0);
		sliderA.setSnapToTicks(true);
		sliderA.setPaintTicks(true);
		sliderA.setPaintLabels(true);
		sliderA.setMinorTickSpacing(1);
		add(sliderA, "cell 0 15,growx");
		
		//Action Listeners
		
		//Potential value slider listener
		sliderV0.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parametersPotential[0]=(double)sliderV0.getValue();
				lblV0.setText("V0: "+sliderV0.getValue()+" eV");
				checkList[4]=true;
				
			}}
			);
		
		
		//Initial potential position listener
		sliderPotentialX0.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parametersPotential[1]=(double)sliderPotentialX0.getValue();
				lblPotentialX0.setText("X0: "+sliderPotentialX0.getValue()+" fm");
				checkList[5]=true;
				
			}}
			);
		
		
		//Range of potential barrier value listener
		sliderA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parametersPotential[2]=(double)sliderA.getValue();
				lblA.setText("a: "+sliderA.getValue()+" fm");
				checkList[6]=true;
				
			}}
			);
		//Energy value slider listener
		sliderEnergy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parametersWave[0]=(double)sliderEnergy.getValue()*0.2;
				lblEnergy.setText(String.format("Energy: %.1f  MeV/c^2", sliderEnergy.getValue()*0.05));
				checkList[0]=true;
				
			}}
			);
		//Mass value slider listener
		sliderMass.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parametersWave[1]=(double)sliderMass.getValue()*0.1;
				lblMass.setText(String.format("Mass: %.1f  GeV/c^2", sliderMass.getValue()*0.1));
				checkList[1]=true;
				
			}}
			);
		//Mass value slider listener
		sliderSigma.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parametersWave[2]=(double)sliderSigma.getValue();
				lblSigma.setText("Sigma: "+parametersWave[2]);
				checkList[2]=true;
				
			}}
			);
		//Initial wave position slider listener
		sliderWaveX0.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parametersWave[3]=(double)sliderWaveX0.getValue();
				lblWaveX0.setText(String.format("x0: "+sliderWaveX0.getValue()+" fm"));
				checkList[3]=true;
				
			}}
			);
		
		//Length value slider listener
		sliderLength.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parametersWave[4]=(double)sliderLength.getValue();
				lblLength.setText("Length: "+parametersWave[4]+" fm");
				checkList[7]=true;
				
			}}
			);
	}
	
	/*
	 * @author Micha³ Murawski
	 * @param null
	 * @return boolean(true if its ready, false if its not)
	 */
	
	public boolean ready(){
		for(boolean b : checkList) if(!b) return false;
	    return true;
	}
	
	/*
	 * @author Micha³ Murawski
	 * @param null
	 * @return table(double)
	 * 
	 * Methods returns values given by sliders
	 * 
	 */
	
	
	public double[] getVariablesPotential(){
		return parametersPotential;
	}
	
	public double[] getVariablesWave(){
		return parametersWave;
	}
	
	
	/*
	 * @author Micha³ Murawski
	 * Main method which provides test.
	 */
	
	
	static public void  main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	Locale currentLocale=new Locale("en","GB");
                JFrame frame = new JFrame("Test");
                OptionPanel p=new OptionPanel();
				frame.getContentPane().add(p);
                frame.setSize(450, 480);
                frame.setVisible(true);
                System.out.println(p.ready());
            }
        });
		
		
		
	}

	
}
