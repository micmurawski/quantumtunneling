package gui;

import javax.swing.JPanel;
import mainpackage.WaveFunction;
import java.awt.GridLayout;
import javax.swing.JSlider;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Panel extends JPanel{
	
	//private double potetnialX0;
	private double mass;
	private double potentialConstant;
	private double waveX0;
	private double energy;
	boolean[] checkList;
	
	
	WaveFunction f;
	
	
	public Panel() {
		setLayout(null);
		checkList = new boolean[4];
		
		final JSlider slider = new JSlider();
		slider.setValue(0);
		slider.setBounds(12, 53, 350, 27);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		add(slider);
		
		JLabel lblEnergry = new JLabel("Energia:");
		lblEnergry.setBounds(40, 25, 70, 15);
		add(lblEnergry);
		
		final JLabel lblEnergyValue = new JLabel("0 eV");
		lblEnergyValue.setBounds(140, 25, 70, 15);
		add(lblEnergyValue);
		
		
		final JSlider slider2 = new JSlider();
		slider2.setValue(0);
		slider2.setBounds(12, 123, 350, 27);
		slider2.setPaintTicks(true);
		slider2.setMinorTickSpacing(1);
		add(slider2);
		
		JLabel lblMass = new JLabel("Masa:");
		lblMass.setBounds(40, 95, 70, 15);
		add(lblMass);
		
		final JLabel lblMassValue = new JLabel("0 eV");
		lblMassValue.setBounds(140, 95, 70, 15);
		add(lblMassValue);
		
		final JSlider slider3 = new JSlider();
		slider3.setValue(0);
		slider3.setBounds(12, 193, 350, 27);
		slider3.setPaintTicks(true);
		slider3.setMinorTickSpacing(1);
		add(slider3);
		
		JLabel lblWaveX0 = new JLabel("x0:");
		lblWaveX0.setBounds(40, 165, 70, 15);
		add(lblWaveX0);
		
		final JLabel lblWaveX0Value = new JLabel("0");
		lblWaveX0Value.setBounds(140, 165, 70, 15);
		add(lblWaveX0Value);
		
		final JSlider slider4 = new JSlider();
		slider4.setValue(0);
		slider4.setBounds(12, 263, 350, 27);
		slider4.setPaintTicks(true);
		slider4.setMinorTickSpacing(1);
		add(slider4);
		
		JLabel lblPotetialConstant = new JLabel("V0:");
		lblPotetialConstant.setBounds(40, 235, 70, 15);
		add(lblPotetialConstant);
		
		final JLabel lblPotetialConstantValue = new JLabel("0 eV");
		lblPotetialConstantValue.setBounds(140, 235, 70, 15);
		add(lblPotetialConstantValue);
		
		
		
		
		
		////BUTONY
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int ii=0;ii<checkList.length;ii++){
					if(!checkList[ii]){
						System.out.print("errror");
						break;
					}else{
						System.out.println("suksec");
						break;
					}
				}
			}
		});
		btnPlay.setBounds(40, 400, 100, 25);
		add(btnPlay);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(150, 400, 100, 25);
		add(btnPause);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(260, 400, 100, 25);
		add(btnStop);
	
	
		////////////LISTENERY
	
		
		slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               lblEnergyValue.setText(String.valueOf(slider.getValue())+" eV");
               energy=slider.getValue();
               checkList[0]=true;
               
            }});
		
		slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e2) {
               lblMassValue.setText(String.valueOf(slider2.getValue())+" eV");
               mass=slider.getValue();
               checkList[1]=true;
               
               
            }});
		
		slider3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e2) {
            	lblWaveX0Value.setText(String.valueOf(slider3.getValue()));
               waveX0=slider3.getValue();
               checkList[2]=true;
               
               
            }});
		
		slider4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e2) {
            	lblPotetialConstantValue.setText(String.valueOf(slider4.getValue()+" eV"));
               potentialConstant=slider4.getValue();
               checkList[3]=true;
               
               
            }});
		
		
		
		
	}
	
	
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	//LocaleHolder.getHolder().setLocale(new Locale("pl"));
	                JFrame frame = new JFrame("Test");
	                frame.getContentPane().add(new Panel());
	                frame.setSize(400, 480);
	                frame.setVisible(true);
	            }
	        });
	    }
	
}
