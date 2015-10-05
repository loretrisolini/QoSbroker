package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import eu.neclab.iotplatform.ngsi.api.datamodel.Restriction;

/*
<?xml version="1.0" encoding="UTF-8"?>
<serviceAgreementRequest>
	<serviceDefinition>
	<operationType>queryContext</operationType>
		<entityIdList> 
		<entityId type="temperature" isPattern="false"> 
			<id>sensor_1:temperature</id> 
		</entityId> 
		<entityId type="Room" isPattern="true"> 
			<id>.*</id>			 
		</entityId>
		<entityId type="environment" isPattern="true"> 
			<id>sensor_2:environment</id>			 
		</entityId>  
	</entityIdList> 
	<attributeList> 
		<attribute>temperature</attribute> 
		<attribute>humidity</attribute> 
	</attributeList>
	<restriction>
		<scope>
			 <operationScope>
				 <scopeType>Location</scopeType>
				 <scopeValue>
					 <Altitude>30</Altitude>
					 <Latitude>43.656998</Latitude>
					 <Longitude>10.437418</Longitude>
				 </scopeValue>
				  </operationScope>
  <operationScope>
				 <scopeType>ServiceFeatures</scopeType>
				 <scopeValue>
					<featureList>
					   <feature>
						 <featureName>accuracy</featureName>
						 <featureValue>0.8</featureValue>
					   </feature>
					</featureList>
				 </scopeValue>
				  </operationScope> 
  <operationScope>
				 <scopeType>QoS</scopeType>
				 <scopeValue>
					 <maxResponseTime>15</maxResponseTime>
					 <maxRateRequest>15</maxRateRequest>
				 </scopeValue>
			 </operationScope>
		</scope>
	</restriction>
	</serviceDefinition>
	</serviceAgreementRequest>
*/

@XmlRootElement(name = "serviceDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceDefinition extends DataStructure{

	@XmlElement(required=true)
	private String operationType;
	
	@XmlElementWrapper(name = "entityIdList")
	@XmlElement(name = "entityId", required = true)
	private List<EntityId> entityId = null;
	
	@XmlElementWrapper(name = "attributeList")
	@XmlElement(name = "attribute", required = true)
	private List<String> attribute;
	
	@XmlElement(required=true)
	private Restriction restriction = null;

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}



	public Restriction getRestriction() {
		return restriction;
	}

	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}

	public List<EntityId> getEntityIdList() {
		return entityId;
	}

	public void setEntityIdList(List<EntityId> entityId) {
		this.entityId = entityId;
	}

	public List<String> getAttributeList() {
		return attribute;
	}

	public void setAttributeList(List<String> attribute) {
		this.attribute = attribute;
	}

}