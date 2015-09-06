package it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "serviceAgreementRequest")
public class ServiceAgreementRequest extends ServiceAgreementStructure{
	
	@XmlElement(name = "serviceDefinitionEntityType")
	private ArrayList<ServiceDefinition> serviceDefList;

}
