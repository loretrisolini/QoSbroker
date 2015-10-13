package it.unipi.iotplatform.qosbroker.couchdb.impl;

import it.unipi.iotplatform.qosbroker.api.datamodel.QoSConsts;
import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

public class QoSCouchDB implements QoSBigDataRepository{

	/** The logger. */
	private static Logger logger = Logger.getLogger(QoSCouchDB.class);

	@Value("${couchdb_host:localhost}")
	private String couchDB_HOST;
	@Value("${couchdb_protocol:http}")
	private String couchDB_PROTOCOL;
	@Value("${couchdb_port:5984}")
	private String couchDB_PORT;
	
//	@Value("${couchdb_names}")
//	private String[] couchDB_NAME;

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

	HashMap<String, Boolean> databaseExist = new HashMap<>();

	private String couchDB_ip = null;

	public QoSCouchDB() {
	}

	public String getCouchDB_ip() {
		return couchDB_ip;
	}

	public void setCouchDB_ip() {
		couchDB_ip = couchDB_PROTOCOL + "://" + couchDB_HOST + ":"
				+ couchDB_PORT + "/";
		logger.info("CouchDB IP: " + couchDB_ip);
	}

	/* function to check if a DB with a name DBName exists.
	 * If it doesn't exist, it is created */
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

		if (databaseExist.get(DBName) == null) {
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
	
	/* function to store data in the DB with DBName given
	 * the list of pairs <Key,JSONObject> */
	@Override
	public Boolean storeData(List<Pair<String, JSONObject>> dataList, String DBName){

		if(!checkDB(DBName)){
			return false;
		}
		
		//itearte over the list of data
		Iterator<Pair<String, JSONObject>> iter = dataList.iterator();
		while (iter.hasNext()) {

			Pair<String, JSONObject> data = iter.next();

			//take the key and the jsonObj
			//to store
			String key = data.getLeft();
			JSONObject jsonObj = data.getRight();
				
			// Store the historical data
			logger.debug("JSON Object to store:" + jsonObj.toString(2));
			try {
				//check if the data with the key if already
				//stored, if it is stored, it is taken
				//the _rev to update the data already stored
				FullHttpResponse dbResponse = queryDB(key, DBName);
				
				//check if the response from the DB is empty
				if(dbResponse.getBody() != null){
					JSONObject resp = new JSONObject(dbResponse.getBody());
					
					if(!resp.isNull("_rev")){
						//take the _rev
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

	/* function to read data from the DB with DBName given the list of keys */
	@Override
	public List<Pair<String, JSONObject>> readData(List<String> keyList, String DBName){

		if(!checkDB(DBName)){
			return null;
		}
		
		//iterate over the list of keys to read data from the DB with name DBName
		List<Pair<String, JSONObject>> readDataList = new ArrayList<Pair<String, JSONObject>>();
		List<String> keys = keyList;
		
		//read all content of the DB with name 
		//DBName
		if(keys == null){
			keys = new ArrayList<>();
			
			//read all elements in DBName
			FullHttpResponse dbResponse = queryDB(null, DBName);
			
			if(dbResponse == null){
				//error
				return null;
			}
			if(dbResponse.getBody() == null){
				//element not found
				return readDataList;
			}
			
			JSONObject jsonResp = new JSONObject(dbResponse.getBody());
			
			if(jsonResp.isNull(QoSConsts.COUCHDB_TOTROWS)){
				//error in reading DBName
				return null;
			}
			else{
				//read the total rows in the response
				//if zero the db is empty
				if(jsonResp.getInt(QoSConsts.COUCHDB_TOTROWS) == 0)
					return readDataList;
			}
			
			//read all keys of the elements in the db
			//this is done to read all elements
			//given the list of keys
			JSONArray rows = jsonResp.getJSONArray(QoSConsts.COUCHDB_ROWS);
			
			for(int i = 0; i < rows.length(); i++){
				
				if(!rows.getJSONObject(i).isNull(QoSConsts.COUCHDB_KEY))
					keys.add(rows.getJSONObject(i).getString(QoSConsts.COUCHDB_KEY));
			}
		}
		

		for(String key: keys){
			FullHttpResponse dbResponse = queryDB(key, DBName);
	
			if(dbResponse == null){
				//error
				return null;
			}
			if(dbResponse.getBody() == null){
				//element not found
				continue;
			}
			
			JSONObject jsonResp = new JSONObject(dbResponse.getBody());
			
			//remove _id and _rev from the jsonResponse
			//and add _id and _rev to Key
			String _id = new String();
			String _rev = new String();
			if(!jsonResp.isNull(QoSConsts.COUCHDB_ID) && !jsonResp.isNull(QoSConsts.COUCHDB_REV)){
				_id = jsonResp.remove(QoSConsts.COUCHDB_ID).toString();
				_rev = jsonResp.remove(QoSConsts.COUCHDB_REV).toString();
			}
				
			//key founded in the DB as _id,_rev
			String foundedKey = new String(_id);// + "||" + _rev);
			
			Pair<String, JSONObject> dataPair = new Pair<String, JSONObject>(foundedKey, jsonResp);
			
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
	private FullHttpResponse queryDB(String key, String DBName) {
		
		FullHttpResponse response = null;
		try {

			if(key == null){
				
				//read all the keys of the docs
				//in the db
				response = HttpRequester.sendGet(new URL(couchDB_ip + "/"
						+ DBName + "/" + QoSConsts.COUCHDB_ALLDOCS));
			}
			else{
				//read an element with a key
				response = HttpRequester.sendGet(new URL(couchDB_ip + "/"
						+ DBName + "/" + key));
			}
		} catch (MalformedURLException e) {
			logger.error("Error: ", e);
			return null;
		} catch (Exception e) {
			logger.error("Error: ", e);
			return null;
		}

		return response;
	}

//	public String[] getCouchDB_NAME() {
//		return couchDB_NAME;
//	}
//
//	public void setCouchDB_NAME(String[] couchDB_NAME) {
//		this.couchDB_NAME = couchDB_NAME;
//	}


}
