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
public class Service extends DataStructure{

	@XmlElement(name = "serviceId")
	@JsonProperty("serviceId")
	private int servId;
	
	@XmlElement(name = "serviceName")
	@JsonProperty("serviceName")
	//Service = attr.name
	private String requestedServiceName;

	public String getRequestedServiceName() {
		return requestedServiceName;
	}

	public void setRequestedServiceName(String requestedServiceName) {
		this.requestedServiceName = requestedServiceName;
	}

	public int getServId() {
		return servId;
	}

	public void setServId(int servId) {
		this.servId = servId;
	}

}
