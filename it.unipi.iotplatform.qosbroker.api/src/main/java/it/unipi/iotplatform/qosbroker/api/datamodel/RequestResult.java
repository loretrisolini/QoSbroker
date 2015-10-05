package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/* class used to store the results of a discovery and a query
 * based on the info in the request object */
@XmlRootElement(name = "requestResults")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestResult extends DataStructure{
	
	@XmlElement(name = "request")
	@JsonProperty("request")
	private Request request;
	
	@XmlElement(name = "equivalentThingsMappings")
	@JsonProperty("equivalentThingsMappings")
	private EquivalentThingsInfoContainer equivalentThingsMappings;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public EquivalentThingsInfoContainer getEquivalentThingsMappings() {
		return equivalentThingsMappings;
	}

	public void setEquivalentThingsMappings(
			EquivalentThingsInfoContainer equivalentThingsMappings) {
		this.equivalentThingsMappings = equivalentThingsMappings;
	}
}
