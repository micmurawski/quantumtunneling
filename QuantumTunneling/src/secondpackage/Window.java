/* --------------------------
 * DefaultXYDatasetDemo2.java
 * --------------------------
 * (C) Copyright 2006, by Object Refinery Limited.
 *
 */

package secondpackage;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing...
 */
public class Window extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title  the frame title.
     */
    public Window(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
    
    private static JFreeChart createChart(XYDataset dataset) {
        return ChartFactory.createScatterPlot("DefaultXYDatasetDemo1", "X", 
                "Y", dataset, PlotOrientation.VERTICAL, true, false, false);
    }
    
    /**
     * Creates a sample dataset.
     */
    private static XYDataset createDataset() {
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[] x = new double[1000];
        double[] y = new double[1000];
        for (int i = 0; i < 1000; i++) {
            x[i] = Math.random() + 1.0;
            y[i] = Math.random() + 1.0;
        }
        double[][] data = new double[][] {x, y};
        dataset.addSeries("Series 1", data);
        return dataset;
    }
    
    /**
     * Creates a panel for the demo.
     *  
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()));
    }
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
    	Window demo = new Window(
                "DefautlXYDataset Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}