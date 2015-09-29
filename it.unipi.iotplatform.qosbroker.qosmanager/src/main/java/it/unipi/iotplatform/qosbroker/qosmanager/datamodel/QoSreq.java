package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/*
<scopeType>QoS</scopeType>
<scopeValue>
	 <maxResponseTime>15</maxResponseTime>
	 <maxRateRequest>15</maxRateRequest>
</scopeValue>
*/

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
/* class to store qos requirements for a request */
public class QoSreq extends DataStructure {
	
	@XmlElement(name = "maxResponseTime")
	@JsonProperty("maxResponseTime")
	private double maxResponseTime;
	
	@XmlElement(name = "maxRateRequest")
	@JsonProperty("maxRateRequest")
	private double maxRateRequest;

	public QoSreq() {

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
	
}
