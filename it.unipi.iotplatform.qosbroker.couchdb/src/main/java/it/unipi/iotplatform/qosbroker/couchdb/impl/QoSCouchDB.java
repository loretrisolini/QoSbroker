package it.unipi.iotplatform.qosbroker.couchdb.impl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import eu.neclab.iotplatform.couchdb.CouchDB;
import eu.neclab.iotplatform.couchdb.CreateDB;
import eu.neclab.iotplatform.couchdb.http.Client;
import eu.neclab.iotplatform.couchdb.http.HttpRequester;
import eu.neclab.iotplatform.iotbroker.commons.FullHttpResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextMetadata;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;
import it.unipi.iotplatform.qosbroker.couchdb.api.QoSBigDataRepository;

public class QoSCouchDB implements QoSBigDataRepository{

	/** The logger. */
	private static Logger logger = Logger.getLogger(CouchDB.class);

	@Value("${couchdb_host:localhost}")
	private String couchDB_HOST;
	@Value("${couchdb_protocol:http}")
	private String couchDB_PROTOCOL;
	@Value("${couchdb_port:5984}")
	private String couchDB_PORT;
//	@Value("${couchdb_name:iotbrokerdb}")
	private String couchDB_NAME = "QoSbrokerdb";
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

	public String getCouchDB_NAME() {
		return couchDB_NAME;
	}

