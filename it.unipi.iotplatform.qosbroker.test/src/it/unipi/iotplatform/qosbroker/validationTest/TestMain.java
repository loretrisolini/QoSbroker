package it.unipi.iotplatform.qosbroker.validationTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestMain {

	public static void main(String[] args){
		
		if(args.length < 3){
			System.out.println("Error num of params not correct");
			return;
		}
		
		File fileTest = new File("./TestResults");
		if(!fileTest.exists()){
			fileTest.mkdir();
		}
		
		PrintWriter writer=null;
		FileWriter output = null;
		
		Long seed = 0L;
		int k = 0;
		int n = 0;
		
		//read the seed
		if(args[0].contentEquals("null")){
			System.out.println("no seed read, creation of the seed");
			seed = System.currentTimeMillis();
		}
		else{
			seed = Long.valueOf(args[0]);
		}
		
		k = Integer.parseInt(args[1]);
		
		n = Integer.parseInt(args[2]);
		
		try{
		
			output = new FileWriter(fileTest.getAbsolutePath()+"/testInfo.txt", true);
			writer = new PrintWriter(output);
			
			System.out.println("Seed: "+seed);
			writer.println("Seed: "+seed);

			System.out.println("requests: "+k);
			writer.println("requests: "+k);

			System.out.println("thingsNumber: "+n);
			writer.println("thingsNumber: "+n);
			
			System.out.println("Parameters of the test set");
			writer.println("Parameters of the test set");
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
		
		Test5 test = new Test5(seed, k, n);

		test.allocationTest();

	}
}
