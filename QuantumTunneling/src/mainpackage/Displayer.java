package mainpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Displayer implements Runnable {
	
	WaveFunction f;
	TasksManager threads;
	
	Displayer(TasksManager threads){
		this.threads=threads;
		new Thread(this,"Displayer").start();
	}
	
	public void run(){
		new Timer(500, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	threads.display();
				}
				}).start();
		
		
	}
	

}
