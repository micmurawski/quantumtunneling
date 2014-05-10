package pl.edu.pw.fizyka.pojava.murlip;

import org.apache.commons.math3.complex.Complex;

public class Test {
	Complex a;
	Complex b;
	Complex c;
	Test(){
		a= new Complex(1,1);
		b= new Complex(1,-1);
		c = new Complex(0,0);
	}
	public static void main(String[] args) {
		Test t=new Test();
		t.c=t.a.multiply(t.b);
		System.out.println(t.c+" "+t.a);
		t.a=t.a.multiply(t.b);
		System.out.println(t.a);

	}

}
