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
	private int maxResponseTime;
	
	@XmlElement(name = "maxRateRequest")
	@JsonProperty("maxRateRequest")
	private int maxRateRequest;

	public QoSreq() {

		this.maxResponseTime = 0;
		this.maxRateRequest = 0;
	}

	public int getMaxResponseTime() {
		return maxResponseTime;
	}

	public void setMaxResponseTime(int maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}

	public int getMaxRateRequest() {
		return maxRateRequest;
	}

	public void setMaxRateRequest(int maxRateRequest) {
		this.maxRateRequest = maxRateRequest;
	}
	

	
}
