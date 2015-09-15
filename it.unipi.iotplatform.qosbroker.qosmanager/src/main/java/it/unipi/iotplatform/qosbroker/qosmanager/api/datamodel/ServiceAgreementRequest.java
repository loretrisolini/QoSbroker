package it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "serviceAgreementRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceAgreementRequest extends ServiceAgreementStructure{
	
	@XmlElement(name = "serviceDefinition")
	private ServiceDefinition serviceDefinition;

	public ServiceDefinition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(ServiceDefinition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

}
