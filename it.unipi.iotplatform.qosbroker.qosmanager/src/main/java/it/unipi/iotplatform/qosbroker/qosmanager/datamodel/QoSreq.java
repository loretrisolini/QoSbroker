package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.io.Serializable;

/*
<scopeType>QoS</scopeType>
<scopeValue>
	 <maxResponseTime>15</maxResponseTime>
	 <maxRateRequest>15</maxRateRequest>
</scopeValue>
*/

/* class to store qos requirements for a request */
public class QoSreq implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private double maxResponseTime;
	
	private double maxRateRequest;

	public QoSreq() {
		super();
		this.maxResponseTime = 0.0;
		this.maxRateRequest = 0.0;
	}
	
	public double getMaxResponseTime() {
		return maxResponseTime;
	}

	public void setMaxResponseTime(double maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}

	public double getMaxRateRequest() {
		return maxRateRequest;
	}

	public void setMaxRateRequest(double maxRateRequest) {
		this.maxRateRequest = maxRateRequest;
	}

	@Override
	public String toString() {
		return "QoSreq [maxResponseTime=" + maxResponseTime
				+ ", maxRateRequest=" + maxRateRequest
				+ ", getMaxResponseTime()=" + getMaxResponseTime()
				+ ", getMaxRateRequest()=" + getMaxRateRequest()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
