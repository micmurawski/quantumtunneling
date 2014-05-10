package mainpackage;

public class Demo {
	
	public static void main(String[] args){
		WaveFunction f = new WaveFunction(1,1,0,1,100);
		f.setPotential(50, 10, 10);
		TasksManager threads= new TasksManager(f);
		new Calculator(threads);
		new Displayer(threads);
	}

}
