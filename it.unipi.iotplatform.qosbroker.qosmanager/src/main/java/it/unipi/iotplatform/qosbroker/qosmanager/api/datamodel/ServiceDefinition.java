package it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import eu.neclab.iotplatform.ngsi.api.datamodel.Restriction;

/*
<serviceDefinition>
	<operationType>queryContext</operationType>
	<entityInfo>
<entityType>Room1</entityType>
</entityInfo>
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
	</serviceDefinition>
	
	<serviceDefinition>
	<operationType>queryContext</operationType>
<entityInfo>
	<entityID>sensorTemp_1:temperature</entityID>
<entityInfo>
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
					 <feature>
						 <featureName>accuracy</featureName>
						 <featureValue>0.8</featureValue>
					 </feature>
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
*/

@XmlRootElement(name = "serviceDefinition")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceDefinition extends ServiceAgreementStructure{

	@XmlElement(required=true)
	private String operationType;
	
    @XmlElements(value = { 
            @XmlElement(name="entityType", 
                        type=String.class),
            @XmlElement(name="entityID", 
                        type=String.class),
    })
	private String entityInfo;
	
	@XmlElement(required=true)
	private String attributeName;
	
	@XmlElement(required=true)
	private Restriction restriction = null;

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
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

	public String getEntityInfo() {
		return entityInfo;
	}

	public void setEntityInfo(String entityInfo) {
		this.entityInfo = entityInfo;
	}

}