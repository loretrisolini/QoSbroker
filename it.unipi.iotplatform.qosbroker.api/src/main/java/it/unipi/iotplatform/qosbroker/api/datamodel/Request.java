package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.Restriction;

/* class to represent the request
   that is sent through the ServiceAgreementRequest */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Request extends DataStructure{
	
	@XmlElement(name = "operationType")
	@JsonProperty("operationType")
	private String opType;
	
	@XmlElement(name = "qosRequirements")
	@JsonProperty("qosRequirements")
	private QoSreq qosRequirements;
	
	@XmlElementWrapper(name = "requiredServicesNameList")
	@XmlElement(name = "serviceName")
	@JsonProperty("requiredServicesName")
	private List<String> requiredServicesNameList;

	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public QoSreq getQosRequirements() {
		return qosRequirements;
	}
	public void setQosRequirements(QoSreq qosRequirements) {
		this.qosRequirements = qosRequirements;
	}
	public List<String> getRequiredServicesNameList() {
		return requiredServicesNameList;
	}
	public void setRequiredServicesNameList(List<String> requiredServicesNameList) {
		this.requiredServicesNameList = requiredServicesNameList;
	}
	
}
