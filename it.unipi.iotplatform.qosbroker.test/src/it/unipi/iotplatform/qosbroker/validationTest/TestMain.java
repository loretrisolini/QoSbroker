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
		
		Long seed;
		int k;
		int n;
		
		try{
		
			output = new FileWriter(fileTest.getAbsolutePath()+"/testInfo.txt", true);
			writer = new PrintWriter(output);
			
			//read the seed
			if(args[0].contentEquals("null")){
				System.out.println("no seed read, creation of the seed");
				seed = System.currentTimeMillis();
			}
			else{
				seed = Long.valueOf(args[0]);
			}
			
			System.out.println("Seed: "+seed);
			writer.println("Seed: "+seed);
			
			k = Integer.parseInt(args[1]);
			
			System.out.println("requests: "+k);
			writer.println("requests: "+k);
			
			n = Integer.parseInt(args[2]);
			System.out.println("thingsNumber: "+n);
			writer.println("thingsNumber: "+n);
			
			System.out.println("Parameters of the test set");
			writer.println("Parameters of the test set");
			writer.println("####################################");
			
			Test4 test = new Test4(seed, k, n);

			test.allocationTest();
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
