package it.unipi.iotplatform.qosbroker.api.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;

/* class that represents an xml instance of a
 * response to a service agreement request */
@XmlRootElement(name = "serviceResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceAgreementResponse extends DataStructure{
	
	@XmlElement(name = "serviceID")
	private String serviceID;
	
	@XmlElement(name = "errorCode")
	private StatusCode errorCode;

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public StatusCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(StatusCode errorCode) {
		this.errorCode = errorCode;
	}

}
