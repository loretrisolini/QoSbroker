package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "serviceEquivalentThingsMapping")
@XmlAccessorType(XmlAccessType.FIELD)
/* class that contain the Map<reqServName, List<DevId>> */
public class ServiceEquivalentThingsMapping extends DataStructure{

	@XmlElementWrapper(name = "serviceEquivalentThingsMap")
	@XmlElement(name = "serviceEquivalentThingsEntry")
	@JsonProperty("serviceEquivalentThings")
	//Map<reqServName, List<DevId>>
	HashMap<String, EquivalentThings> serviceEquivalentThings;

	public HashMap<String, EquivalentThings> getServiceEquivalentThings() {
		return serviceEquivalentThings;
	}

	public void setServiceEquivalentThings(
			HashMap<String, EquivalentThings> serviceEquivalentThings) {
		this.serviceEquivalentThings = serviceEquivalentThings;
	}
}
