package it.unipi.iotplatform.restcontroller;

import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementRequest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import eu.neclab.iotplatform.iotbroker.commons.XmlFactory;

public class RestClient {

	private static int r = 0;
	private static File file;
	
	public static void main(String[] args) {

		if(args.length < 1){
			System.out.println("specify serviceAgreement request files");
			return;
		}
		
		String split = args[0];
		
		if(!split.contentEquals("single") && !split.contentEquals("multi")){
			
			System.out.println("no split specification");
			return;
		}
		
		setTestDir();
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		try{
			
			output = new FileWriter(file.getPath()+"/result"+r+".txt", true);
			writer = new PrintWriter(output);
			writer.println("####################################");
			writer.println("####################################");
		
			ArrayList<ServiceAgreementRequest> servRequestsList = new ArrayList<>();
			
			for(int i = 0; i < args.length; i++){
				
				ServiceAgreementRequest servAgr = (ServiceAgreementRequest)
						(new XmlFactory()).convertFileToXML("./src/test/resources/"+args[i]+".xml", ServiceAgreementRequest.class);
				
				servRequestsList.add(servAgr);			
	
			}
	
			int j = 0;
	
			long t0 = System.currentTimeMillis();
			
			ResultMerger allocationResult = new ResultMerger();
			
			ExecutorService pool = Executors.newCachedThreadPool();
			
			long startInterval = 0L;
	
			while(true){
				
				try{
					Thread.sleep(startInterval);
					
					Callable<Boolean> req = new RequestThread(j, 
							servRequestsList.get(j%servRequestsList.size()), allocationResult);
					
					Future<Boolean> future = pool.submit(req);
					
					if(!future.get()) break;
					
					future.cancel(false);
					
					if(j==0) startInterval += 5000L;
					
					j++;
				}
				catch(InterruptedException ie){
					
					ie.printStackTrace();
					break;
				}
				catch(ExecutionException ee){
					ee.printStackTrace();
					break;
				}
			}
			
			long t1 = System.currentTimeMillis();
			
			System.out.println("Feasible requests: "+ allocationResult.getHowManyFeasible());
			System.out.println("split: "+split);
			System.out.println("Time Interval: "+ (t1 - t0));
			System.out.println("Service request Period: "+ startInterval/1000 +"sec");
			
			writer.println("Allocated Services: "+ allocationResult.getHowManyFeasible());
			writer.println("Time Interval: "+ (t1 - t0));
			writer.println("Service request Period: "+ startInterval/1000 +"sec");
			
			pool.shutdown();
			
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
	
	private static void setTestDir() {
		
		r++;
		
		file = new File("/home/lorenzo/Desktop/RestClient/");
		
	}
	
}
