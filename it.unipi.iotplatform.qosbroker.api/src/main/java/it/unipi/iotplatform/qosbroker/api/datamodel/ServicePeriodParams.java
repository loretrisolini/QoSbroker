package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.ArrayList;

public class ServicePeriodParams {

	//period of the service request
	private Double period;
	
	//period Coefficient h/p_j
	private Integer nj;

	public Double getPeriod() {
		return period;
	}

	public void setPeriod(Double period) {
		this.period = period;
	}

	public Integer getNj() {
		return nj;
	}

	public void setNj(Integer nj) {
		this.nj = nj;
	} 
	
	public static long getHyperperiod(ArrayList<Double> periods){
	    long result = (long) Math.ceil(periods.get(0));
	    for(int i = 1; i < periods.size(); i++) result = lcm(result, (long) Math.ceil(periods.get(i)));
	    return result;
	}
	
	private static long gcd(long a, long b){
	    while (b > 0)
	    {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	private static long lcm(long a, long b){
	    return a * (b / gcd(a, b));
	}
}
