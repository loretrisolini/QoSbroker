package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/* class that represent a request service from the
 * ServiceAgreementRequest */
@XmlRootElement(name = "service")
@XmlAccessorType(XmlAccessType.FIELD)
public class Service {

	@XmlElement(name = "serviceId")
	@JsonProperty("serviceId")
	private int servId;
	
	@XmlElement(name = "service")
	@JsonProperty("service")
	//Service = attr.name
	private String service;

	public int getServId() {
		return servId;
	}

	public void setServId(int servId) {
		this.servId = servId;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
}
