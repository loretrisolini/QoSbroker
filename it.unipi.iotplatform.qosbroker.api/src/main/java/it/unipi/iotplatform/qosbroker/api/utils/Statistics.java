package it.unipi.iotplatform.qosbroker.api.utils;

import it.unipi.iotplatform.qosbroker.api.datamodel.AllocationInfo;
import it.unipi.iotplatform.qosbroker.api.datamodel.Request;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceDefinition;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceFeatures;
import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;
import it.unipi.iotplatform.qosbroker.api.datamodel.ThingsIdList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextRegistrationResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;

public class Statistics{

	public static final String STAT_DIR = "/home/lorenzo/Statistics";
	
	public static File file;
	
	private static int abgapIterationCounter = 0;
	private static int resultCounter = 0;
	
	public int medium = 0;
	
	public Statistics(){

	}
	
	public void printThingsMappings(Request request, HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> serviceEquivalentThings) {
			
		FileWriter fileWriterThingsMappings = null;
		
		file = new File(STAT_DIR);
		if(!file.exists()){
			file.mkdir();
		}
		
		try{
			//create Thing folder
			File fileThing = new File(file.getAbsolutePath()+"Things");
			if(!fileThing.exists()){
				fileThing.mkdirs();
			}
			
			fileWriterThingsMappings = new FileWriter(fileThing.getPath()+"/Things.csv", true);
			
			fileWriterThingsMappings.append("DevId,BatteryLevel,Coords");
			fileWriterThingsMappings.append("\n");
			
			Map<String, HashMap<String, ServiceFeatures>> mapThingServices = new HashMap<>();
			
			for(Map.Entry<String, Thing> entryThing: thingsInfo.entrySet()){
				fileWriterThingsMappings.append(entryThing.getKey());
				fileWriterThingsMappings.append(",");

				fileWriterThingsMappings.append(entryThing.getValue().getBatteryLevel()==null ? "null" 
													: entryThing.getValue().getBatteryLevel().toString());
				fileWriterThingsMappings.append(",");
				fileWriterThingsMappings.append(entryThing.getValue().getCoords()==null ? "null" 
													: entryThing.getValue().getCoords().getLatitude()+" "+
													entryThing.getValue().getCoords().getLongitude());
				fileWriterThingsMappings.append("\n");
				mapThingServices.put(entryThing.getKey(), entryThing.getValue().getServicesList());
				
			}
			
			fileWriterThingsMappings.append("\n");
			
			if(!mapThingServices.isEmpty()){
			
				fileWriterThingsMappings.append("DevId,ServiceName,Latency,EnergyCost");//,Accuracy");
				fileWriterThingsMappings.append("\n");
				
				for(Map.Entry<String, HashMap<String, ServiceFeatures>> entry: mapThingServices.entrySet()){
					
					String devId = entry.getKey();
					HashMap<String, ServiceFeatures> services = entry.getValue();
					
					for(Map.Entry<String, ServiceFeatures> service: services.entrySet()){
						
						fileWriterThingsMappings.append(devId);
						fileWriterThingsMappings.append(",");
						fileWriterThingsMappings.append(service.getKey());
						fileWriterThingsMappings.append(",");
						fileWriterThingsMappings.append(service.getValue().getLatency()==null ? "null"
														: service.getValue().getLatency().toString());
						fileWriterThingsMappings.append(",");
						fileWriterThingsMappings.append(service.getValue().getEnergyCost()==null ? "null"
														: service.getValue().getEnergyCost().toString());
//						fileWriterThingsMappings.append(",");
//						fileWriterThingsMappings.append(service.getValue().getAccuracy()==null ? "null"
//														: service.getValue().getAccuracy().toString());
						fileWriterThingsMappings.append("\n");
					}
				}
			}
			
			fileWriterThingsMappings.append("\n");
			
			fileWriterThingsMappings.append("requiredServiceName,devIdList");
			fileWriterThingsMappings.append("\n");
			
			for(Map.Entry<String, ThingsIdList> entryEqThings: serviceEquivalentThings.entrySet()){
				
				fileWriterThingsMappings.append(entryEqThings.getKey());
				fileWriterThingsMappings.append(",");

				List<String> eqThings = entryEqThings.getValue().getEqThings();
				for(String thingId: eqThings){
					fileWriterThingsMappings.append(thingId);
					fileWriterThingsMappings.append(",");
				}
				fileWriterThingsMappings.append("\n");
			}
			
		}
		catch(Exception e){
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}
		finally{
			try {
				fileWriterThingsMappings.flush();
				fileWriterThingsMappings.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
		}
	}


	/* function to print the input values of the GAP algorithm */
	public void printInputsABGAP(int k, List<Pair<String, Request>> requests,
			HashMap<String,HashMap<String, Double>> matrixF_ij,
			HashMap<String,HashMap<String, Double>> matrixU_ij,
			HashMap<String, Integer> hyperperiodPeriodMap,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM, String prio, String split) {
		
		FileWriter fileWriterInputGap=null;
		
		try{
			
			//create Thing folder
			File fileABGAP = new File("./ABGAP_Results");
			if(!fileABGAP.exists()){
				fileABGAP.mkdirs();
			}

			fileWriterInputGap = new FileWriter(fileABGAP.getPath()+"/InputsABGAP_ABGAP_"+abgapIterationCounter+"_Medium_"+medium+".csv", true);
			abgapIterationCounter++;
			
			fileWriterInputGap.append("k,Priority,SplitPolicy");
			fileWriterInputGap.append("\n");
			
			fileWriterInputGap.append(String.valueOf(k));
			fileWriterInputGap.append(",");
			fileWriterInputGap.append(prio);
			fileWriterInputGap.append(",");
			fileWriterInputGap.append(split);
			fileWriterInputGap.append("\n");
			
			
			fileWriterInputGap.append("TransactionID, operationType, maxRespTime, maxRateRequest, LocationRequirement, ServiceList");
			fileWriterInputGap.append("\n");
			for(Pair<String, Request> reqPair: requests){
				String transId = reqPair.getLeft();
				Request req = reqPair.getRight();
				
				fileWriterInputGap.append(transId);
				fileWriterInputGap.append(",");
				fileWriterInputGap.append(req.getOpType());
				fileWriterInputGap.append(",");
				fileWriterInputGap.append(String.valueOf(req.getQosRequirements().getMaxResponseTime()));
				fileWriterInputGap.append(",");
				fileWriterInputGap.append(String.valueOf(req.getQosRequirements().getMaxRateRequest()));
				fileWriterInputGap.append(",");
				
				Point point = null;
				Circle circle = null;


				if(req.getLocationRequirementPoint()!=null){
					point = req.getLocationRequirementPoint().getLocationRequirement();
					fileWriterInputGap.append("Point: "+String.valueOf(point.getLatitude())+" "+String.valueOf(point.getLongitude()));
				}
				else{
					if(req.getLocationRequirementCircle()!=null){
						circle = req.getLocationRequirementCircle().getLocationRequirement();
						fileWriterInputGap.append("Circle: cLat: "+String.valueOf(circle.getCenterLatitude())+" | cLon: "+String.valueOf(circle.getCenterLongitude())
											+" | rad: "+String.valueOf(circle.getRadius()));
					}
				}
				
				fileWriterInputGap.append(",");
				
				for(String serv: req.getRequiredServicesNameList()){
					fileWriterInputGap.append(serv);
					fileWriterInputGap.append(",");
				}
				fileWriterInputGap.append("\n");
			}
			
			fileWriterInputGap.append("\n");
			fileWriterInputGap.append("devId, transId, service, f_ij");
			fileWriterInputGap.append("\n");
			for(Map.Entry<String,HashMap<String,Double>> entryF_ij: matrixF_ij.entrySet()){
				
				String devId = entryF_ij.getKey();
				
				HashMap<String, Double> transIdServ = entryF_ij.getValue();
				
				for(Map.Entry<String, Double> entryNeC: transIdServ.entrySet()){
					
					String[] transId_service = entryNeC.getKey().split("::");
					
					String transId = transId_service[0];
					String service = transId_service[1];
					
					fileWriterInputGap.append(devId);
					fileWriterInputGap.append(",");
					fileWriterInputGap.append(transId);
					fileWriterInputGap.append(",");
					fileWriterInputGap.append(service);
					fileWriterInputGap.append(",");
					fileWriterInputGap.append(String.valueOf(entryNeC.getValue()));
					fileWriterInputGap.append("\n");
				}
			}
			
			fileWriterInputGap.append("\n");
			fileWriterInputGap.append("devId, transId, service, u_ij");
			fileWriterInputGap.append("\n");
			for(Map.Entry<String,HashMap<String,Double>> entryU_ij: matrixU_ij.entrySet()){
				
				String devId = entryU_ij.getKey();
				
				HashMap<String,Double> transIdServ = entryU_ij.getValue();
				
				for(Map.Entry<String,Double> entryUt: transIdServ.entrySet()){
					
					String[] transId_service = entryUt.getKey().split("::");
					
					String transId = transId_service[0];
					String service = transId_service[1];

					fileWriterInputGap.append(devId);
					fileWriterInputGap.append(",");
					fileWriterInputGap.append(transId);
					fileWriterInputGap.append(",");
					fileWriterInputGap.append(service);
					fileWriterInputGap.append(",");
					fileWriterInputGap.append(String.valueOf(entryUt.getValue()));
					fileWriterInputGap.append("\n");
				}
			}
			
			fileWriterInputGap.append("\n");
			fileWriterInputGap.append("TransactionID, h/p_j");
			fileWriterInputGap.append("\n");
			for(Map.Entry<String, Integer> entryPeriod: hyperperiodPeriodMap.entrySet()){
				fileWriterInputGap.append(entryPeriod.getKey());
				fileWriterInputGap.append(",");
				fileWriterInputGap.append(String.valueOf(entryPeriod.getValue()));
				fileWriterInputGap.append("\n");
			}
			
			fileWriterInputGap.append("\n");
			
			fileWriterInputGap.append("DevId,BatteryLevel,Coords");
			fileWriterInputGap.append("\n");
			
			Map<String, HashMap<String, ServiceFeatures>> mapThingServices = new HashMap<>();
			
			for(Map.Entry<String, Thing> entryThing: eqThingInfo.entrySet()){
				fileWriterInputGap.append(entryThing.getKey());
				fileWriterInputGap.append(",");

				fileWriterInputGap.append(entryThing.getValue().getBatteryLevel()==null ? "null" 
													: entryThing.getValue().getBatteryLevel().toString());
				fileWriterInputGap.append(",");
				fileWriterInputGap.append(entryThing.getValue().getCoords()==null ? "null" 
													: entryThing.getValue().getCoords().getLatitude()+" "+
													entryThing.getValue().getCoords().getLongitude());
				fileWriterInputGap.append("\n");
				mapThingServices.put(entryThing.getKey(), entryThing.getValue().getServicesList());
				
			}
			
			fileWriterInputGap.append("\n");
			
			if(!mapThingServices.isEmpty()){
			
				fileWriterInputGap.append("DevId,ServiceName,Latency,EnergyCost");//,Accuracy");
				fileWriterInputGap.append("\n");
				
				for(Map.Entry<String, HashMap<String, ServiceFeatures>> entry: mapThingServices.entrySet()){
					
					String devId = entry.getKey();
					HashMap<String, ServiceFeatures> services = entry.getValue();
					
					for(Map.Entry<String, ServiceFeatures> service: services.entrySet()){
						
						fileWriterInputGap.append(devId);
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(service.getKey());
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(service.getValue().getLatency()==null ? "null"
															: service.getValue().getLatency().toString());
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(service.getValue().getEnergyCost()==null ? "null"
															: service.getValue().getEnergyCost().toString());
//						fileWriterInputGap.append(",");
//						fileWriterInputGap.append(service.getValue().getAccuracy()==null ? "null"
//														: service.getValue().getAccuracy().toString());
						fileWriterInputGap.append("\n");
					}
				}
			}
			
			fileWriterInputGap.append("\n");
			
			fileWriterInputGap.append("requiredServiceName,devIdList");
			fileWriterInputGap.append("\n");
			
			for(Map.Entry<String, ThingsIdList> entryEqThings: servNameThingsIdList.entrySet()){
				
				fileWriterInputGap.append(entryEqThings.getKey());
				fileWriterInputGap.append(",");

				List<String> eqThings = entryEqThings.getValue().getEqThings();
				for(String devId: eqThings){
					fileWriterInputGap.append(devId);
					fileWriterInputGap.append(",");
				}
				fileWriterInputGap.append("\n");
			}
			
			fileWriterInputGap.append("\n");
			
			fileWriterInputGap.append("transactionId::serviceName,devIdList");
			fileWriterInputGap.append("\n");
			
			for(Map.Entry<String, List<String>> entry: matrixM.entrySet()){
				
				fileWriterInputGap.append(entry.getKey());
				fileWriterInputGap.append(",");

				List<String> things = entry.getValue();
				for(String devId: things){
					fileWriterInputGap.append(devId);
					fileWriterInputGap.append(",");
				}
				fileWriterInputGap.append("\n");
			}
			
		}
		catch(Exception e){
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}
		finally{
			try {
				fileWriterInputGap.flush();
				fileWriterInputGap.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
		}
		
	}
	
	
	public void printAllocationSchema(
			HashMap<String, HashMap<String, AllocationInfo>> allocationSchema, String split) {
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
			
			//create Thing folder
			File fileABGAP = new File("./ABGAP_Results_"+split);
			if(!fileABGAP.exists()){
				fileABGAP.mkdirs();
			}
			
			output = new FileWriter(fileABGAP.getPath()+"/ResultABGAP_"+split+"_ABGAP_"+resultCounter+"_Medium_"+medium+".txt", true);
			resultCounter++;
			
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
			
			if(!allocationSchema.isEmpty()){
				for(Map.Entry<String, HashMap<String, AllocationInfo>> entry: allocationSchema.entrySet()){
					writer.println("transId: "+entry.getKey());
					writer.println("");
					
					HashMap<String, AllocationInfo> services = entry.getValue();
					
					writer.println("services allocated:");
					for(Map.Entry<String, AllocationInfo> entryAllocation: services.entrySet()){
						writer.println("service Name: "+entryAllocation.getKey());
						
						writer.println(entryAllocation.toString());
						
					}
					writer.println("");
					writer.println("");
				}
			}
			else{
				writer.println("NOT FEASIBLE");
			}
				
			writer.println("########################################");
			writer.println("########################################");

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				output.flush();
				output.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
		}
		
	}
	

	public void printServiceAgreementReq(ServiceDefinition serviceRequest){
		

		PrintWriter writer=null;
		FileWriter output = null;
		
		file = new File(STAT_DIR);
		if(!file.exists()){
			file.mkdir();
		}
		
		try{
			
			output = new FileWriter(file.getPath()+"/ServiceAgreementReq.txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
			
			writer.println("ServiceAgreementRequest");
			
			writer.println(serviceRequest.toString());
			
			
			writer.println("########################################");
			writer.println("########################################");

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				output.flush();
				output.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
		}
		
	}

	public void printNgsiResults(List<ContextRegistrationResponse> contRegList, List<ContextElementResponse> contElemList){
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		file = new File(STAT_DIR);
		if(!file.exists()){
			file.mkdir();
		}
		
		try{
			
			output = new FileWriter(file.getPath()+"/DiscoveryResults.txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
			
			writer.println("Context Registration List");
			
			writer.println(contRegList.toString());
			
			writer.println("Context Element List");
			
			writer.println(contElemList.toString());
			
			writer.println("########################################");
			writer.println("########################################");

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				output.flush();
				output.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
		}
		
	}
	
}
