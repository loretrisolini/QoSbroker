package it.unipi.iotplatform.qosbroker.qosmanager.datamodel;

import java.util.List;

import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.Restriction;

//class to represent the request
//that is sent through the ServiceAgreementRequest
public class Request {

	private Integer reqId;
	private String opType;
	private QoSreq qosRequirements;
	private Restriction restriction;
	//entityId List in the serviceRequest
	private List<EntityId> entityIdList;
	private List<Service> servicesRequested;
	
	public Request(Integer reqId, String opType, QoSreq qosRequirements,
			Restriction restriction, List<EntityId> entityIdList,
			List<Service> servicesRequested) {

		this.reqId = reqId;
		this.opType = opType;
		this.qosRequirements = qosRequirements;
		this.restriction = restriction;
		this.entityIdList = entityIdList;
		this.servicesRequested = servicesRequested;
	}
	
	public Integer getReqId() {
		return reqId;
	}
	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}
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
	public List<Service> getServicesRequested() {
		return servicesRequested;
	}
	public void setServicesRequested(List<Service> servicesRequested) {
		this.servicesRequested = servicesRequested;
	}
	
}
