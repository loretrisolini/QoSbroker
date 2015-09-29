package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "thing")
@XmlAccessorType(XmlAccessType.FIELD)
public class Thing extends DataStructure{
	
	@XmlElement(name = "batteryLevel")
	@JsonProperty("batteryLevel")
	private double batteryLevel;

	@XmlElementWrapper(name = "thingServiceList")
	@XmlElement(name = "thingService")
	@JsonProperty("thingServices")
	//Map<ThingServiceId, ThingServiceFeatures>
	private HashMap<Integer, ThingService> thingServices;

	public double getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(double batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public HashMap<Integer, ThingService> getThingServices() {
		return thingServices;
	}

	public void setThingServices(HashMap<Integer, ThingService> thingServices) {
		this.thingServices = thingServices;
	}
	
}
