package gui;


import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class OptionPanel  extends JPanel{
	
	/*
	 * @author Micha� Murawski
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
	private JSlider sliderV0,sliderEnergy,sliderMass,sliderSigma,sliderWaveX0,sliderLength,sliderA,sliderPotentialX0;
	private JLabel lblV0,lblEnergy,lblMass,lblSigma,lblWaveX0,lblLength,lblA,lblPotentialX0;
	
	
	OptionPanel(){
		super();
		parametersWave= new double[5];	//wave function parameters
		parametersPotential = new double[3];	//potential parameters
		checkList = new boolean[8];	//list of boolean which is being use to check if all parameters are set
		checkList[4]=true;
		
		setLayout(new MigLayout("", "[361.00]", "[][][][][][][][][][][][][][][][][]"));
		
		
		//Labels and sliders
		
		//Energy label
		lblEnergy = new JLabel("Energy:...MeV");
		add(lblEnergy, "cell 0 0,alignx center");
		//Energy slider
		sliderEnergy = new JSlider();
		sliderEnergy.setMinorTickSpacing(1);
		sliderEnergy.setValue(0);
		sliderEnergy.setSnapToTicks(true);
		sliderEnergy.setPaintTicks(true);
		sliderEnergy.setPaintLabels(true);
		add(sliderEnergy, "cell 0 1,growx");
		
		//Mass label
		lblMass = new JLabel("Mass:...GeV/c^2");
		add(lblMass, "cell 0 2,alignx center");
		//Mass slider
		sliderMass = new JSlider();
		sliderMass.setMinimum(1);
		sliderMass.setValue(0);
		sliderMass.setSnapToTicks(true);
		sliderMass.setPaintTicks(true);
		sliderMass.setPaintLabels(true);
		sliderMass.setMinorTickSpacing(1);
		add(sliderMass, "cell 0 3,growx");
		
		//Sigma label
		lblSigma = new JLabel("Sigma:...");
		add(lblSigma, "cell 0 4,alignx center");
		//Sigma slider
		sliderSigma = new JSlider();
		sliderSigma.setMaximum(1000);
		sliderSigma.setMinimum(1);
		sliderSigma.setValue(0);
		sliderSigma.setSnapToTicks(true);
		sliderSigma.setPaintTicks(true);
		sliderSigma.setPaintLabels(true);
		sliderSigma.setMinorTickSpacing(1);
		add(sliderSigma, "cell 0 5,growx");
		
		// Initial position of wave label
		lblWaveX0 = new JLabel("x0:...fm");
		add(lblWaveX0, "cell 0 6,alignx center");
		//Initial position of wave slider
		sliderWaveX0 = new JSlider();
		sliderWaveX0.setValue(0);
		sliderWaveX0.setSnapToTicks(true);
		sliderWaveX0.setPaintTicks(true);
		sliderWaveX0.setPaintLabels(true);
		sliderWaveX0.setMinorTickSpacing(1);
		add(sliderWaveX0, "cell 0 7,growx");
		
		//Length label
		lblLength = new JLabel("Length: ... fm");
		add(lblLength, "cell 0 8,alignx center");
		//Length slider
		sliderLength = new JSlider();
		sliderLength.setMinimum(20);
		sliderLength.setValue(0);
		sliderLength.setSnapToTicks(true);
		sliderLength.setPaintTicks(true);
		sliderLength.setPaintLabels(true);
		sliderLength.setMinorTickSpacing(1);
		add(sliderLength, "cell 0 9,growx");
		
		//Potential value label
		 lblV0 = new JLabel("v0:...MeV");
		add(lblV0, "cell 0 10,alignx center");
		//Potential value slider
		 sliderV0 = new JSlider();
		sliderV0.setMinimum(-20);
		sliderV0.setMaximum(20);
		sliderV0.setValue(0);
		sliderV0.setSnapToTicks(true);
		sliderV0.setPaintTicks(true);
		sliderV0.setPaintLabels(true);
		sliderV0.setMinorTickSpacing(1);
		add(sliderV0, "cell 0 11,growx");
		
		//Initial potential position
		 lblPotentialX0 = new JLabel("x0:...fm");
		add(lblPotentialX0, "cell 0 12,alignx center");
		//Initial potential position slider
		 sliderPotentialX0 = new JSlider();
		sliderPotentialX0.setValue(0);
		sliderPotentialX0.setSnapToTicks(true);
		sliderPotentialX0.setPaintTicks(true);
		sliderPotentialX0.setPaintLabels(true);
		sliderPotentialX0.setMinorTickSpacing(1);
		add(sliderPotentialX0, "cell 0 13,growx");
		
		//Range of potential barrier position label
		lblA = new JLabel("a:...fm");
		add(lblA, "cell 0 14,alignx center");
		//Range of potential barrier slider
		sliderA = new JSlider();
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
				lblV0.setText("V0: "+sliderV0.getValue()+" MeV");
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
				parametersWave[0]=(double)sliderEnergy.getValue()*0.05;
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
	 * @author Micha� Murawski
	 * @param null
	 * @return boolean(true if its ready, false if its not)
	 */
		public void setVariables(int[] variables1, int[] variables2){
		
		parametersPotential[0]=(double)variables1[0];
		sliderV0.setValue(variables1[0]);
		lblV0.setText("V0: "+sliderV0.getValue()+" MeV");
		checkList[4]=true;
		parametersPotential[1]=(double)variables1[1];
		sliderPotentialX0.setValue(variables1[1]);
		lblPotentialX0.setText("X0: "+sliderPotentialX0.getValue()+" fm");
		checkList[5]=true;
		parametersPotential[2]=(double)(variables1[2]);
		sliderA.setValue(variables1[2]);
		lblA.setText("a: "+sliderA.getValue()+" fm");
		checkList[6]=true;
		parametersWave[0]=(double)(variables2[0]*0.05);
		sliderEnergy.setValue(variables2[0]);
		lblEnergy.setText(String.format("Energy: %.1f  MeV/c^2", sliderEnergy.getValue()*0.05));
		checkList[0]=true;
		
		parametersWave[1]=(double)(variables2[1]*0.1);
		sliderMass.setValue(variables2[1]);
    	lblMass.setText(String.format("Mass: %.1f  GeV/c^2", sliderMass.getValue()*0.1));
		checkList[1]=true;		
			
		parametersWave[2]=(double)(variables2[2]);
		sliderSigma.setValue(variables2[2]);
		lblSigma.setText("Sigma: "+parametersWave[2]);
		checkList[2]=true;		
				
		parametersWave[3]=(double)variables2[3];
		sliderWaveX0.setValue(variables2[3]);
		lblWaveX0.setText(String.format("x0: "+sliderWaveX0.getValue()+" fm"));
		checkList[3]=true;
													
		parametersWave[4]=(double)variables2[4];
		sliderLength.setValue(variables2[4]);
		lblLength.setText("Length: "+parametersWave[4]+" fm");
		checkList[7]=true;
						
		
		
	}
	
	public boolean ready(){
		for(boolean b : checkList) if(!b) return false;
	    return true;
	}
	
	/*
	 * @author Micha� Murawski
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
	 * @author Micha� Murawski
	 * Main method which provides test.
	 */

	
}
