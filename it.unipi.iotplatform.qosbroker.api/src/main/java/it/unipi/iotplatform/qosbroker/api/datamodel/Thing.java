package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;

/* class that represents a Thing (associated 
 * to a ContextRegistrationResponse element) */
@XmlRootElement(name = "thing")
@XmlAccessorType(XmlAccessType.FIELD)
public class Thing extends DataStructure{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(Thing.class);
	
	@XmlElement(name = "batteryLevel")
	@JsonProperty("batteryLevel")
	private Double batteryLevel;
	
	@XmlElement(name = "coordinates")
	@JsonProperty("coordinates")
	private Point coords;
	
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
	//return a Thing object
	public static Thing getThing(
			List<ContextRegistrationAttribute> contRegAttrsList, List<ContextAttribute> contAttrsList){
		
		Thing t = new Thing();
		HashMap<String, ServiceFeatures> services = new HashMap<>();
		
		//iterate over List<ContextRegistrationAttribute>
		//to get the list of services on a Thing
		//and for each service the value of c_ij and t_ij
		for(ContextRegistrationAttribute contRegAttr: contRegAttrsList){
			
			ServiceFeatures servFeat = new ServiceFeatures();
			
			List<ContextMetadata> contMetadata = contRegAttr.getMetaData();
			
		    if(contMetadata.isEmpty()){
		    	//case in which that attr
		    	//have no features latency or energy cost
		    	servFeat.setLatency(null);
		    	servFeat.setEnergyCost(null);
		    	services.put(contRegAttr.getName(), servFeat);
		    	
		    	continue;
		    }
			
		    Map<String,ContextMetadata> mappedContMetadata = 
		    		Maps.uniqueIndex(contMetadata, new Function <ContextMetadata,String> () {
			          public String apply(ContextMetadata from) {
			            return from.getName(); 
		    }});
		    
		    if(mappedContMetadata.get(ServiceFeatures.LATENCY) != null){
		    	Double latency = Double.valueOf(String.valueOf(mappedContMetadata.get(ServiceFeatures.LATENCY).getValue()));
		    	servFeat.setLatency(latency);
		    }
		    else{
		    	logger.debug("Latency value is null");
		    	servFeat.setLatency(null);
		    }
			
		    if(mappedContMetadata.get(ServiceFeatures.ENERGY_COST) != null){
		    	Double enCost = Double.valueOf(String.valueOf(mappedContMetadata.get(ServiceFeatures.ENERGY_COST).getValue()));
		    	servFeat.setEnergyCost(enCost);
		    }
		    else{
		    	logger.debug("EnergyCost value is null");
		    	servFeat.setEnergyCost(null);
		    }
		    
		    if(mappedContMetadata.get(ServiceFeatures.ACCURACY) != null){
		    	Double accuracy = Double.valueOf(String.valueOf(mappedContMetadata.get(ServiceFeatures.ACCURACY).getValue()));
			    servFeat.setAccuracy(accuracy);
		    }
		    else{
			    servFeat.setAccuracy(null);
		    }
		    
		    services.put(contRegAttr.getName(), servFeat);
		}
		
		//set the list of services of a thing
		t.setServicesList(services);
			
		if(contAttrsList == null){
			//battery value is not known
			t.setBatteryLevel(null);
			
			//coords value is not known
			t.setCoords(null);
		}
		else{
			
		    Map<String,ContextAttribute> mappedContAttrs = 
		    		Maps.uniqueIndex(contAttrsList, new Function <ContextAttribute,String> () {
			          public String apply(ContextAttribute from) {
			            return from.getName(); 
		    }});
		    
		    if(mappedContAttrs.get(QoSConsts.BATTERY) != null){
			    Double battery = Double.valueOf(String.valueOf(mappedContAttrs.get(QoSConsts.BATTERY).getcontextValue()));
			    t.setBatteryLevel(battery);
			}
		
		    String[] coordsValues;
		    if(mappedContAttrs.get(QoSConsts.COORDS) != null){
		    	String coords = String.valueOf(mappedContAttrs.get(QoSConsts.COORDS).getcontextValue());
		    	
		    	try{
		    		coordsValues = coords.split(",");
		    		
			    	Point p = new Point();
			    	
			    	p.setLatitude(Float.valueOf(coordsValues[0]));
			    	p.setLongitude(Float.valueOf(coordsValues[1]));
			    	
			    	t.setCoords(p);
		    	}
		    	catch(PatternSyntaxException pe){
		    		logger.debug("coords was not in format \"latitude,longitude\" ");
		    		
					//coords value is not known
					t.setCoords(null);
		    	}
		    	
		    }
		}
		
		return t;
	}

	public Point getCoords() {
		return coords;
	}

	public void setCoords(Point coords) {
		this.coords = coords;
	}
	
//	"thing":{"servicesList":{"entry":[{"value":"","key":"battery"},{"value":{"latency":0.2000000000000000111,"energyCost":0.9000000000000000222},"key":"degrees"}]},"batteryLevel":77}}
}
