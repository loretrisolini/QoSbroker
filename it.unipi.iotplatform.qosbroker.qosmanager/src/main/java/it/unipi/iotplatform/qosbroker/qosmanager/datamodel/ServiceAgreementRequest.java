package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "serviceAgreementRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceAgreementRequest extends DataStructure{
	
	@XmlElement(name = "serviceDefinition")
	//in our implementation we consider always one service definition
	private ArrayList<ServiceDefinition> serviceDefinitionList;

	public ArrayList<ServiceDefinition> getServiceDefinitionList() {
		return serviceDefinitionList;
	}

	public void setServiceDefinitionList(
			ArrayList<ServiceDefinition> serviceDefinitionList) {
		this.serviceDefinitionList = serviceDefinitionList;
	}

}
