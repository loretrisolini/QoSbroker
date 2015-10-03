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
/* class that enclose all the maps about the equivalent
 * things */
public class EquivalentThingsInfoContainer extends DataStructure{

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
	private HashMap<Integer, EquivalentThingsList> eqThingsListPerService;

	public HashMap<Integer, Thing> getThingsMap() {
		return thingsMap;
	}

	public void setThingsMap(HashMap<Integer, Thing> thingsMap) {
		this.thingsMap = thingsMap;
	}

	public HashMap<Integer, EquivalentThingsList> getEqThingsListPerService() {
		return eqThingsListPerService;
	}

	public void setEqThingsListPerService(
			HashMap<Integer, EquivalentThingsList> eqThingsListPerService) {
		this.eqThingsListPerService = eqThingsListPerService;
	} 
}
