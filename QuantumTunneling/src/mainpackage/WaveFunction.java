package mainpackage;

import java.util.ArrayList;

import org.apache.commons.math3.complex.Complex;

/**
 * Klasa która przechowuje równanie funkcji falowej i wykonuje obliczenia.
*@author Michał Murawski
**/


public class WaveFunction{
	
	ArrayList<Complex> waveFunction;
	ArrayList<Complex> beta;
	ArrayList<Complex> U;
	ArrayList<Double> potential;
	Complex alpha;	//Potrzebne stałe do wykonania obliczeń.
	Complex gamma;
	Complex den;
	
	double k;	//wektor falowy
	double t;	//czas	
	double m;	//masa	
	double x0;	// poczatkowe polozenie paczki falowej
	double sigma;	//sigma
	double A; // stala normaluzujaca do rownania paczki falowej
	double L;	//długość fali
	double B;	//pomocnicza stała	
	public static final double h_bar=1; //stała plancka
	public static final double dt=0.1;	// interwał czasowy
	public static final double dx=0.1;	// interwał przestrzenny	
	int N;	//liczba podziałek
	
	
	 public WaveFunction(double k, double m, double x0, double sigma,double L){
		this.N=(int)(L/dx);
		this.k=k;
		this.t=0;
		this.m=m;
		this.x0=x0;
		this.sigma=sigma;
		this.L=L;
		this.A=1/(Math.sqrt(Math.sqrt(4*Math.PI))*Math.sqrt(sigma));
		
		
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
			
			B=A*Math.exp((double)(-((ii*dx)-x0)*((ii*dx)-x0))/(2*sigma*sigma));
			waveFunction.add(new Complex(B*Math.cos(k),B*Math.sin(k)));
			U.set(ii,new Complex(0,0));
			}
		
		}
		
		
	ArrayList<Complex> get(){
		return this.waveFunction;
	}
	
	public ArrayList<Double> getProbabilityDensity(){
		ArrayList<Double> density = new ArrayList<Double>(); 
		for(int ii=0;ii<N+1;ii++){
			density.add(ii,this.waveFunction.get(ii).abs());
		}
		return density;
	}
	
	
	public ArrayList<Double> getPotential(){
		return potential;
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
		//for(int kk=N;kk<1;kk--){
			//waveFunction.set(kk, waveFunction.get(kk).subtract(waveFunction.get(kk+1).multiply(U.get(kk))));
		//}
		t+=dt;
		
		
		
	}
	
	
	void setEnergy(double e0){
		this.k=Math.sqrt(2*this.m*e0)/h_bar;
	}

	double getTime(){
		return t;
	}
	
public static void main(String[] args){
	WaveFunction test = new WaveFunction(10,10,10,10,10);
	test.setPotential(50, 10, 10);
	test.evolution();
	System.out.println(test.getProbabilityDensity().get(100)+" "+test.N+" "+test.gamma+" \n"+test.alpha);
	for(int i=10;i<100;i++)
	System.out.println(test.U.get(i));
}
	

}