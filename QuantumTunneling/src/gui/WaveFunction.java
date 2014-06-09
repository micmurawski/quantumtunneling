package gui;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class WaveFunction{
	
	/*
	 * @author Michał Murawski
	 * @params
	 * @return
	 * 
	 * Class defines wave function as bunch of needed values and data types to describe and calculate evolution
	 * in time of wave. It consists all needed methods to get and calculate all necessery parameters.  
	 * 
	 */
	
	Complex[] waveFunction;	//table of wave values
	Complex[] beta;	//table of variables needed to calculations
	Complex[] U;	//table of variables needed to calculations (evolution operator)
	private final double[] potential;	//table of potential values
	Complex alpha,gamma,den,z;	//variables needed to calculations
	
	private double k;	//wave vector
	private double t;	//time
	private double m;	//mass
	private double x0;	//initial position
	private double L;	//length
	private double sigma;	//sigma (uncertainity)
	private double A; // helpful variable
	private double B;	//	helpful variable
	public static final double h_bar=0.4135667516; //Dirac constant
	public static double dt;	//time interval
	public static double dx;	//position interval
	private int N;	//number of intersections
	
	
/*
 * @author Michał Murawski
 * @param table(double)
 * @return WaveFunction
 * 
 * Constructor which construct initial shape of wave function and parameters needed to calculate wave evolution.
 * It take as argument table of value.
 * 
 */
	
	 public WaveFunction(double[] parameters){
		dx=0.05;
		dt=0.05;
		 //[energy,mass,length,x0]
		this.L=parameters[4];
		this.N=(int)(parameters[4]/dx);
		this.m=parameters[1];
		this.k=Math.sqrt(2*m*parameters[0]/(h_bar*h_bar));
		this.x0=parameters[3];
		this.sigma=parameters[2]*(h_bar*h_bar)/(2*m*parameters[0]);
		this.A=1/Math.sqrt(Math.sqrt(2*Math.PI*sigma*sigma));
		this.t=0;
		
		alpha=new Complex(0,-(h_bar*dt)/(4.0*m*dx*dx));
		gamma=new Complex(0,-(h_bar*dt)/(4.0*m*dx*dx));
		den=new Complex(0,0);
		waveFunction= new Complex[N+1];
		U= new Complex[N+1];
		beta = new Complex[N+1];
		potential = new double[N+1];
		
		//Wave function shape initialization
		for(int ii=0; ii<N+1;ii++){
			beta[ii]=(new Complex(0,0));
			potential[ii]=(0.0);
			U[ii]=(new Complex(0,0));
			
			B=A*Math.exp((double)(-((ii*dx)-x0)*((ii*dx)-x0))/(4.0*sigma*sigma));
			waveFunction[ii]=(new Complex(B*Math.cos(k*(ii*dx)),B*Math.sin(k*(ii*dx))));
			}
		
	}
	
	 /*
	  * @author Michał Murawski
	  * @param null
	  * @return Complex
	  * 
	  * Method returns value of wave function as complex number
	  */
	 Complex get(int i){
		return this.waveFunction[i];}
	 /*
	  * @author Michał Murawski
	  * @param null
	  * @return double
	  * Method returns value of probability density of wave function. 
	  */
	
	public double getProbabilityDensity(int i){
		return this.waveFunction[i].abs()*this.waveFunction[i].abs();}
	
	/*
	  * @author Michał Murawski
	  * @param null
	  * @return double
	  * Method returns value of potential. 
	  */
	public double getPotential(int i){
		return potential[i];}
	/*
	  * @author Michał Murawski
	  * @param null
	  * @return integer
	  * Method returns value of intersections. 
	  */
	public int getN(){
		return N;}
	
	/*
	  * @author Michał Murawski
	  * @param null
	  * @return double
	  * Method returns value of length. 
	  */
	
	public double getLength(){
		return L;
	}
	
	public double getTime(){
		return t;
	}
	
	/*
	  * @author Michał Murawski
	  * @param null
	  * @return double
	  * Method returns value of norm. 
	  */
	
	public double checkNorm(){
		 double s=0;
		 for(int jj=0; jj<N+1;jj++)
			 s+=this.getProbabilityDensity(jj)*dx;
		 return s;}
	
	public double integration(double beginning){
		double s=0;
		int n0=(int)(beginning/dx);
		 for(int jj=n0; jj<N+1;jj++)
			 s+=this.getProbabilityDensity(jj)*dx;
		 return s/checkNorm();}
	
	
	/*
	  * @author Michał Murawski
	  * @param double[]
	  * @return null
	  * Method sets potential barrier and define needed variables to calculations. 
	  */
	
	public void setPotential(double[] parameters){
		this.potential[0]=1e5;
		this.potential[N]=1e5;
		for(int ii=1;ii<this.N;ii++){
			if((ii*dx)>=parameters[1]){
				if(ii*dx>=parameters[1]+parameters[2]){
					this.potential[ii]=(0.0);
				}else{
					this.potential[ii]=parameters[0];
				}
				
				}else{
					this.potential[ii]=0.0;
				}

			}
		for(int jj=0;jj<this.N;jj++){
			beta[jj]=new Complex(1,0);
			beta[jj]=beta[jj].minus(alpha.times(2));
			beta[jj]=beta[jj].plus(new Complex(0,(potential[jj]*dt)/(2.0*h_bar)));
			}}
	
	
	public void setPotential2(double[] parameters){
		//[v0,x0,a]
		for(int ii=1;ii<this.N;ii++){
			potential[ii]=parameters[0]+0.5*(parameters[2]*((ii*dx)-parameters[1])*((ii*dx)-parameters[1]));
			}
		
		
		for(int jj=0;jj<this.N;jj++){
			beta[jj]=new Complex(1,0);
			beta[jj]=beta[jj].minus(alpha.times(2));
			beta[jj]=beta[jj].plus(new Complex(0,(potential[jj]*dt)/(2.0*h_bar)));
			}}
	
	
	/*
	  * @author Michał Murawski
	  * @param null
	  * @return null
	  * Method provides changes of wave for time interval. 
	  */
	
	
	 public void evolution(){
		 for(int ii=1;ii<N;ii++){
			 	z=(alpha.times(-1)).times(waveFunction[ii-1]);
	 			U[ii]=z.minus((beta[ii].minus(2.0)).times(waveFunction[ii]));
	 			U[ii]=U[ii].minus(gamma.times(waveFunction[ii+1]));
	 		}
	 		
	 		
	 		U[1]=U[1].minus(alpha.times(waveFunction[0]));
	 		U[N-1]=U[N-1].minus(gamma.times(waveFunction[N]));
	 		waveFunction[1]=U[1].divides(beta[1]);
	 		U[1]=gamma.divides(beta[1]);
	 		
	 		for(int jj=2;jj<N;jj++){
	 			den=beta[jj].minus((alpha.times(U[jj-1])));
	 			waveFunction[jj]=(U[jj].minus(alpha.times(waveFunction[jj-1]))).divides(den);
	 			U[jj]=gamma.divides(den);
	 		}
	 		
			for(int kk=N-1;kk>=1;kk--){
				waveFunction[kk]=waveFunction[kk].minus(waveFunction[kk+1].times(U[kk]));
			}
	 		t+=dt;
	 		}
	 
	 /*
	  * @author Michał Murawski
	  * @param null
	  * @return XYSeries
	  * Methods return wave and potential shape as XYSeries. 
	  */
	
	public XYSeries seriesWave(){
		XYSeries data= new XYSeries("Wave",false);
		for(int ii=0;ii<N+1;ii++)
			data.add(ii*dx,this.getProbabilityDensity(ii));
		return data;}
	
	public XYSeries seriesPotential(){
		XYSeries data= new XYSeries("Potential",false);
		for(int ii=0;ii<N+1;ii++)
			data.add(ii*dx,this.getPotential(ii)*0.05);
		return data;}
	
	public double[] getPotentialTable(){
		return potential;
	}
	
	
	
public static void main(String[] args){
	WaveFunction f=new WaveFunction(new double[]{5.5,3.72,100,15});
	f.setPotential(new double[]{10,50,5});
	//WaveFunction test =new WaveFunction(4, 1, 1, 10,100);
	//test.setPotential(50, 10, 10);
	//test.evolution();
	//System.out.println("beta"+test.beta);
	//System.out.println("gamma: "+test.gamma);
	//System.out.println("U: "+test.U);
	//test.evolution();
	//System.out.println("norm"+test.checkNorm());
	//System.out.println("U: "+test.U);
	//System.out.println(test.getProbabilityDensity(100)+" "+test.N+" "+test.gamma+" \n"+test.alpha);
	//for(int i=1;i<test.N;i++)
	//System.out.println(i+"..."+test.getProbabilityDensity(i));
}
	

}