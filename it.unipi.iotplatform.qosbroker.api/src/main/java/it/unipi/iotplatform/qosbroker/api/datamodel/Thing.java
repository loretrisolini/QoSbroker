package it.unipi.iotplatform.qosbroker.api.datamodel;

import it.unipi.iotplatform.qosbroker.qosmanager.utils.ThingInfoContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationAttribute;

/* class that represents a Thing (associated 
 * to a ContextRegistrationResponse element) */
@XmlRootElement(name = "thing")
@XmlAccessorType(XmlAccessType.FIELD)
public class Thing extends DataStructure{
	
	@XmlElement(name = "batteryLevel")
	@JsonProperty("batteryLevel")
	private Double batteryLevel;
	
	@XmlElementWrapper(name = "servicesList")
	@XmlElement(name = "service")
	@JsonProperty("services")
	//Map<ServiceName, ServiceFeatures>
	private HashMap<String, ServiceFeatures> servicesList;

	public Double getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(Double batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public HashMap<String, ServiceFeatures> getServicesList() {
		return servicesList;
	}

	public void setServicesList(HashMap<String, ServiceFeatures> servicesList) {
		this.servicesList = servicesList;
	}
	
	//given a Pair of List<ContextRegistrationAttribute>, List<ContextAttribute> contAttrsList
	//return a ThingInfoContainer object
	public static Thing getThingInfoContainer(String id,
			List<ContextRegistrationAttribute> contRegAttrsList, List<ContextAttribute> contAttrsList){
		
		Thing t = new Thing();
		HashMap<String, ServiceFeatures> services = new HashMap<>();
		
		//iterate over List<ContextRegistrationAttribute>
		//to get the list of services on a Thing
		//and for each service the value of c_ij and t_ij
		for(ContextRegistrationAttribute crAttr: contRegAttrsList){
			
			ServiceFeatures servFeat = new ServiceFeatures();
			
			List<ContextMetadata> contMetadata = contRegAttr.getMetaData();
			
		    if(contMetadata.isEmpty()){
		    	continue;
		    }
			
		    Map<String,ContextMetadata> mappedContMetadata = 
		    		Maps.uniqueIndex(contMetadata, new Function <ContextMetadata,String> () {
			          public String apply(ContextMetadata from) {
			            return from.getName(); 
		    }});
			
		}
	    
		//create a map for each List<ContextMetadata> in a ContextRegistrationAttribute element
		//inside a List<ContextRegistrationAttribute>
		HashMap<String, Map<String, ContextMetadata>> mappedContRegAttrsMetadata =
				new HashMap<>();

		for(ContextRegistrationAttribute contRegAttr: contRegAttrsList){
			
			List<ContextMetadata> contMetadata = contRegAttr.getMetaData();
			
		    if(contMetadata.isEmpty()){
		    	continue;
		    }
			
		    Map<String,ContextMetadata> mappedContMetadata = 
		    		Maps.uniqueIndex(contMetadata, new Function <ContextMetadata,String> () {
			          public String apply(ContextMetadata from) {
			            return from.getName(); 
		    }});
		    
		    if(mappedContMetadata.isEmpty()){
		    	return null;
		    }
		    
		    mappedContRegAttrsMetadata.put(contRegAttr.getName(), mappedContMetadata);
		}
	    
		Map<String,ContextAttribute> mappedContAttrs;
		if(contAttrsList != null){
			//create a map from List<ContextAttribute>
		    mappedContAttrs = 
		    		Maps.uniqueIndex(contAttrsList, new Function <ContextAttribute,String> () {
			          public String apply(ContextAttribute from) {
			            return from.getName(); 
		    }});
		}
		else{
			mappedContAttrs = null;
		}
		
	}
}
