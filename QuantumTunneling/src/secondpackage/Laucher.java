package secondpackage;

import javafx.application.Application;
import javafx.stage.Stage;

public class Laucher extends Application{
	@Override 
	public void start(Stage stage) {
	LineChartSample wykres = new LineChartSample();
    stage.setTitle("Line Chart Sample");
    stage.setScene(wykres.scene);
    stage.show();

}
	
	  public static void main(String[] args) {
	    	
	        launch(args);
	    }
	}
