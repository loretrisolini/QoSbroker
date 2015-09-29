package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.Restriction;

//class to represent the request
//that is sent through the ServiceAgreementRequest
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Request extends DataStructure{

	public Request(String transactionId, String opType, QoSreq qosRequirements,
			Restriction restriction, List<EntityId> entityIdList,
			List<Service> requestedServiceList) {
		super();
		this.transactionId = transactionId;
		this.opType = opType;
		this.qosRequirements = qosRequirements;
		this.restriction = restriction;
		this.entityIdList = entityIdList;
		this.requestedServiceList = requestedServiceList;
	}
	@XmlElement(name = "requestId")
	@JsonProperty("requestId")
	private String transactionId;
	
	@XmlElement(name = "operationType")
	@JsonProperty("operationType")
	private String opType;
	
	@XmlElement(name = "qosRequirements")
	@JsonProperty("qosRequirements")
	private QoSreq qosRequirements;
	
	@XmlElement(name = "restriction")
	@JsonProperty("restriction")
	private Restriction restriction;
	
	@XmlElementWrapper(name = "entityIdList")
	@XmlElement(name = "entityId")
	@JsonProperty("entities")
	//entityId List in the serviceRequest
	private List<EntityId> entityIdList;
	
	@XmlElementWrapper(name = "requestedServiceList")
	@XmlElement(name = "service")
	@JsonProperty("requestedServices")
	private List<Service> requestedServiceList;
	

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
	public Restriction getRestriction() {
		return restriction;
	}
	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}
	public List<EntityId> getEntityIdList() {
		return entityIdList;
	}
	public void setEntityIdList(List<EntityId> entityIdList) {
		this.entityIdList = entityIdList;
	}

	public List<Service> getRequestedServiceList() {
		return requestedServiceList;
	}

	public void setRequestedServiceList(List<Service> requestedServiceList) {
		this.requestedServiceList = requestedServiceList;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
}
