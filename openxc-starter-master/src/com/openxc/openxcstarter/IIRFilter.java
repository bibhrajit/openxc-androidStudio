package com.openxc.openxcstarter;

public class IIRFilter {
	
	// instance variables
	
	private double x;
	private double fc;
	
	// constructors
	
	public IIRFilter(double filter_const, double x0) {
		x = x0;
		fc = filter_const;
	}
	
	public IIRFilter(double filter_const) {
		x = 0.0d;
		fc = filter_const;
	}
	
	public double update(double u) {
		x = fc * x  + (1.0d - fc) * u;
		return x;
	}
	
	public double getState() {
		return x;
	}

}
