package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import mainpackage.WaveFunction;

public class PlotPanel extends JPanel{
	
	WaveFunction f;
	XYSeries wave, potential;
	XYSeriesCollection dataset;
	JFreeChart chart;
	String title;
	ChartFrame frame;
	
	
	PlotPanel(WaveFunction f,String title){
		wave=new XYSeries("Prob");
		potential= new XYSeries("Potential");
		
		for(int ii=1;ii<f.getN();ii++){
			wave.add(ii*0.1,f.getProbabilityDensity().get(ii));
			potential.add(ii*0.1,f.getPotential().get(ii));
		}
		dataset = new XYSeriesCollection();
		dataset.addSeries(wave);
		dataset.addSeries(potential);
		chart = null;
		chart = ChartFactory.createXYLineChart(
				"Wykres XY",//Tytuł
				"x- Lable", // x-axis Opis
				"y- Lable", // y-axis Opis
				dataset, // Dane
				PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
				true, // pozkaż legende
				true, // podpowiedzi tooltips
				false
			);
		XYPlot plot =(XYPlot)chart.getXYPlot();
		plot.setDomainCrosshairVisible(true);
	    plot.setRangeCrosshairVisible(true);
	    NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(0.00, 100.00);
        domain.setTickUnit(new NumberTickUnit(10));
        domain.setVerticalTickLabels(true);
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(0.0,0.3);
        range.setTickUnit(new NumberTickUnit(0.3));
        ChartFrame frame = new ChartFrame("XYArea Chart",chart);
		
	}
	
	void updateData(){
		wave=new XYSeries("Y");
		potential= new XYSeries("X");
		
		for(int ii=1;ii<f.getN();ii++){
			wave.add(ii*0.1,f.getProbabilityDensity().get(ii));
			potential.add(ii*0.1,f.getPotential().get(ii));
		}
		dataset = new XYSeriesCollection();
		dataset.addSeries(wave);
		dataset.addSeries(potential);

	}
		
		
		public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                WaveFunction f = new WaveFunction(10, 10,0,10,100);
	                f.setPotential(50,10,0.3);
	               // for(int i=0; i<50;i++)
		             //   f.evolution();
	                JFrame jFrame = new JFrame();
	                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                jFrame.setLayout(new BorderLayout());
	                PlotPanel p=new PlotPanel(f," ");
	          
	                p.setVisible(true);
	                p.setSize(400,300);
	               
	           
		               
	               
	                jFrame.add(p, BorderLayout.CENTER);
	                //jFrame.setSize(640, 480);
	                //jFrame.setVisible(true);
	                
	            }
	        });
	    }
	}