package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

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
public class RequestResults extends DataStructure{

	@XmlElement(name = "transactionId")
	@JsonProperty("transactionId")
	private String transactionId;
	
	@XmlElement(name = "request")
	@JsonProperty("request")
	private Request request;
	
	@XmlElement(name = "equivalentThingsMappings")
	@JsonProperty("equivalentThingsMappings")
	private EquivalentThingsMappings equivalentThingsMappings;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public EquivalentThingsMappings getEquivalentThingsMappings() {
		return equivalentThingsMappings;
	}

	public void setEquivalentThingsMappings(
			EquivalentThingsMappings equivalentThingsMappings) {
		this.equivalentThingsMappings = equivalentThingsMappings;
	}
}
