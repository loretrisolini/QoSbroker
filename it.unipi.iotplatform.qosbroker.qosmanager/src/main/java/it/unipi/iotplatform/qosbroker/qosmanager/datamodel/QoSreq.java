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
	private Double maxResponseTime;
	
	@XmlElement(name = "maxRateRequest")
	@JsonProperty("maxRateRequest")
	private Double maxRateRequest;

	public QoSreq() {

		this.maxResponseTime = 0.0;
		this.maxRateRequest = 0.0;
	}

	public Double getMaxResponseTime() {
		return maxResponseTime;
	}

	public void setMaxResponseTime(Double maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}

	public Double getMaxRateRequest() {
		return maxRateRequest;
	}

	public void setMaxRateRequest(Double maxRateRequest) {
		this.maxRateRequest = maxRateRequest;
	}
	
}
