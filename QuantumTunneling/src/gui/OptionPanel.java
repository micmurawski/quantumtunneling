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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double[] parameters;
	public boolean[] checkList;
	
	
	
	
	
	OptionPanel(){
		super();
		parameters= new double[7];
		checkList = new boolean[7];
		setLayout(new MigLayout("", "[361.00]", "[][][][][][][][][][][][][][][][][]"));
		
		final JLabel lblEnergy = new JLabel("Energy:...eV");
		add(lblEnergy, "cell 0 0,alignx center");
		
		final JSlider sliderEnergy = new JSlider();
		sliderEnergy.setMinorTickSpacing(1);
		sliderEnergy.setValue(0);
		sliderEnergy.setSnapToTicks(true);
		sliderEnergy.setPaintTicks(true);
		sliderEnergy.setPaintLabels(true);
		add(sliderEnergy, "cell 0 1,growx");
		
		final JLabel lblMass = new JLabel("Mass:...MeV/c^2");
		add(lblMass, "cell 0 2,alignx center");
		
		final JSlider sliderMass = new JSlider();
		sliderMass.setMinimum(1);
		sliderMass.setValue(0);
		sliderMass.setSnapToTicks(true);
		sliderMass.setPaintTicks(true);
		sliderMass.setPaintLabels(true);
		sliderMass.setMinorTickSpacing(1);
		add(sliderMass, "cell 0 3,growx");
		
		final JLabel lblSigma = new JLabel("Sigma:...");
		add(lblSigma, "cell 0 4,alignx center");
		
		final JSlider sliderSigma = new JSlider();
		sliderSigma.setMinimum(1);
		sliderSigma.setValue(0);
		sliderSigma.setSnapToTicks(true);
		sliderSigma.setPaintTicks(true);
		sliderSigma.setPaintLabels(true);
		sliderSigma.setMinorTickSpacing(1);
		add(sliderSigma, "cell 0 5,growx");
		
		final JLabel lblWaveX0 = new JLabel("x0:...A");
		add(lblWaveX0, "cell 0 6,alignx center");
		
		final JSlider sliderWaveX0 = new JSlider();
		sliderWaveX0.setValue(0);
		sliderWaveX0.setSnapToTicks(true);
		sliderWaveX0.setPaintTicks(true);
		sliderWaveX0.setPaintLabels(true);
		sliderWaveX0.setMinorTickSpacing(1);
		add(sliderWaveX0, "cell 0 7,growx");
		
		final JLabel lblV0 = new JLabel("v0:...eV");
		add(lblV0, "cell 0 8,alignx center");
		
		final JSlider sliderV0 = new JSlider();
		sliderV0.setValue(0);
		sliderV0.setSnapToTicks(true);
		sliderV0.setPaintTicks(true);
		sliderV0.setPaintLabels(true);
		sliderV0.setMinorTickSpacing(1);
		add(sliderV0, "cell 0 9,growx");
		
		final JLabel lblPotentialX0 = new JLabel("x0:...A");
		add(lblPotentialX0, "cell 0 10,alignx center");
		
		final JSlider sliderPotentialX0 = new JSlider();
		sliderPotentialX0.setValue(0);
		sliderPotentialX0.setSnapToTicks(true);
		sliderPotentialX0.setPaintTicks(true);
		sliderPotentialX0.setPaintLabels(true);
		sliderPotentialX0.setMinorTickSpacing(1);
		add(sliderPotentialX0, "cell 0 11,growx");
		
		final JLabel lblA = new JLabel("a:...A");
		add(lblA, "cell 0 12,alignx center");
		
		final JSlider sliderA = new JSlider();
		sliderA.setValue(0);
		sliderA.setSnapToTicks(true);
		sliderA.setPaintTicks(true);
		sliderA.setPaintLabels(true);
		sliderA.setMinorTickSpacing(1);
		add(sliderA, "cell 0 13,growx");
		
		sliderEnergy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parameters[0]=(double)sliderEnergy.getValue()*1.60217657e-19;
				lblEnergy.setText("Energy: "+sliderEnergy.getValue()+" eV");
				checkList[0]=true;
				
			}}
			);
		
		sliderMass.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parameters[1]=(double)sliderMass.getValue()*1.78266185042e-31;
				lblMass.setText(String.format("Mass: %.1f  MeV/c^2", sliderMass.getValue()*0.1));
				checkList[1]=true;
				
			}}
			);
		
		sliderSigma.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parameters[2]=(double)sliderSigma.getValue()*0.1;
				lblSigma.setText("Sigma: "+parameters[2]);
				checkList[2]=true;
				
			}}
			);
		
		sliderWaveX0.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parameters[3]=(double)sliderWaveX0.getValue()*0.1;
				lblWaveX0.setText(String.format("x0: %.1f A", sliderWaveX0.getValue()*0.1));
				checkList[3]=true;
				
			}}
			);
		
		sliderV0.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parameters[4]=(double)sliderV0.getValue()*1.60217657e-19;
				lblV0.setText("V0: "+sliderV0.getValue()+" eV");
				checkList[4]=true;
				
			}}
			);
		
		sliderPotentialX0.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parameters[5]=(double)sliderPotentialX0.getValue();
				lblPotentialX0.setText("V0: "+sliderPotentialX0.getValue()+" eV");
				checkList[5]=true;
				
			}}
			);
		
		sliderA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				parameters[6]=(double)sliderA.getValue();
				lblA.setText("a: "+sliderA.getValue()+" A");
				checkList[6]=true;
				
			}}
			);
		
		
		
		
	}
	
	public boolean ready(){
		for(boolean b : checkList) if(!b) return false;
	    return true;
	}
	
	
	
	
	
	
	
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

	public double getVariable(int i){
		return parameters[i];
	}
}
