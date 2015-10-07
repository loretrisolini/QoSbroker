package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "ThingsInfo")
@XmlAccessorType(XmlAccessType.FIELD)
/* class that contain the Map<DevId, Thing> */
public class ThingsInfo extends DataStructure{

	@XmlElementWrapper(name = "thingInfoList")
	@XmlElement(name = "thingInfo")
	@JsonProperty("thingsInfo")
	//Map<DevId, <batt, Map<ServName, <c_ij, t_ij>>>>
	private HashMap<String, Thing> thingsInfo;

	public HashMap<String, Thing> getThingInfoList() {
		return thingsInfo;
	}

	public void setThingInfoList(HashMap<String, Thing> thingsInfo) {
		this.thingsInfo = thingsInfo;
	}
}