	public void setCouchDB_NAME(String couchDB_NAME) {
		this.couchDB_NAME = couchDB_NAME;
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

	boolean databaseExist = false;

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

	@SuppressWarnings("deprecation")
	@Override
	public void storeData(List<ContextElement> contextElementList) {

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

		if (!databaseExist) {
			try {
				if (couchDBtool.checkDB(getCouchDB_ip(), couchDB_NAME,
						authentication)) {

					databaseExist = true;

				} else {

					couchDBtool.createDb(getCouchDB_ip(), couchDB_NAME,
							authentication);
					databaseExist = true;
				}
			} catch (MalformedURLException e) {
				logger.info("Impossible to store information into CouchDB", e);
				return;
			}
		}

		Iterator<ContextElement> iter = contextElementList.iterator();
		while (iter.hasNext()) {

			ContextElement contextElement = iter.next();

			// Create a list of ContextElement where each ContextElement has
			// only one ContextAttribute
			List<ContextElement> isolatedContextElementList = this
					.isolateAttributes(contextElement);

			// Iterate over the list
			for (ContextElement isolatedContextElement : isolatedContextElementList) {

				// Extract the timestamp from the ContextAttribute
				Date timestamp = extractTimestamp(isolatedContextElement
						.getContextAttributeList().iterator().next());

				// If no timestamp is found, take the actual one.
				if (timestamp == null) {
					timestamp = new Date();
				}

				// Generate the documentKey for historical data
//				String documentKey = this.generateKeyForHistoricalData(
//						contextElement.getEntityId(), contextElement
//								.getContextAttributeList().iterator().next()
//								.getName(), timestamp);

//				JSONObject xmlJSONObj = XML.toJSONObject(contextElement
//						.toString());
				
				JSONObject xmlJSONObj = fromContextElementToJson(contextElement);
				
				// Store the historical data
				logger.debug("JSON Object to store:" + xmlJSONObj.toString(2));
				try {
					FullHttpResponse dbResponse = queryDB(contextElement.getEntityId());
					
					//check if the response from the DB is empty
					if(dbResponse.getBody() != null){
						JSONObject resp = new JSONObject(dbResponse.getBody());
						
						if(!resp.isNull("_rev")){
							
							xmlJSONObj.put("_rev", resp.getString("_rev"));
						}
					}
						
					Client.sendRequest(new URL(getCouchDB_ip() + couchDB_NAME
							+ "/" + contextElement.getEntityId().getId()), "PUT", xmlJSONObj.toString(),
							"application/json", authentication);
					

					
				} catch (MalformedURLException e) {
					logger.info("Impossible to store information into CouchDB",
							e);
				}

				// TODO update latest
				// update latest

			}

		}

	}

	private JSONObject fromContextElementToJson(ContextElement contextElement) {

		JSONObject contexElemJSONObj = new JSONObject();
		
		JSONObject entityId = new JSONObject();
		
		entityId.put("id", contextElement.getEntityId().getId());
		entityId.put("type", contextElement.getEntityId().getType());
		entityId.put("isPattern", contextElement.getEntityId().getIsPattern());
		
		contexElemJSONObj.put("entityId", entityId);
		
		JSONArray attributes = new JSONArray();
		
		List<ContextAttribute> contAttrList = contextElement.getContextAttributeList();
		
		for(ContextAttribute contAttr: contAttrList){
			
			JSONObject contAttrJSONObj = new JSONObject();
			
			contAttrJSONObj.put("name", contAttr.getName());
			contAttrJSONObj.put("type", contAttr.getType());
			contAttrJSONObj.put("contextValue", contAttr.getcontextValue());
			
			attributes.put(contAttrJSONObj);
		}
		
		contexElemJSONObj.put("attributes", attributes);
		
		//return the ContElem enclosed in a contextElement Json object
		//because when it is read is more easy to identify the
		//elements of a complex structure ContextElement
		return (new JSONObject()).put("contextElement", contexElemJSONObj);
	}

	private String generateKeyForHistoricalData(EntityId entityId,
			String attributeName, Date timestamp) {

		// example: obs_urn:x-iot:smartsantander:1:3301|2015-05-08 16:36:22

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd%20HH:mm:ss");

		String key = new String(String.format("obs_%s:%s|%s", entityId.getId(),
				attributeName, dateFormat.format(timestamp)));

		return key;

	}

	/**
	 * This method create a list of ContextElement, one for each
	 * ContextAttribute in the original ContextElement. The new
	 * ContextAttributes will have duplicated DomainMetadata and EntityID. This
	 * is necessary in order to store historical data and make historical query
	 * of a specified attribute.
	 * 
	 * @param contextElement
	 * @return
	 */
	private List<ContextElement> isolateAttributes(ContextElement contextElement) {

		List<ContextElement> contextElementList = new ArrayList<ContextElement>();

		if (contextElement.getContextAttributeList().size() > 1) {
			contextElementList.add(contextElement);
		} else {

			for (ContextAttribute contextAttribute : contextElement
					.getContextAttributeList()) {
				List<ContextAttribute> contextAttributeList = new ArrayList<ContextAttribute>();
				contextAttributeList.add(contextAttribute);
				contextElementList.add(new ContextElement(contextElement
						.getEntityId(),
						contextElement.getAttributeDomainName(),
						contextAttributeList, contextElement
								.getDomainMetadata()));
			}
		}

		return contextElementList;

	}

	private Date extractTimestamp(ContextAttribute contextAttribute) {

		Date timestamp = null;

		if(contextAttribute.getMetadata() != null){
			for (ContextMetadata contextMetadata : contextAttribute.getMetadata()) {
	
				if (contextMetadata.getName().equalsIgnoreCase("creation_time")) {
	
					/*
					 * This contextMetadata is set by the leafengine connector
					 */
	
					// example timestamp "2015.05.29 19:24:28:769 +0000"
					// "yyyy.MM.dd HH:mm:ss:SSS Z"
	
					SimpleDateFormat parserSDF = new SimpleDateFormat(
							"yyyy.MM.dd HH:mm:ss:SSS Z");
					timestamp = parserSDF.parse(
							(String) contextMetadata.getValue(), new ParsePosition(
									0));
					break;
				}
	
			}
		}

		return timestamp;
	}

	public static void main(String[] args) {
		String date = "2015.05.29 19:24:28:769 +0000";
		SimpleDateFormat parserSDF = new SimpleDateFormat(
				"yyyy.MM.dd HH:mm:ss:SSS Z");
		Date timestamp = parserSDF.parse(date, new ParsePosition(0));
		System.out.println(timestamp);
	}

	@Override
	public List<ContextElementResponse> getEntityLatestValues(EntityId entityId) {

		// TODO Auto-generated method stub

		FullHttpResponse dbResponse = queryDB(entityId);

		JSONObject resp = new JSONObject(dbResponse.getBody());
		
		if(!resp.isNull("contextElement")){
			
			ContextElementResponse contextResponse = fromJsonToContextElementResponse(resp.getJSONObject("contextElement"));
			
			List<ContextElementResponse> contextElementList = new ArrayList<>();
			
			contextElementList.add(contextResponse);
			
			return contextElementList;
		}
		else{
			return null;
		}
		
	}

	private ContextElementResponse fromJsonToContextElementResponse(
			JSONObject contElemJson) {
		
		ContextElementResponse contextElemResp = new ContextElementResponse();
		
		ContextElement contextElem = new ContextElement();
		
		//read values for EntityId structure
		EntityId entId = new EntityId();
		
		entId.setId(contElemJson.getJSONObject("entityId").getString("id"));
		
		entId.setType(URI.create(contElemJson.getJSONObject("entityId").getString("type")));
		
		entId.setIsPattern(contElemJson.getJSONObject("entityId").getBoolean("isPattern"));
		
		contextElem.setEntityId(entId);
		
		//read values for ContextAttributeList
		List<ContextAttribute> contAttrList = new ArrayList<>();
		
		JSONArray attributes = contElemJson.getJSONArray("attributes");
		
		for(int i=0; i < attributes.length(); i++){
			
			JSONObject attr = attributes.getJSONObject(i);
			
			ContextAttribute contAttr = new ContextAttribute();
			
			contAttr.setName(attr.getString("name"));
			
			contAttr.setType(URI.create(attr.getString("type")));
			
			contAttr.setcontextValue(String.valueOf(attr.get("contextValue")));
			
			contAttrList.add(contAttr);
		}
		
		contextElem.setEntityId(entId);
		contextElem.setContextAttributeList(contAttrList);
		
		//set ContextElement Response
		contextElemResp.setContextElement(contextElem);
		
		return contextElemResp;
	}

	/**
	 * It returns result of a view in couchDB
	 * 
	 * @param queryName
	 * @param documentType
	 * @return
	 */
	private FullHttpResponse queryDB(EntityId entityId) {
		
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

		if (!databaseExist) {
			try {
				if (couchDBtool.checkDB(getCouchDB_ip(), couchDB_NAME,
						authentication)) {

					databaseExist = true;

				} else {

					couchDBtool.createDb(getCouchDB_ip(), couchDB_NAME,
							authentication);
					databaseExist = true;
				}
			} catch (MalformedURLException e) {
				logger.info("Impossible to store information into CouchDB", e);
				return null;
			}
		}
		
		FullHttpResponse response = null;
		try {

			response = HttpRequester.sendGet(new URL(couchDB_ip + "/"
					+ couchDB_NAME + "/" + entityId.getId()));
		} catch (MalformedURLException e) {
			logger.error("Error: ", e);
		} catch (Exception e) {
			logger.error("Error: ", e);
		}

		return response;

	}


}
