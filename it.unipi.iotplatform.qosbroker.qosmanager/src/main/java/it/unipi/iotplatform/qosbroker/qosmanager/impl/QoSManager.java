package it.unipi.iotplatform.qosbroker.qosmanager.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.DataStructure;
import it.unipi.iotplatform.qosbroker.api.datamodel.Policy;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSCode;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSConsts;
import it.unipi.iotplatform.qosbroker.api.datamodel.QoSReasonPhrase;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ReservationResults;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServicePeriodParams;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;
import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;
import it.unipi.iotplatform.qosbroker.qoscalculator.api.QoSCalculatorIF;
import it.unipi.iotplatform.qosbroker.qosmanager.api.QoSManagerIF;
import it.unipi.iotplatform.qosbroker.qosmonitor.api.QoSMonitorIF;

import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistration;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.StatusCode;

public class QoSManager implements QoSManagerIF {

//	/**  Reference to the Negotiator engine */
//	private NegotiationInterface negotiator; 
	
	private QoSCalculatorIF qosCalculator;
	
	private QoSBigDataRepository bigDataRepository;
	
	
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
	public StatusCode createAgreement(String offer, String transactionId, Request request){//, HashMap<String, TransIdList> thingTransactionsMap){
		
		//TODO Parse Offer
		
		StatusCode statusCode;
		
		//counter for the number of service requests
		int k=0;
		
		System.out.println("QoSManager -- createAgreement() read old requests from DB");
		//read all old requests from requests DB
		List<Pair<String, JSONObject>> requestsJsonList = bigDataRepository.readData(null, QoSConsts.REQUESTS_DB);
		if(requestsJsonList == null){
			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), 
											QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), "QoSManager -- createAgreement() Error in reading old requests from "+QoSConsts.REQUESTS_DB);
			return statusCode;
		}
		
		//convert JsonObj requests in request object
		//and put it in List<transId, Request>
		//it will be the list of service requests
		//given in input to the heuristic
		List<Pair<String, Request>> requestsList = new ArrayList<>();
		
		//it is used to compute hyperperiod h
		ArrayList<Double> periodsList = new ArrayList<>();
		
		//Map<transactionId, <p_j, h/p_j>>
		HashMap<String, ServicePeriodParams> servPeriodParamsMap = new HashMap<>();
		
		Double p_j;
		
		//check if there are old requests already allocated
		if(!requestsJsonList.isEmpty()){
			for(Pair<String, JSONObject> reqPair: requestsJsonList){
				String transId = reqPair.getLeft();
				Request req = Request.fromJsonToJaxb(reqPair.getRight(), new Request(), Request.class);
				
				//take the number of services requested for
				//the request in this transaction
				k+=req.getRequiredServicesNameList().size();
				
				//take the period p_j equal to maxRateRequest
				//in QoSrequirements of the request identified by transId
				p_j = req.getQosRequirements().getMaxRateRequest();
				periodsList.add(p_j);
				
				//set p_j in servPeriodParamsMap
				ServicePeriodParams servPeriodParams = new ServicePeriodParams();
				servPeriodParams.setPeriod(p_j);
				servPeriodParamsMap.put(transId, servPeriodParams);
				
				requestsList.add(new Pair<String, Request>(transId, req));
			}
		}
		
		System.out.println("QoSManager -- createAgreement() add the new request");
		//add the new request object
		requestsList.add(new Pair<String, Request>(transactionId, request));
		
		k+=request.getRequiredServicesNameList().size();
		p_j = request.getQosRequirements().getMaxRateRequest();
		periodsList.add(p_j);
		
		ServicePeriodParams servPeriodParams = new ServicePeriodParams();
		servPeriodParams.setPeriod(p_j);
		servPeriodParamsMap.put(transactionId, servPeriodParams);
		
		System.out.println("QoSManager -- createAgreement() compute hyperperiod");
		//compute hyperiod h
		Long h = ServicePeriodParams.getHyperperiod(periodsList);
		
		Double coeff;
		//complete to fill Map<transactionId, <p_j, h/p_j>> with h/p_j
		for(Map.Entry<String, ServicePeriodParams> entry: servPeriodParamsMap.entrySet()){
			p_j = entry.getValue().getPeriod();
			coeff = h/p_j;
			entry.getValue().setNj(coeff.intValue());
		}
		
		System.out.println("QoSManager -- createAgreement() read THINGS_INFO_DB and SERV_EQ_THINGS_DB");
		//read from DBs the Map<DevId, Thing> and the Map<reqServName, List<DevId>>
		//Map<DevId, List<transId>>
		List<Pair<String, JSONObject>> thingsInfoJson = bigDataRepository.readData(null, QoSConsts.THINGS_INFO_DB);
		List<Pair<String, JSONObject>> servNameThingsIdListJson = bigDataRepository.readData(null, QoSConsts.SERV_EQ_THINGS_DB);
