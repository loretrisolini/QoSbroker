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
	private double latency;
	
	@XmlElement(name = "energyCost")
	@JsonProperty("energyCost")
	//c_ij
	private double energyCost;

	public final static String LATENCY = "latency";
	public final static String ENERGY_COST = "energyCost";
	
	public double getLatency() {
		return latency;
	}

	public void setLatency(double latency) {
		this.latency = latency;
	}

	public double getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(double energyCost) {
		this.energyCost = energyCost;
	}
}
