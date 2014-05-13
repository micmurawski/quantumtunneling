package gui;
import java.awt.Color;
import java.util.Locale;
import java.util.ResourceBundle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph
{
	/******************** Dane do pakietów jêzykowych ********************/
	// Zmienna lokalizacji i pobranie pliku z odpowiedni¹ z nich
		Locale currentLocale; // = new Locale(language, country);
		ResourceBundle text; // = ResourceBundle.getBundle("properties.MessagesBundle", currentLocale);
		
	int size;
	XYSeries[] graph = new XYSeries[size]; 
	
	public Graph(Locale locale)
	{
		size = 0;
		currentLocale = locale;
		text = ResourceBundle.getBundle("properties.MessagesBundle", currentLocale);
	}
	
	void addSerie(XYSeries a)
	{
		size += 1;
		graph = new XYSeries[size];
		graph[size - 1] = a;
	}
	
	JFreeChart Make(XYSeries S)
	{
		XYSeriesCollection collection = new XYSeriesCollection();
			collection.addSeries(S);
		JFreeChart curve = ChartFactory.createScatterPlot(text.getString("graphTitle"), null, null, collection, PlotOrientation.VERTICAL, false, true, false);
		//curve.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
		curve.getXYPlot().setBackgroundPaint(Color.WHITE); //kolor obszaru kreœlenia
		curve.getXYPlot().setRangeGridlinePaint(Color.black); //kolor poziomych linii
		curve.getXYPlot().setDomainGridlinePaint(Color.black); //kolor pionowych linii
		return curve;
	}
	
	JFreeChart xSignalMake()
	{
		XYSeriesCollection collection = new XYSeriesCollection();
		for(int i=0; i < graph.length; i++)
			collection.addSeries(graph[i]);
		JFreeChart curve = ChartFactory.createXYLineChart(text.getString("xSignal"), "t[s]", null, collection, PlotOrientation.VERTICAL, false, true, false);
		//curve.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
		//curve.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
		curve.getXYPlot().setBackgroundPaint(Color.WHITE); //kolor obszaru kreœlenia
		curve.getXYPlot().setRangeGridlinePaint(Color.black); //kolor poziomych linii
		curve.getXYPlot().setDomainGridlinePaint(Color.black); 
		
		return curve;
	}
	
	JFreeChart ySignalMake()
	{
		XYSeriesCollection collection = new XYSeriesCollection();
		for(int i=0; i < graph.length; i++)
			collection.addSeries(graph[i]);
		JFreeChart curve = ChartFactory.createXYLineChart(text.getString("ySignal"), "t[s]", null, collection, PlotOrientation.VERTICAL, false, true, false);
		//curve.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
		//curve.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
		curve.getXYPlot().setBackgroundPaint(Color.WHITE); //kolor obszaru kreœlenia
		curve.getXYPlot().setRangeGridlinePaint(Color.black); //kolor poziomych linii
		curve.getXYPlot().setDomainGridlinePaint(Color.black); 
		return curve;
	}
}
