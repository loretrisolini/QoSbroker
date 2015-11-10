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
	

}
