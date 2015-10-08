package it.unipi.iotplatform.qosbroker.couchdb.impl;

import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import eu.neclab.iotplatform.couchdb.CreateDB;
import eu.neclab.iotplatform.couchdb.http.Client;
import eu.neclab.iotplatform.couchdb.http.HttpRequester;
import eu.neclab.iotplatform.iotbroker.commons.FullHttpResponse;
import eu.neclab.iotplatform.iotbroker.commons.Pair;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;

public class QoSCouchDB implements QoSBigDataRepository{

	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSCouchDB.class);

	@Value("${couchdb_host:localhost}")
	private String couchDB_HOST;
	@Value("${couchdb_protocol:http}")
	private String couchDB_PROTOCOL;
	@Value("${couchdb_port:5984}")
	private String couchDB_PORT;
	
	@Value("${couchdb_names}")
	private String[] couchDB_NAME;
	
	@Value("${couchdb_username:null}")
	private String couchDB_USERNAME;
	@Value("${couchdb_password:null}")
	private String couchDB_PASSWORD;
	
	public String getCouchDB_HOST() {
		return couchDB_HOST;
	}

	public void setCouchDB_HOST(String couchDB_HOST) {
		this.couchDB_HOST = couchDB_HOST;
	}

	public String getCouchDB_PROTOCOL() {
		return couchDB_PROTOCOL;
	}

	public void setCouchDB_PROTOCOL(String couchDB_PROTOCOL) {
		this.couchDB_PROTOCOL = couchDB_PROTOCOL;
	}

	public String getCouchDB_PORT() {
		return couchDB_PORT;
	}

	public void setCouchDB_PORT(String couchDB_PORT) {
		this.couchDB_PORT = couchDB_PORT;
	}

	public String getUSERNAME() {
		return couchDB_USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		couchDB_USERNAME = uSERNAME;
	}

	public String getPASSWORD() {
		return couchDB_PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		couchDB_PASSWORD = pASSWORD;
	}

	private final CreateDB couchDBtool = new CreateDB();

	private String authentication = null;

	HashMap<String, Boolean> databaseExist;

	private String couchDB_ip = null;

	public QoSCouchDB() {
		
		databaseExist = new HashMap<>();
		
		int i = 0;
		for(Map.Entry<String, Boolean> entry : databaseExist.entrySet()){
			databaseExist.put(couchDB_NAME[i], false);
			
			i++;
		}
	}

	public String getCouchDB_ip() {
		return couchDB_ip;
	}

	public void setCouchDB_ip() {
		couchDB_ip = couchDB_PROTOCOL + "://" + couchDB_HOST + ":"
				+ couchDB_PORT + "/";
		logger.info("CouchDB IP: " + couchDB_ip);
	}

	@SuppressWarnings("deprecation")
	private Boolean checkDB(String DBName){
		
		if (couchDB_ip == null) {
			setCouchDB_ip();
		}

		logger.info("Send update to the CouchDB storage...");

		if (couchDB_USERNAME != null && !couchDB_USERNAME.trim().isEmpty()
				&& couchDB_PASSWORD != null
				&& !couchDB_PASSWORD.trim().isEmpty()) {
			UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
					couchDB_USERNAME, couchDB_PASSWORD);
			authentication = BasicScheme.authenticate(creds, "US-ASCII", false)
					.toString();
		}

		if (!databaseExist.get(DBName)) {
			try {
				if (couchDBtool.checkDB(getCouchDB_ip(), DBName,
						authentication)) {

					databaseExist.put(DBName, true);

				} else {

					couchDBtool.createDb(getCouchDB_ip(), DBName,
							authentication);
					databaseExist.put(DBName, true);
				}
			} catch (MalformedURLException e) {
				logger.info("Impossible to store information into CouchDB", e);
				return false;
			}
		}
		return true;
	}
	

	@Override
	public Boolean storeData(List<Pair<String, JSONObject>> dataList, String DBName){

		if(!checkDB(DBName)){
			return false;
		}

		Iterator<Pair<String, JSONObject>> iter = dataList.iterator();
		while (iter.hasNext()) {

			Pair<String, JSONObject> data = iter.next();

			String key = data.getLeft();
			JSONObject jsonObj = data.getRight();
				
			// Store the historical data
			logger.debug("JSON Object to store:" + jsonObj.toString(2));
			try {
				FullHttpResponse dbResponse = queryDB(key);
				
				//check if the response from the DB is empty
				if(dbResponse.getBody() != null){
					JSONObject resp = new JSONObject(dbResponse.getBody());
					
					if(!resp.isNull("_rev")){
						
						jsonObj.put("_rev", resp.getString("_rev"));
					}
				}
					
				Client.sendRequest(new URL(getCouchDB_ip() + DBName
						+ "/" + key), "PUT", jsonObj.toString(),
						"application/json", authentication);
				

				
			} catch (MalformedURLException e) {
				logger.info("Impossible to store information into CouchDB",
						e);
				return false;
			}

		}
		return true;
	}

	

	public static void main(String[] args) {
		String date = "2015.05.29 19:24:28:769 +0000";
		SimpleDateFormat parserSDF = new SimpleDateFormat(
				"yyyy.MM.dd HH:mm:ss:SSS Z");
		Date timestamp = parserSDF.parse(date, new ParsePosition(0));
		System.out.println(timestamp);
	}

	@Override
	public List<Pair<String, JSONObject>> readData(List<String> keyList, String DBName){

		if(!checkDB(DBName)){
			return null;
		}
		
		List<Pair<String, JSONObject>> readDataList = new ArrayList<Pair<String, JSONObject>>();
		for(String key: keyList){
			FullHttpResponse dbResponse = queryDB(key);
	
			if(dbResponse == null){
				return null;
			}
			
			JSONObject jsonResp = new JSONObject(dbResponse.getBody());
			
			Pair<String, JSONObject> dataPair = new Pair<String, JSONObject>(key, jsonResp);
			
			readDataList.add(dataPair);
		}
		
		return readDataList;
	}

	
	/**
	 * It returns result of a view in couchDB
	 * 
	 * @param queryName
	 * @param documentType
	 * @return
	 */
	private FullHttpResponse queryDB(String key) {
		
		FullHttpResponse response = null;
		try {

			response = HttpRequester.sendGet(new URL(couchDB_ip + "/"
					+ couchDB_NAME + "/" + key));
		} catch (MalformedURLException e) {
			logger.error("Error: ", e);
			return null;
		} catch (Exception e) {
			logger.error("Error: ", e);
			return null;
		}

		return response;
	}

	public String[] getCouchDB_NAME() {
		return couchDB_NAME;
	}

	public void setCouchDB_NAME(String[] couchDB_NAME) {
		this.couchDB_NAME = couchDB_NAME;
	}


}
