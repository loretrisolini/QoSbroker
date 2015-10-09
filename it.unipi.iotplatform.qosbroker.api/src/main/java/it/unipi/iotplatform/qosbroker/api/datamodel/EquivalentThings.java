package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "equivalentThings")
@XmlAccessorType(XmlAccessType.FIELD)
/* class that contain the List<DevId> */
public class EquivalentThings extends DataStructure{

	@XmlElementWrapper(name = "equivalentThingsList")
	@XmlElement(name = "equivalentThing")
	@JsonProperty("equivalentThings")
	private List<String> eqThings;

	public EquivalentThings(){
		eqThings  = new ArrayList<>();
	}
	
	public void addEqThing(String devId){
		eqThings.add(devId);
	}
	
	public List<String> getEqThings() {
		return eqThings;
	}

	public void setEqThings(List<String> eqThings) {
		this.eqThings = eqThings;
	}
}