//		List<Pair<String, JSONObject>> thingTransactionsJson = bigDataRepository.readData(null, QoSConsts.REQUIREMENTS_DB);
		if(thingsInfoJson == null || thingsInfoJson.isEmpty() || 
				servNameThingsIdListJson == null || servNameThingsIdListJson.isEmpty()){// || thingTransactionsJson==null){
			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), 
					QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
					"QoSManager -- createAgreement() Error in reading thingInfo,servNameThingsIdList, thingTransactionsJson from "+ 
					QoSConsts.THINGS_INFO_DB +", "+QoSConsts.SERV_EQ_THINGS_DB);//+", "+QoSConsts.REQUIREMENTS_DB);
			
			return statusCode;
		}
		
		HashMap<String, Thing> thingsInfo = Thing.fromDbFormatToJavaFormat(thingsInfoJson, Thing.class);
		if(thingsInfo == null){
			
			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), 
					QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
					"QoSManager -- createAgreement() Error conversion fromDbFormatToJavaFormat of eqThingInfoJson");
			
			return statusCode;
		}
		
		HashMap<String, ThingsIdList> servNameThingsIdList = 
				ThingsIdList.fromDbFormatToJavaFormat(servNameThingsIdListJson, ThingsIdList.class);
		if(servNameThingsIdList == null){
			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), 
					QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
					"QoSManager -- createAgreement() Error conversion fromDbFormatToJavaFormat of servNameThingsIdListJson");
			
			return statusCode;
		}

//		HashMap<String, TransIdList> matrixM = 
//				TransIdList.fromDbFormatToJavaFormat(thingTransactionsJson, TransIdList.class);
//		if(matrixM == null){
//			statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), 
//					QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
//					"QoSManager -- createAgreement() Error conversion fromDbFormatToJavaFormat of thingTransactionsJson");
//			
//			return statusCode;
//		}
		
