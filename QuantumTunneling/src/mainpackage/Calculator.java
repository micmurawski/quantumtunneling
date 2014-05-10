package mainpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Calculator implements Runnable{
	
	TasksManager threads;
	
	Calculator(TasksManager threads){
		this.threads=threads;
		new Thread(this, "Calculator").start();
		
	}
	
	
	public void run(){
		while(true){
		    	threads.calculate();
		}
		
		
		
	}

}
