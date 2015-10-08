package it.unipi.iotplatform.qosbroker.api.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/* class that represents the execution features of a service
 * on a thing */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceFeatures extends DataStructure{

	@XmlElement(name = "latency")
	@JsonProperty("latency")
	//t_ij
	private Double latency;
	
	@XmlElement(name = "energyCost")
	@JsonProperty("energyCost")
	//c_ij
	private Double energyCost;

	@XmlElement(name = "accuracy")
	@JsonProperty("accuracy")
	private Double accuracy;
	
	public final static String LATENCY = "latency";
	public final static String ENERGY_COST = "energy_cost";
	public final static String ACCURACY = "accuracy";
	
	public Double getLatency() {
		return latency;
	}

	public void setLatency(Double latency) {
		this.latency = latency;
	}

	public Double getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(Double energyCost) {
		this.energyCost = energyCost;
	}

	public Double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Double accuracy) {
		this.accuracy = accuracy;
	}
}