//		//fuse the matrixM read from the DB
//		//and new one computed with the ServiceAgreement operation
//		if(!matrixM.isEmpty()){
//			for(Map.Entry<String, TransIdList> entry: thingTransactionsMap.entrySet()){
//				String devId = entry.getKey();
//				
//				if(matrixM.get(devId) != null){
//					matrixM.get(devId).getTransIdList().addAll(entry.getValue().getTransIdList());
//				}
//				else{
//					matrixM.put(devId, entry.getValue());
//				}
//			}
//		}
//		else{
//			//case in which matrixM is empty
//			//so add directly all the elements
//			matrixM.putAll(thingTransactionsMap);
//		}
		
		System.out.println("QoSManager -- createAgreement() compute allocation");
		//execute allocation algorithm
		ReservationResults result = qosCalculator.computeAllocation(k, requestsList, servPeriodParamsMap, 
														thingsInfo, servNameThingsIdList, Policy.MAX_SPLIT, 0.001);
		
		List<ContextRegistration> allocationSchema = null;
		if(result.isFeas()){
			
			System.out.println("QoSManager -- createAgreement() allocation OK");
			allocationSchema = result.getAllocationSchema();
			
//			//store new matrixM if allocation feasible
//			List<Pair<String, JSONObject>> matrixMJson = TransIdList.fromJavaFormatToDbFormat(matrixM, TransIdList.class);
//			if(matrixMJson == null){
//				statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), 
//						QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
//						"QoSManager -- createAgreement() Error conversion fromJavaFormatToDbFormat of matrixM");
//				
//				return statusCode;
//			}
//			else{
//				if(!bigDataRepository.storeData(matrixMJson, QoSConsts.REQUIREMENTS_DB)){
//					statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), 
//							QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
//							"QoSManager -- createAgreement() Error store matrixMJson in DB "+QoSConsts.REQUIREMENTS_DB);
//					
//					return statusCode;
//				}
//			}
			
			//convert the new request in JSON and add thins one to the old
			//list of request read from the DB
			JSONObject newRequestJson = Request.fromJaxbToJson(request, Request.class);
			//store new request Result if allocation feasible			
			requestsJsonList.add(new Pair<String, JSONObject>(transactionId, newRequestJson));
			if(!bigDataRepository.storeData(requestsJsonList, QoSConsts.REQUESTS_DB)){
				statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), 
						QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
						"QoSManager -- createAgreement() Error store requestsJsonList in DB "+QoSConsts.REQUESTS_DB);
				
				return statusCode;
			}
			
			//store new allocation schemas if allocation is feasible
			List<Pair<String, JSONObject>> allocationSchemaJson = new ArrayList<Pair<String, JSONObject>>();
			
			for(ContextRegistration contReg: allocationSchema){
				JSONObject contRegJson = DataStructure.fromJaxbToJson(contReg, ContextRegistration.class);
				
				//there is only one id, the one relative to transactionId
				String transId = contReg.getListEntityId().get(0).getId();
				
				allocationSchemaJson.add(new Pair<String, JSONObject>(transId ,contRegJson));
			}
			if(!bigDataRepository.storeData(allocationSchemaJson, QoSConsts.ALLOCATION_DB)){
				statusCode = new StatusCode(QoSCode.INTERNALERROR_500.getCode(), 
						QoSReasonPhrase.RECEIVERINTERNALERROR_500.name(), 
						"QoSManager -- createAgreement() Error store allocationSchemaJson in DB "+QoSConsts.ALLOCATION_DB);
				
				return statusCode;
			}
			
			return result.getStatusCode();
		}
		else{
			
			System.out.println("QoSManager -- createAgreement() allocation FAILED");
			return result.getStatusCode();
		}
		

	}

	public QoSCalculatorIF getQosCalculator() {
		return qosCalculator;
	}

	public void setQosCalculator(QoSCalculatorIF qosCalculator) {
		this.qosCalculator = qosCalculator;
	}

	public QoSBigDataRepository getBigDataRepository() {
		return bigDataRepository;
	}

	public void setBigDataRepository(QoSBigDataRepository bigDataRepository) {
		this.bigDataRepository = bigDataRepository;
	}

	@Override
	public List<ContextRegistration> readAllocationSchema(
			List<String> transactionId) {
		
		System.out.println("QoSManager -- readAllocationSchemas read allocation schemas");
		List<Pair<String, JSONObject>> allocationSchemasList = bigDataRepository.readData(transactionId, QoSConsts.ALLOCATION_DB);
		if(allocationSchemasList == null){
			return null;
		}
		
		List<ContextRegistration> allocation = new ArrayList<>();
		
		for(Pair<String, JSONObject> pair: allocationSchemasList){
			
			ContextRegistration allocationSchema = DataStructure.fromJsonToJaxb(pair.getRight(), new ContextRegistration(), 
																								ContextRegistration.class);
			allocation.add(allocationSchema);
			
			
			//TODO UPDATE POLICY+1
		}
		
		return allocation;
	}
	
//	public NegotiationInterface getNegotiator() {
//	return negotiator;
//}
//
//public void setNegotiator(NegotiationInterface negotiator) {
//	this.negotiator = negotiator;
//}

}
