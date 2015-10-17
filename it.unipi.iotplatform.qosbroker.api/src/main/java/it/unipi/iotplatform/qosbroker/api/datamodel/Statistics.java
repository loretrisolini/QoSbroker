package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.Circle;
import eu.neclab.iotplatform.ngsi.api.datamodel.Point;
import eu.neclab.iotplatform.ngsi.api.datamodel.Polygon;
import eu.neclab.iotplatform.ngsi.api.datamodel.Vertex;

public class Statistics{
	
	public static int count = 0;
	public static Double r = new Random().nextDouble();
	public static String testFolder = "testFolder" + r.toString();
	
	public static void printThingsMappings(Request request, HashMap<String, Thing> thingsInfo,
			HashMap<String, ThingsIdList> serviceEquivalentThings) {
		
		FileWriter fileWriterThingsMappings = null;
		
		try{
			count++;
			
			fileWriterThingsMappings = new FileWriter("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/Tests/Things"+count+".csv");
			
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
			
				fileWriterThingsMappings.append("DevId,ServiceName,Latency,EnergyCost,Accuracy");
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
						fileWriterThingsMappings.append(",");
						fileWriterThingsMappings.append(service.getValue().getAccuracy()==null ? "null"
														: service.getValue().getAccuracy().toString());
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
	public static void printInputGap(int k, List<Pair<String, Request>> requests,
			HashMap<String,HashMap<String,List<NormalizedEnergyCost>>> matrixF_ij,
			HashMap<String,HashMap<String,List<Utilization>>> matrixU_ij,
			HashMap<String, ServicePeriodParams> servPeriodsMap,
			HashMap<String, Thing> eqThingInfo,
			HashMap<String, ThingsIdList> servNameThingsIdList,
			HashMap<String, List<String>> matrixM, double teta, String prio,
			String policy) {
		
		FileWriter fileWriterInputGap=null;
		
		try{
			fileWriterInputGap = new FileWriter("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/Tests/InputGap"+count+".csv");
			
			fileWriterInputGap.append("k,Teta,Priority,Policy");
			fileWriterInputGap.append("\n");
			
			fileWriterInputGap.append(String.valueOf(k));
			fileWriterInputGap.append(",");
			fileWriterInputGap.append(String.valueOf(teta));
			fileWriterInputGap.append(",");
			fileWriterInputGap.append(prio);
			fileWriterInputGap.append(",");
			fileWriterInputGap.append(policy);
			fileWriterInputGap.append("\n");
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
				Polygon polygon = null;
				
				if(req.getLocationRequirement() != null){
					//take the location requirement from the LocationRequirement object in the request object
					Class<?> locRequirementsType = req.getLocationRequirement().getLocationRequirement().getClass();
					
					if(locRequirementsType == Point.class){
						point = (Point)req.getLocationRequirement().getLocationRequirement();
						fileWriterInputGap.append("Point: "+String.valueOf(point.getLatitude())+" "+String.valueOf(point.getLongitude()));
					}
					else{
						if(locRequirementsType == Circle.class){
							circle = (Circle)req.getLocationRequirement().getLocationRequirement();
							fileWriterInputGap.append("Circle: cLat: "+String.valueOf(circle.getCenterLatitude())+" | cLon: "+String.valueOf(circle.getCenterLongitude())
												+" | rad: "+String.valueOf(circle.getRadius()));
						}
						else{
							polygon = (Polygon)req.getLocationRequirement().getLocationRequirement();
							
							List<Vertex> vertexList = polygon.getVertexList();
							fileWriterInputGap.append("Polygon: ");
							for(Vertex v: vertexList){
								fileWriterInputGap.append("vLat: "+String.valueOf(v.getLatitude())+" vLon: "+String.valueOf(v.getLongitude())+" | ");
							}
						}
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
			for(Map.Entry<String,HashMap<String,List<NormalizedEnergyCost>>> entryF_ij: matrixF_ij.entrySet()){
				
				String devId = entryF_ij.getKey();
				
				HashMap<String,List<NormalizedEnergyCost>> transIdServ = entryF_ij.getValue();
				
				for(Map.Entry<String,List<NormalizedEnergyCost>> entryNeC: transIdServ.entrySet()){
					
					String transId = entryNeC.getKey();
					
					List<NormalizedEnergyCost> normEnCost = entryNeC.getValue();
					
					for(NormalizedEnergyCost neC: normEnCost){
						fileWriterInputGap.append(devId);
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(transId);
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(neC.getService());
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(String.valueOf(neC.getF_ij()));
						fileWriterInputGap.append("\n");
					}
				}
			}
			
			fileWriterInputGap.append("\n");
			fileWriterInputGap.append("devId, transId, service, u_ij");
			fileWriterInputGap.append("\n");
			for(Map.Entry<String,HashMap<String,List<Utilization>>> entryU_ij: matrixU_ij.entrySet()){
				
				String devId = entryU_ij.getKey();
				
				HashMap<String,List<Utilization>> transIdServ = entryU_ij.getValue();
				
				for(Map.Entry<String,List<Utilization>> entryUt: transIdServ.entrySet()){
					
					String transId = entryUt.getKey();
					
					List<Utilization> utList = entryUt.getValue();
					
					for(Utilization u: utList){
						fileWriterInputGap.append(devId);
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(transId);
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(u.getService());
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(String.valueOf(u.getU_ij()));
						fileWriterInputGap.append("\n");
					}
				}
			}
			
			fileWriterInputGap.append("\n");
			fileWriterInputGap.append("TransactionID, h/p_j,p_j");
			fileWriterInputGap.append("\n");
			for(Map.Entry<String, ServicePeriodParams> entryPeriod: servPeriodsMap.entrySet()){
				fileWriterInputGap.append(entryPeriod.getKey());
				fileWriterInputGap.append(",");
				fileWriterInputGap.append(String.valueOf(entryPeriod.getValue().getNj()));
				fileWriterInputGap.append(",");
				fileWriterInputGap.append(String.valueOf(entryPeriod.getValue().getPeriod()));
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
			
				fileWriterInputGap.append("DevId,ServiceName,Latency,EnergyCost,Accuracy");
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
						fileWriterInputGap.append(",");
						fileWriterInputGap.append(service.getValue().getAccuracy()==null ? "null"
														: service.getValue().getAccuracy().toString());
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
	
	
	public static void printAllocationSchema(
			HashMap<String, HashMap<String, AllocationObj>> allocationSchema) {
		
		PrintWriter writer=null;
		
		try{
			writer = new PrintWriter("/home/lorenzo/Downloads/FIWARE-WORK/git/QoSbroker/Tests/ResultGap"+count+".txt", "UTF-8");
			writer.println("####################################");
			writer.println("####################################");
			
			for(Map.Entry<String, HashMap<String, AllocationObj>> entry: allocationSchema.entrySet()){
				writer.println("transId: "+entry.getKey());
				writer.println("");
				
				HashMap<String, AllocationObj> services = entry.getValue();
				
				writer.println("services allocated:");
				for(Map.Entry<String, AllocationObj> entryAllocation: services.entrySet()){
					writer.println("service Name: "+entryAllocation.getKey());
					
					writer.println(entryAllocation.toString());
					
				}
				writer.println("");
				writer.println("");
			}
			
			writer.println("########################################");
			writer.println("########################################");
			writer.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
