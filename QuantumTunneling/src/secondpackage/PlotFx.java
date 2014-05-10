package secondpackage;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class PlotFx {
	NumberAxis xAxis;
    NumberAxis yAxis;
    LineChart<Number,Number> lineChart;
    XYChart.Series wave;
    XYChart.Series potential;

    
    PlotFx(){
    	NumberAxis xAxis= new NumberAxis();
        NumberAxis yAxis= new NumberAxis();
        XYChart.Series wave = new XYChart.Series();
        XYChart.Series potential = new XYChart.Series();
    }
    void setWave(double[] f, double dx){
    	this.wave=null;
    	this.lineChart.getData().remove(wave);
    	XYChart.Series wave = new XYChart.Series();
    	for(int i=0;i<f.length;i++)
          this.wave.getData().add(new XYChart.Data(i*dx,f[i]));
    	this.lineChart.getData().add(wave);
    	
    }
    
    void setPotential(double[] f, double dx){
    	this.potential=null;
    	this.lineChart.getData().remove(potential);
    	XYChart.Series potential = new XYChart.Series();
    	for(int i=0;i<f.length;i++)
            this.potential.getData().add(new XYChart.Data(i*dx,f[i]));
    	this.lineChart.getData().add(potential);
    }

}
