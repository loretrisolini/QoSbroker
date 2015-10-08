package it.unipi.iotplatform.qosbroker.api.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;

@XmlRootElement(name = "LocationScopeValue")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationScopeValue<T> extends DataStructure {
	
	@XmlElement(name = "locationRequirement", required = true)
	//can be Point, Circle or Polygon
	private T locationRequirement;

	public T getLocationRequirement() {
		return locationRequirement;
	}

	public void setLocationRequirement(T locationRequirement) {
		
		if(locationRequirement.getClass() != Point.class || locationRequirement.getClass() != Circle.class || 
				locationRequirement.getClass() != Polygon.class){
			throw new RuntimeException("locationRequirements must be Point, Circle or Polygon");
		}
		
		this.locationRequirement = locationRequirement;
	}
	
}