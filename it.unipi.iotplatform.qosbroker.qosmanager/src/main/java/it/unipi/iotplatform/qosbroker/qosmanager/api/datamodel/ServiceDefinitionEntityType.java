package it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.Restriction;

/*
 * 
<serviceDefinitionEntityType>
	<operationType>queryContext</operationType>
	<entityType>Room1</entityType>
	<attributeName>temperature</attributeName>
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
	</serviceDefinitionEntityType>
 * 
 * */

@XmlRootElement(name = "serviceDefinitionEntityType")
public class ServiceDefinitionEntityType extends ServiceRequestStructure{

	@XmlElement(name = "operationType", required=true)
	private String operationType;
	
	@XmlElement(name = "entityType")
	private String entityType;
	
	@XmlElement(name = "attributeName", required=true)
	private String attributeName;
	
	@XmlElement(name = "restriction", required=true)
	private Restriction restriction = null;

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Restriction getRestriction() {
		return restriction;
	}

	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributeName == null) ? 0 : attributeName.hashCode());
		result = prime * result
				+ ((entityType == null) ? 0 : entityType.hashCode());
		result = prime * result
				+ ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result
				+ ((restriction == null) ? 0 : restriction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceDefinitionEntityType other = (ServiceDefinitionEntityType) obj;
		if (attributeName == null) {
			if (other.attributeName != null)
				return false;
		} else if (!attributeName.equals(other.attributeName))
			return false;
		if (entityType == null) {
			if (other.entityType != null)
				return false;
		} else if (!entityType.equals(other.entityType))
			return false;
		if (operationType == null) {
			if (other.operationType != null)
				return false;
		} else if (!operationType.equals(other.operationType))
			return false;
		if (restriction == null) {
			if (other.restriction != null)
				return false;
		} else if (!restriction.equals(other.restriction))
			return false;
		return true;
	}

}
