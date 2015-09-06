package it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;

@XmlRootElement(name = "serviceResponse")
public class ServiceAgreementResponse extends ServiceAgreementStructure{
	
	@XmlElement(name = "serviceID")
	private String serviceID = null;
	
	@XmlElement(name = "errorCode")
	private StatusCode errorCode = null;

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
