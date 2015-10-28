package it.unipi.iotplatform.restcontroller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class StopThread implements Runnable{


	private ScheduledFuture<Boolean> fSchedFuture;

	private ScheduledExecutorService scheduledExecutorService;
	
	private ResultMerger merger;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try{
			if(!fSchedFuture.get()){
				fSchedFuture.cancel(false);
				
				scheduledExecutorService.shutdown();
				
				PrintWriter writer=null;
				FileWriter output = null;
				
				try{
					output = new FileWriter("/home/lorenzo/Desktop/RestClient/testResults.txt", true);
					writer = new PrintWriter(output);
					writer.println("####################################");
					writer.println("####################################");
					
					writer.println("Final Results: ");
					
					synchronized(merger){
						writer.println("Feasible allocations: "+merger.getHowManyFeasible());
						
						writer.println("Arrival Times: "+merger.getArrivalTime());
					}
					
					writer.println("####################################");
					writer.println("####################################");
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
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void setfSchedFuture(ScheduledFuture<Boolean> fSchedFuture) {
		this.fSchedFuture = fSchedFuture;
	}

	public StopThread(ScheduledFuture<Boolean> fSchedFuture,
			ScheduledExecutorService scheduledExecutorService,
			ResultMerger merger) {

		this.fSchedFuture = fSchedFuture;
		this.scheduledExecutorService = scheduledExecutorService;
		this.merger = merger;
	}

}
