package gui;

import java.awt.Color;
import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Klasa która przechowuje równanie funkcji falowej i wykonuje obliczenia.
*@author Michał Murawski
**/


public class WaveFunction{
	
	ArrayList<Complex> waveFunction;
	ArrayList<Complex> beta;
	ArrayList<Complex> U;
	private final ArrayList<Double> potential;
	Complex alpha;	//Potrzebne stałe do wykonania obliczeń.
	Complex gamma;
	Complex den;
	
	private double k;	//energia
	private double t;	//czas	
	private double m;	//masa	
	private double x0;	// poczatkowe polozenie paczki falowej
	private double  sigma;	//sigma
	private double A; // stala normaluzujaca do rownania paczki falowej
	private double L;	//długość fali
	private double B;	//pomocnicza stała	
	public static final double h_bar=1; //stała plancka
	public static final double dt=0.1;	// interwał czasowy
	public static final double dx=0.1;	// interwał przestrzenny	
	int N;	//liczba podziałek
	
	
	 public WaveFunction(double e0, double m, double x0, double sigma,double L){
		this.N=(int)(L/dx);
		this.k=Math.sqrt(2*m*e0/(h_bar*h_bar));
		this.t=0;
		this.m=m;
		this.x0=x0;
		this.sigma=sigma;
		this.L=L;
		this.A=1/Math.sqrt(Math.sqrt(2*Math.PI*sigma*sigma));
		
		
		alpha=new Complex(0,-(h_bar*dt)/(4*m*dx*dx));
		gamma=new Complex(0,-(h_bar*dt)/(4*m*dx*dx));
		den=new Complex(0,0);
		waveFunction= new ArrayList<Complex>();
		U= new ArrayList<Complex>();
		beta = new ArrayList<Complex>();
		potential = new ArrayList<Double>();
		
		for(int ii=0; ii<N+1;ii++){
			beta.add(new Complex(0,0));
			potential.add(0.0);
			U.add(new Complex(0,0));
			
			B=A*Math.exp((double)(-((ii*dx)-x0)*((ii*dx)-x0))/(4*sigma*sigma));
			waveFunction.add(new Complex(B*Math.cos(k),B*Math.sin(k)));
			}
		
		}
		
		
	 Complex get(int i){
		return this.waveFunction.get(i);
	}
	 
	
	public double getProbabilityDensity(int i){
		return this.waveFunction.get(i).abs()*this.waveFunction.get(i).abs();
	
	
	}
	
	
	public double getPotential(int i){
		return potential.get(i);
	}
	
	public int getN(){
		return N;
	}
	
	
	
	
	public void setPotential(double x0, double a, double v0){
		for(int ii=0;ii<this.N+1;ii++){
			if((ii*dx)>=x0){
				if(ii*dx>=x0+a){
					this.potential.set(ii,0.0);
				}else{
					this.potential.set(ii,v0);
				}
				
				}else{
					this.potential.set(ii,0.0);
				}

			}
		for(int jj=0;jj<this.N;jj++)
			beta.set(jj ,new Complex(1+(potential.get(jj)*dt/(2*h_bar)),(h_bar*dt)/(2*m*dx*dx)));
	}
	
	 public void evolution(){
		for(int ii=1;ii<N;ii++){
			U.set(ii,alpha.negate().multiply(waveFunction.get(ii-1)));
			U.set(ii,U.get(ii).add((beta.get(ii).negate().add(2)).multiply(waveFunction.get(ii))));
			U.set(ii,U.get(ii).subtract(gamma.multiply(waveFunction.get(ii+1))));
		}
		
		
		U.set(1,U.get(1).subtract(alpha.multiply(waveFunction.get(0))));
		U.set(N-1,U.get(N-1).subtract(gamma.multiply(waveFunction.get(N))));
		waveFunction.set(1, U.get(1).divide(beta.get(1)));
		
		for(int jj=2;jj<N;jj++){
			den=(beta.get(jj).subtract(alpha.multiply(U.get(jj-1))));
			waveFunction.set(jj,U.get(jj).subtract(alpha.multiply(waveFunction.get(jj-1).divide(den))));
			U.set(jj, gamma.divide(den));
		}
		for(int kk=N;kk<1;kk--){
			waveFunction.set(kk, waveFunction.get(kk).subtract(waveFunction.get(kk+1).multiply(U.get(kk))));
		}
		t+=dt;
		
		
		
	}
	 
	 public double checkNorm(){
		 double s=0;
		 for(int jj=0; jj<N+1;jj++)
			 s+=this.getProbabilityDensity(jj)*dx;
		 return s;
	 }
	
	
	void setEnergy(double e0){
		this.k=Math.sqrt(2*this.m*e0)/h_bar;
	}

	double getTime(){
		return t;
	}
	
	XYSeries seriesWave(){
		XYSeries data= new XYSeries("Wave");
		for(int ii=0;ii<waveFunction.size();ii++)
			data.add(ii*dx,this.getProbabilityDensity(ii));
		return data;
	}
	XYSeries seriesPotential(){
		XYSeries data= new XYSeries("Potential");
		for(int ii=0;ii<potential.size();ii++)
			data.add(ii*dx,this.getPotential(ii));
		return data;
	}
	
	JFreeChart Make(XYSeries S)
	{
		XYSeriesCollection collection = new XYSeriesCollection();
			collection.addSeries(S);
		JFreeChart curve = ChartFactory.createScatterPlot("seria", null, null, collection, PlotOrientation.VERTICAL, false, true, false);
		//curve.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
		curve.getXYPlot().setBackgroundPaint(Color.WHITE); //kolor obszaru kre�lenia
		curve.getXYPlot().setRangeGridlinePaint(Color.black); //kolor poziomych linii
		curve.getXYPlot().setDomainGridlinePaint(Color.black); //kolor pionowych linii
		curve.getXYPlot().getDomainAxis().setRange(0.0,100);
		curve.getXYPlot().getRangeAxis().setRange(0.0,1);
		return curve;
	}
	
	
	
public static void main(String[] args){
	WaveFunction test = new WaveFunction(10,10,50,10,100);
	test.setPotential(50, 10, 10);
	test.evolution();
	System.out.println(test.checkNorm());
	System.out.println(test.getProbabilityDensity(100)+" "+test.N+" "+test.gamma+" \n"+test.alpha);
	for(int i=10;i<100;i++)
	System.out.println(test.U.get(i));
}
	

}