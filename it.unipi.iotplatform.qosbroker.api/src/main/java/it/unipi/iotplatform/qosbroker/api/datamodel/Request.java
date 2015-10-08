package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;

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
	private QoSscopeValue qosRequirements;
	
	@XmlElementWrapper(name = "requiredServicesNameList")
	@XmlElement(name = "serviceName")
	@JsonProperty("requiredServicesName")
	private List<String> requiredServicesNameList;

	@XmlElement(name = "locationRequirement")
	@JsonProperty("locationRequirement")
	protected LocationScopeValue locationRequirement;
	
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}

	public List<String> getRequiredServicesNameList() {
		return requiredServicesNameList;
	}
	public void setRequiredServicesNameList(List<String> requiredServicesNameList) {
		this.requiredServicesNameList = requiredServicesNameList;
	}
	public QoSscopeValue getQosRequirements() {
		return qosRequirements;
	}
	public void setQosRequirements(QoSscopeValue qosRequirements) {
		this.qosRequirements = qosRequirements;
	}
	public LocationScopeValue getLocationRequirement() {
		return locationRequirement;
	}
	public void setLocationRequirement(LocationScopeValue locationRequirement) {
		this.locationRequirement = locationRequirement;
	}

}
