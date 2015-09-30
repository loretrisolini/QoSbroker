package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/* class that represents a service on a thing with the
 * associated info about the execution of the service
 * (latency, energy_cost) */
@XmlRootElement(name = "thingService")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThingService extends DataStructure{

	@XmlElement(name = "serviceName")
	@JsonProperty("serviceName")
	private String serviceName;
	
	@XmlElement(name = "thingServiceFeatures")
	@JsonProperty("thingServiceFeatures")
	private ThingServiceFeatures thingServFeatures;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public ThingServiceFeatures getThingServFeatures() {
		return thingServFeatures;
	}
	public void setThingServFeatures(ThingServiceFeatures thingServFeatures) {
		this.thingServFeatures = thingServFeatures;
	}
}
