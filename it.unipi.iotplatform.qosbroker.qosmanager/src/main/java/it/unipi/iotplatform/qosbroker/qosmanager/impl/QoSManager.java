package it.unipi.iotplatform.qosbroker.qosmanager.impl;

import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSManagerIF;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.RequestResults;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Service;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ServiceExecutionFeature;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ThingIdThingServiceIdPair;
import it.unipi.iotplatform.qosbroker.qosmanager.datamodel.ThingService;

import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class QoSManager implements QoSManagerIF {

//	/**  Reference to the Negotiator engine */
//	private NegotiationInterface negotiator; 
	
//	private QoSCalculatorIF qosCalculator;
	
	@Override
	public String getTemplate() {

		try{
			FileInputStream is = new FileInputStream("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/qosbrokerconfig/schemas/wsag4j/fiware-template.xml");

		    DocumentBuilderFactory dFactory= DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder= dFactory.newDocumentBuilder();
		    Document doc= dBuilder.parse(is);
		    try {
		        StringWriter sw = new StringWriter();
		        TransformerFactory tf = TransformerFactory.newInstance();
		        Transformer transformer = tf.newTransformer();
		        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		        transformer.transform(new DOMSource(doc), new StreamResult(sw));
		        String s=sw.toString();
		     
		     	return s;
		     	
		    } catch (Exception ex) {
		        throw new RuntimeException("Error converting to String", ex);
		    }

		}
		catch(Exception e){
			throw new RuntimeException("Error converting to String", e);
		}
	}
	
	@Override
	public void createAgreement(String offer, RequestResults reqResult){
		
		//TODO Parse Offer
		
		List<RequestResults> requestResultsList = new ArrayList<>();
		//TODO Get old List of RequestResults
		
		requestResultsList.add(reqResult);
		
		//Map<transId_ServiId, List<<tId_tsId,f_ij,u_ij>>
		HashMap<String, List<ServiceExecutionFeature>> mappingServEqThings = new HashMap<>();
		HashMap<String, Integer> coefficientMap = new HashMap<>();
		HashMap<String, Integer> periodsMap = new HashMap<>();
		
		ArrayList<Integer> periods = new ArrayList<>();
		
		for(RequestResults requestResult: requestResultsList){
			
			Integer period = requestResult.getRequest().getQosRequirements().getMaxRateRequest();
			
			periodsMap.put(requestResult.getTransactionId(), period);
			periods.add(period);
		}
		
		//TODO compute H hyperperiod
		long hyperPeriod = lcms(periods);
		
		for(RequestResults requestResult: requestResultsList){
			
			String transactionId = requestResult.getTransactionId();
			Integer period = periodsMap.get(transactionId);
			
			Long coeff = hyperPeriod/period;
			
			coefficientMap.put(transactionId, coeff.intValue());
			
			List<Service> requestedServicesList = requestResult.getRequest().getRequestedServiceList();
			
			for(Service service: requestedServicesList){
				
				List<ServiceExecutionFeature> servExecFeatList = new ArrayList<>();
				
				HashMap<Integer, Thing> thingsMap = 
						requestResult.getEquivalentThingsMappings().getThingsMap();
				
				List<ThingIdThingServiceIdPair> equivalentThingsId = 
						requestResult.getEquivalentThingsMappings().getEqThingsListPerService()
						.get(service.getServId()).getEquivalentThingsId();
				
				for(ThingIdThingServiceIdPair thingsIdThingServiceId: equivalentThingsId){
					
					Integer thingId = thingsIdThingServiceId.getThingId();
					Integer thingServiceId = thingsIdThingServiceId.getThingServiceId();
					
					Thing t = thingsMap.get(thingId);
					ThingService ts = t.getThingServices().get(thingServiceId);
					
					Integer batteryLev = t.getBatteryLevel();
					Double energyCost = ts.getThingServFeatures().getEnergyCost();
					Double latency = ts.getThingServFeatures().getLatency();
					
					Double normalizedEnergyCost = hyperPeriod/period * energyCost/batteryLev;
					Double utilization = latency/period;
					
					ServiceExecutionFeature servExecFeat = new ServiceExecutionFeature();
					
					String feautureId =  String.valueOf(thingId)+"_"+String.valueOf(thingServiceId);
					servExecFeat.setFeatureId(feautureId);
					servExecFeat.setNormalizedEnergyCost(normalizedEnergyCost);
					servExecFeat.setUtilization(utilization);
					servExecFeatList.add(servExecFeat);
				}
				
				String transId_servId = transactionId+"_"+String.valueOf(service.getServId());
				mappingServEqThings.put(transId_servId, servExecFeatList);
			}
		}
		
		//TODO heuristic algorithm
//		HashMap<Integer, List<ServiceExecutionFeature>> mappingServiceThing =
//				createMappingServiceThing();
		
//		heuristicScheduler = Executors.newSingleThreadExecutor();
//		Future<QoSRankList> future = 
//				heuristicScheduler.submit(new Heuristic(m_equivalents, m_assignments, 
//						m_requests, m_assuredrequests, m_thingservices, m_things, this.MapGw, 
//						serviceId, rank, requirement, caller.getGatewayId(), all, this.context));
//		
//			allocation_schema = future.get();
//		update_data(allocation_schema);
		
//		reservationResults = qosCalculator.
		
		//TODO store new request Result if allocation feasible
		
		//TODO store new allocation schemas if allocation is feasible
	}
	
//	public NegotiationInterface getNegotiator() {
//	return negotiator;
//}
//
//public void setNegotiator(NegotiationInterface negotiator) {
//	this.negotiator = negotiator;
//}
	
	private long lcms(ArrayList<Integer> periods){
	    long result = (long) Math.ceil(periods.get(0));
	    for(int i = 1; i < periods.size(); i++) result = lcm(result, (long) Math.ceil(periods.get(i)));
	    return result;
	}
	
	private long gcd(long a, long b){
	    while (b > 0)
	    {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	private long lcm(long a, long b){
	    return a * (b / gcd(a, b));
	}
}
