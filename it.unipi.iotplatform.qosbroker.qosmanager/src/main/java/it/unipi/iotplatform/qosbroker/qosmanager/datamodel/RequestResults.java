package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "requestResults")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestResults extends DataStructure{

	@XmlElement(name = "transactionId")
	@JsonProperty("transactionId")
	private String transactionId;
	
	@XmlElement(name = "request")
	@JsonProperty("request")
	private Request request;
	
	@XmlElementWrapper(name = "thingsList")
	@XmlElement(name = "thing")
	@JsonProperty("things")
	//Map<ThingId, Thing>
	private HashMap<Integer, Thing> thingsMap;

	public HashMap<Integer, Thing> getThingsMap() {
		return thingsMap;
	}

	public void setThingsMap(HashMap<Integer, Thing> thingsMap) {
		this.thingsMap = thingsMap;
	}

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
}
