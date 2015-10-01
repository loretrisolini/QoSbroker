package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/* class that represents a Thing (associated 
 * to a ContextRegistrationResponse element) */
@XmlRootElement(name = "thing")
@XmlAccessorType(XmlAccessType.FIELD)
public class Thing extends DataStructure{
	
	//id (in the entityId Stricture) of the contRegResp
	//associated to the thing and used to create
	//the allocation schema
	@XmlElement(name = "contexRegRespId")
	@JsonProperty("contexRegRespId")
	private String contextEntityId;
	
	@XmlElement(name = "batteryLevel")
	@JsonProperty("batteryLevel")
	private Integer batteryLevel;

	@XmlElementWrapper(name = "thingServiceList")
	@XmlElement(name = "thingService")
	@JsonProperty("thingServices")
	//Map<ThingServiceId, ThingServiceFeatures>
	private HashMap<Integer, ThingService> thingServices;

	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public HashMap<Integer, ThingService> getThingServices() {
		return thingServices;
	}

	public void setThingServices(HashMap<Integer, ThingService> thingServices) {
		this.thingServices = thingServices;
	}

	public String getContextEntityId() {
		return contextEntityId;
	}

	public void setContextEntityId(String contextEntityId) {
		this.contextEntityId = contextEntityId;
	}
	
}
