package it.unipi.iotplatform.restcontroller;

import it.unipi.iotplatform.qosbroker.api.datamodel.QoSCode;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementRequest;
import it.unipi.iotplatform.qosbroker.api.datamodel.ServiceAgreementResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import eu.neclab.iotplatform.iotbroker.commons.XmlFactory;

public class RequestThread implements Callable<Boolean>{

	public final static String CREATE_AGREEMENT_URL = "http://localhost:8090/ngsi10/createAgreement";
	
	
	private ServiceAgreementRequest servAgreementRequest;
	private int reqId;
	private ResultMerger merger;
	
	public RequestThread(int req, ServiceAgreementRequest servAgrReq, ResultMerger resultMerger){
		
		reqId = req;
		servAgreementRequest = servAgrReq;
		merger = resultMerger;
	}
	
	@Override
    public Boolean call() throws InterruptedException{
		
		System.out.println("Start Request number: "+reqId);
		
		System.out.println("Services Requested: ");
		System.out.println(servAgreementRequest.getServiceDefinitionList().get(0).getAttributeList());
		
    	try {

			DefaultHttpClient httpClient = new DefaultHttpClient();

			HttpPost postRequest = new HttpPost(CREATE_AGREEMENT_URL);

			StringEntity input = new StringEntity(servAgreementRequest.toString());

			input.setContentType("application/xml");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			String result = "";
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				
					result += output;
			}
			
			boolean allocResult = false;
			
			if(result.isEmpty()){
				System.out.println("QoSBroker response null");
				allocResult = false;
			}
			else{
				ServiceAgreementResponse servResponse = (ServiceAgreementResponse)
					(new XmlFactory()).convertStringToXml(result, ServiceAgreementResponse.class);
				
				httpClient.getConnectionManager().shutdown();
				
				if(servResponse == null){
					System.out.println("error conversion QoSBroker response");
					
					allocResult = false;
				}
				
				if(servResponse.getErrorCode().getCode() == QoSCode.SERVICEALLOCATIONFAILED_502.getCode() || 
						servResponse.getErrorCode().getCode() != QoSCode.OK_200.getCode()){
					System.out.println(servResponse.getErrorCode().getDetails());
					
					allocResult = false;
				}
				else{
					allocResult = true;
				}

			}
			
			synchronized(merger){
				if(allocResult)
					merger.setResult();
				else{
					throw new InterruptedException();

				}
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
    	
    	return false;
    }
	

}
