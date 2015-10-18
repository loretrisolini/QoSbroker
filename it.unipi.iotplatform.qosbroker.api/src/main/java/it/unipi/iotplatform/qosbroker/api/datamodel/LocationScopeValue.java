package it.unipi.iotplatform.qosbroker.api.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;
import eu.neclab.iotplatform.ngsi.api.datamodel.Segment;

@XmlRootElement(name = "scopeValue")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationScopeValue<T> extends DataStructure {
	
    @XmlElements({
        @XmlElement(name="circle", type=Circle.class),
        @XmlElement(name="polygon", type=Polygon.class),
        @XmlElement(name="point", type=Point.class)
    })
    //can be Point, Segment, Circle
	private T locationRequirement;

	public T getLocationRequirement() {
		return locationRequirement;
	}

	public void setLocationRequirement(T locationRequirement) {
		
		if(locationRequirement.getClass() != Point.class && locationRequirement.getClass() != Circle.class &&
				locationRequirement.getClass() != Polygon.class){
			throw new RuntimeException("locationRequirements must be Point, Circle or Polygon");
		}
		
		this.locationRequirement = locationRequirement;
	}
	
}