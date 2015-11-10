package it.unipi.iotplatform.qosbroker.validationTest;

import it.unipi.iotplatform.qosbroker.threadHeuristic.TestThread3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test3 {

private final int REQ_PERIOD = 1;
	
	//seed
	//#reqs 
	//#requiresServ <-- assumptions 1 required service per request
	//#TotalNumber of services all different
	public void test(Long seed, int k, int n) {

				
		ScheduledExecutorService scheduledExecutorService =
		        Executors.newScheduledThreadPool(1);
		
		Runnable run = new TestThread3(
				seed,
				k,
				n,
				scheduledExecutorService);

		scheduledExecutorService.scheduleWithFixedDelay(run, 0, REQ_PERIOD, TimeUnit.SECONDS);			
	}
}
