package gui;

import java.awt.BorderLayout;

import javafx.scene.chart.LineChart;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import mainpackage.WaveFunction;

public class Test extends JPanel{
	
	Test(WaveFunction f){
		
		XYSeries wave =new XYSeries("Prob");
		XYSeries potential = new XYSeries("Potential");
		for(int ii=1;ii<f.getN();ii++){
			wave.add(ii*0.1,f.getProbabilityDensity().get(ii));
			potential.add(ii*0.1,f.getPotential().get(ii));
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(wave);
		dataset.addSeries(potential);
		JFreeChart chart = ChartFactory.createXYLineChart(
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
		
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	WaveFunction f = new WaveFunction(10, 10,0,10,100);
            	f.setPotential(50,10,0.3);
            	
            	JFrame jFrame = new JFrame();
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setLayout(new BorderLayout());
                jFrame.add(null, BorderLayout.CENTER);
                jFrame.setSize(640, 480);
                jFrame.setVisible(true);
            	
            	
            	
            }});
            }
	}


