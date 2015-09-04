package it.unipi.iotplatform.qosbroker.qosmanager.api.datamodel;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "serviceRequest")
public class ServiceRequest extends ServiceRequestStructure{
	
	@XmlElementWrapper(name = "serviceRequest")
	@XmlElement(name = "serviceDefinition", required = true)
	private ArrayList<ServiceDefinition> serviceDefList;

	public ArrayList<ServiceDefinition> getServiceDefList() {
		return serviceDefList;
	}

	public void setServiceDefList(ArrayList<ServiceDefinition> serviceDefList) {
		this.serviceDefList = serviceDefList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((serviceDefList == null) ? 0 : serviceDefList.hashCode());
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
		ServiceRequest other = (ServiceRequest) obj;
		if (serviceDefList == null) {
			if (other.serviceDefList != null)
				return false;
		} else if (!serviceDefList.equals(other.serviceDefList))
			return false;
		return true;
	}

}
