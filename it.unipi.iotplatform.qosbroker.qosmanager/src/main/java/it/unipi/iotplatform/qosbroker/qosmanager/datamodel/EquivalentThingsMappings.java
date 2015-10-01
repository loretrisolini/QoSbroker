package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EquivalentThingsMappings extends DataStructure{

	@XmlElementWrapper(name = "thingsList")
	@XmlElement(name = "thing")
	@JsonProperty("things")
	//Map<ThingId, Thing>
	private HashMap<Integer, Thing> thingsMap; 
	
	@XmlElementWrapper(name = "eqThingsListPerServiceList")
	@XmlElement(name = "eqThingsListPerService")
	@JsonProperty("eqThingsListPerService")
	//used to re-compute the mapping Service-EquivalentThings
	//Map<ServId, List<ThingId, ThingServiceId>>
	private HashMap<Integer, EquivalentThingsId> eqThingsListPerService;

	public HashMap<Integer, Thing> getThingsMap() {
		return thingsMap;
	}

	public void setThingsMap(HashMap<Integer, Thing> thingsMap) {
		this.thingsMap = thingsMap;
	}

	public HashMap<Integer, EquivalentThingsId> getEqThingsListPerService() {
		return eqThingsListPerService;
	}

	public void setEqThingsListPerService(
			HashMap<Integer, EquivalentThingsId> eqThingsListPerService) {
		this.eqThingsListPerService = eqThingsListPerService;
	} 
}
