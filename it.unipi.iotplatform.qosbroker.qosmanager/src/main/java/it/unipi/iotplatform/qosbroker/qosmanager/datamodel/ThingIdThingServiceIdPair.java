package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "ThingIdThingServiceIdPair")
@XmlAccessorType(XmlAccessType.FIELD)
/* class to store the id of a thing that satisfy a service
 * and the id of the associated ThingService.
 * This class is used to build the Mapping Service-EquivalentThings
 * whose values have to be compute every time (f_ij, u_ij) */
public class ThingIdThingServiceIdPair {
	
	@XmlElement(name = "thingId")
	@JsonProperty("thingId")
	private Integer thingId;
	
	@XmlElement(name = "thingServiceId")
	@JsonProperty("thingServiceId")
	private Integer thingServiceId;
	
	public Integer getThingId() {
		return thingId;
	}
	public void setThingId(Integer thingId) {
		this.thingId = thingId;
	}
	public Integer getThingServiceId() {
		return thingServiceId;
	}
	public void setThingServiceId(Integer thingServiceId) {
		this.thingServiceId = thingServiceId;
	}
}
