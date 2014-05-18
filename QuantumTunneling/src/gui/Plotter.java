package gui;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Plotter {
	public Plotter(){
		
	}
	public JFreeChart plot(XYSeries u, XYSeries d,String title){
		XYSeriesCollection collection = new XYSeriesCollection();
		collection.addSeries(u);
		collection.addSeries(d);
	JFreeChart plot = ChartFactory.createXYLineChart(title,"X","Y", collection,PlotOrientation.VERTICAL,true,true,false);
	plot.getXYPlot().setBackgroundPaint(Color.WHITE); //kolor obszaru kreœlenia
	plot.getXYPlot().setRangeGridlinePaint(Color.black); //kolor poziomych linii
	plot.getXYPlot().setDomainGridlinePaint(Color.black); //kolor pionowych linii
	plot.getXYPlot().getDomainAxis().setRange(0.0,100);
	plot.getXYPlot().getRangeAxis().setRange(0.0,1);
	return plot;
		
	}

}
