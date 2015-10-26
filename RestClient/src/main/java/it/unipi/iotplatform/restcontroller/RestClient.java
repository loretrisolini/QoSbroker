package it.unipi.iotplatform.restcontroller;

import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementRequest;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.neclab.iotplatform.iotbroker.commons.XmlFactory;

public class RestClient {

	public static void main(String[] args) {

		if(args.length < 1){
			System.out.println("no serviceAgreement request");
			return;
		}
		
		ArrayList<ServiceAgreementRequest> servRequestsList = new ArrayList<>();
		
		for(int i = 0; i < args.length; i++){
			
			ServiceAgreementRequest servAgr = (ServiceAgreementRequest)
					(new XmlFactory()).convertFileToXML("/home/lorenzo/workspace/RestClient/src/main/resources/"+args[i]+".xml", ServiceAgreementRequest.class);
			
			servRequestsList.add(servAgr);			

		}

		int j = 0;

		long t0 = System.currentTimeMillis();
		
		ResultMerger allocationResult = new ResultMerger();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		long startInterval = 5000L;

		while(true){
			
			try{
				Thread.sleep(startInterval);
				
				
				
				Callable<Boolean> req = new RequestThread(j, 
						servRequestsList.get(j%servRequestsList.size()), allocationResult);
				
				Future<Boolean> future = pool.submit(req);
				
				future.get();
				
				future.cancel(false);
				
				//startInterval += 5000L;
				j++;
			}
			catch(InterruptedException ie){
				
				System.out.println("ALLOCATION "+j+" FAILED");
				break;
			}
			catch(ExecutionException ee){
				ee.printStackTrace();
				break;
			}
		}
		
		long t1 = System.currentTimeMillis();
			
		System.out.println("Allocated Services: "+ allocationResult.getHowManyFeasible());
		System.out.println("Time Interval: "+ (t1 - t0));
		
		pool.shutdown();
	}
	
}
