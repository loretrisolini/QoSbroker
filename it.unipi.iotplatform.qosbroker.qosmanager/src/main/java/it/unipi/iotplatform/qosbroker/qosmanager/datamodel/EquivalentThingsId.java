package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
/* class that enclose the list of thingId_thingServId
 * pair that is for each service Id there is a thing
 * that offer that service through a thingService
 * with that id */
public class EquivalentThingsId extends DataStructure{

	@XmlElementWrapper(name = "equivalentThingsIdList")
	@XmlElement(name = "equivalentThingsId")
	@JsonProperty("equivalentThingsId")
	private List<ThingIdThingServiceIdPair> equivalentThingsId;

	public EquivalentThingsId(){
		equivalentThingsId = new ArrayList<>();
	}
	
	public List<ThingIdThingServiceIdPair> getEquivalentThingsId() {
		return equivalentThingsId;
	}

	public void setEquivalentThingsId(
			List<ThingIdThingServiceIdPair> equivalentThingsId) {
		this.equivalentThingsId = equivalentThingsId;
	}
}
