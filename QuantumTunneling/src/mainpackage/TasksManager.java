package mainpackage;

import javax.swing.Timer;


public class TasksManager {
	
	Timer t1;
	WaveFunction f;
	boolean newData = false;
	
	TasksManager(WaveFunction f){
		this.f=f;
		       
		
	}
	
	synchronized void calculate(){
		if(newData)
			try{
				wait();
			}catch(InterruptedException e){
				System.out.println("error");
			}
				
				
				newData= true;
				f.evolution();
				System.out.println("Obliczono"+"\n");
				notify();
				
				
			}

	
	synchronized void display(){
		
		if(!newData)
			try{
				wait();
			}catch(InterruptedException e2){
				System.out.println("error");
				
			}
		
		    	System.out.println("Time: "+f.getTime()+"\n");
				newData=false;
				notify();
		
	}

}
