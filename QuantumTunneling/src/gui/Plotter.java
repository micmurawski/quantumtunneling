package gui;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Plotter {
	/*
	 * @author Micha³ Murawsk
	 * @param	XYSeries,XYSeries,Double,String,Double Double,String,String
	 * @return	JFreeChart
	 * 
	 * Class defines method that return JFreeChart
	 * 
	 */
	public Plotter(){
		
	}
	
	
	
	public JFreeChart plot(XYSeries u, XYSeries d, double x0,String title, double xRangeMax,double yRangeMin,double yRangeMax,String X,String Y){
		XYSeriesCollection collection = new XYSeriesCollection();
		collection.addSeries(u);
		collection.addSeries(d);
		XYSeries g = new XYSeries("x0",false);
		g.add(x0,10);
		g.add(x0,-10);
		collection.addSeries(g);
	JFreeChart plot = ChartFactory.createXYLineChart(title,X,Y, collection,PlotOrientation.VERTICAL,true,true,false);
	plot.getXYPlot().setBackgroundPaint(Color.WHITE); //kolor obszaru kreœlenia
	plot.getXYPlot().setRangeGridlinePaint(Color.black); //kolor poziomych linii
	plot.getXYPlot().setDomainGridlinePaint(Color.black); //kolor pionowych linii
	plot.getXYPlot().getDomainAxis().setRange(0,xRangeMax);
	plot.getXYPlot().getRangeAxis().setRange(yRangeMin,yRangeMax);
	return plot;
		
	}

}