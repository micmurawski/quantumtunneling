package secondpackage;
import javafx.scene.chart.LineChart;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;
 
 
public class LineChartSample{
	
	NumberAxis xAxis;
	NumberAxis yAxis;
	Scene scene;
	XYChart.Series wave;
	XYChart.Series potential;
	LineChart<Number,Number> lineChart;
	
	
    static final double dx=0.1;
    static final double dt=0.1;
    
    LineChartSample(){
    	xAxis = new NumberAxis();
        yAxis = new NumberAxis();
    	lineChart = new LineChart<Number,Number>(xAxis,yAxis);
    	xAxis.setLabel("X");
    	wave = new XYChart.Series();
    	potential = new XYChart.Series();
    	lineChart.getData().add(wave);
    	lineChart.getData().add(potential);
    	scene  = new Scene(lineChart,800,600);
    	
    	
    	
    }
    
    void setWave(double[] f){
    	int N=f.length;
    	lineChart.getData().remove(wave);
    	wave = new XYChart.Series();
    	wave.setName("funkcja falowa");
    	for(int ii=0;ii<N+1;ii++)
    	wave.getData().add(new XYChart.Data(ii*dx, f[ii] ));
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(wave);
    }
    
    void setPotential(double[] f){
    	int N=f.length;
    	lineChart.getData().remove(potential);
    	potential = new XYChart.Series();
    	potential.setName("funkcja falowa");
    	for(int ii=0;ii<N+1;ii++)
    	potential.getData().add(new XYChart.Data(ii*dx, f[ii] ));
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(potential);
    }
 
  
        
    }