package it.unipi.iotplatform.qosbroker.qosmanager.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationAttribute;

/* class that contains the data structures to parse in a easy
 * way the pairs List<ContextRegistrationAttribute> and List<ContextAttribute>
 * that represent the main info about a thing  */
public class ThingInfoContainer {

	//id of the entityId structure on which it is matched the 
	//ContextRegistration with the ContextElement
	//or the id of the first entityId structure
	//in the entityIdList in the ContextRegistration
	private String contextEntityId;
	
	//Mapping List<ContextRegistrationAttribute> in a ContextRegistration
	//in a Map<ContextRegistrationAttribute_Name, ContextRegistrationAttribute>
	private Map<String, ContextRegistrationAttribute> contRegAttrsMap;
	
	//Mapping List<ContextMetadata> in each element of contRegAttrsMap
	//in a Map<ContextRegistrationAttributeName, Map<ContextMetadata_Name, ContextMetadata>>
	private Map<String, Map<String, ContextMetadata>> contRegAttrsMetadataMapForEachContRegAttr;
	
	//Mapping List<ContextAttribute> in a ContextElement
	//in a Map<ContextAttribute_Name, ContextAttribute>
	private Map<String, ContextAttribute> contAttrsMap;
	
	//given a Pair of List<ContextRegistrationAttribute>, List<ContextAttribute> contAttrsList
	//return a ThingInfoContainer object
	public static ThingInfoContainer getThingInfoContainer(String id,
			List<ContextRegistrationAttribute> contRegAttrsList, List<ContextAttribute> contAttrsList){
		
		//create a map from List<ContextRegistrationAttribute>
	    Map<String, ContextRegistrationAttribute> mappedContRegAttrs = 
	    		Maps.uniqueIndex(contRegAttrsList, new Function <ContextRegistrationAttribute,String> () {
		          public String apply(ContextRegistrationAttribute from) {
		            return from.getName(); 
	    }});
		
	    if(mappedContRegAttrs.isEmpty()){
	    	return null;
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
		
	    ThingInfoContainer thingInfoContainer = new ThingInfoContainer();
		
	    thingInfoContainer.setContextEntityId(id);
	    thingInfoContainer.setContRegAttrsMap(mappedContRegAttrs);
	    thingInfoContainer.setContRegAttrsMetadataMapForEachContRegAttr(mappedContRegAttrsMetadata);
	    thingInfoContainer.setContAttrsMap(mappedContAttrs);
	    
		return thingInfoContainer;
	}

	public Map<String, ContextRegistrationAttribute> getContRegAttrsMap() {
		return contRegAttrsMap;
	}

	public void setContRegAttrsMap(
			Map<String, ContextRegistrationAttribute> contRegAttrsMap) {
		this.contRegAttrsMap = contRegAttrsMap;
	}

	public Map<String, ContextAttribute> getContAttrsMap() {
		return contAttrsMap;
	}

	public void setContAttrsMap(Map<String, ContextAttribute> contAttrsMap) {
		this.contAttrsMap = contAttrsMap;
	}

	public Map<String, Map<String, ContextMetadata>> getContRegAttrsMetadataMapForEachContRegAttr() {
		return contRegAttrsMetadataMapForEachContRegAttr;
	}

	public void setContRegAttrsMetadataMapForEachContRegAttr(
			Map<String, Map<String, ContextMetadata>> contRegAttrsMetadataMapForEachContRegAttr) {
		this.contRegAttrsMetadataMapForEachContRegAttr = contRegAttrsMetadataMapForEachContRegAttr;
	}

	public String getContextEntityId() {
		return contextEntityId;
	}

	public void setContextEntityId(String contextEntityId) {
		this.contextEntityId = contextEntityId;
	}
	
}
