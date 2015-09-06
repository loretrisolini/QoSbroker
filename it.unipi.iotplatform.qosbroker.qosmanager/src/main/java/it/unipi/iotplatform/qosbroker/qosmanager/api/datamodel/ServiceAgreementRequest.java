package it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "serviceAgreementRequest")
public class ServiceAgreementRequest extends ServiceRequestStructure{
	
	@XmlElement(name = "serviceDefinitionEntityType")
	private ArrayList<ServiceDefinitionEntityType> serviceDefEntTypeList;
	
	@XmlElement(name = "serviceDefinitionEntityID")
	private ArrayList<ServiceDefinitionEntityType> serviceDefEntIDList;

	public ArrayList<ServiceDefinitionEntityType> getServiceDefEntTypeList() {
		return serviceDefEntTypeList;
	}

	public void setServiceDefEntTypeList(
			ArrayList<ServiceDefinitionEntityType> serviceDefEntTypeList) {
		this.serviceDefEntTypeList = serviceDefEntTypeList;
	}

	public ArrayList<ServiceDefinitionEntityType> getServiceDefEntIDList() {
		return serviceDefEntIDList;
	}

	public void setServiceDefEntIDList(
			ArrayList<ServiceDefinitionEntityType> serviceDefEntIDList) {
		this.serviceDefEntIDList = serviceDefEntIDList;
	}

}
