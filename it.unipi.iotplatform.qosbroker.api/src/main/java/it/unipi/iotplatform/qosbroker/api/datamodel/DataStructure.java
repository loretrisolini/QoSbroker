package it.unipi.iotplatform.qosbroker.api.datamodel;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Node;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextAttribute;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;

/**
 *  Common super-type for NGSI data structure implementations.
 */
public abstract class DataStructure {

	/** The logger. */
	private static Logger logger = Logger.getLogger(DataStructure.class);
	
	@Override
	public String toString() {

		String result;
		StringWriter sw = new StringWriter();
		try {
			JAXBContext carContext = JAXBContext.newInstance(this.getClass());
			Marshaller carMarshaller = carContext.createMarshaller();
			carMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			carMarshaller.marshal(this, sw);
			result = sw.toString();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

		return result;

	}

	public static Object convertStringToXml(String xml, Class<?> type) {

		if (type.getSuperclass() != DataStructure.class) {
			throw new RuntimeException("Cannot convert String to "
					+ type.getName());
		}

		Object response = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			response = unmarshaller.unmarshal(reader);

		} catch (JAXBException e) {
			logger.info("JAXBException", e);
		}

		return response;

	}
	
	@SuppressWarnings("unchecked")
	public static <T> T convertObjectToJaxbObject(Node object, T JaxbObject, Class<?> type) {

		if (JaxbObject.getClass() != type) {
			throw new RuntimeException("JaxbObject type must be equal to type "+ type.getCanonicalName());
		}
		
		logger.debug(JaxbObject.getClass().getCanonicalName());
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(JaxbObject.getClass());
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			JaxbObject = (T)unmarshaller.unmarshal(object);

		} catch (JAXBException e) {
			logger.info("JAXBException", e);
		}

		return JaxbObject;

	}

	public static JSONObject fromContextElementToJson(ContextElement contextElement) {

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
	
	public static ContextElementResponse fromJsonToContextElementResponse(
			JSONObject contElemJson) {
		
		ContextElementResponse contextElemResp = new ContextElementResponse();
		
		ContextElement contextElem = new ContextElement();
		
		//read values for EntityId structure
		EntityId entId = new EntityId();
		
		entId.setId(contElemJson.getJSONObject("contextElement").getJSONObject("entityId").getString("id"));
		
		entId.setType(URI.create(contElemJson.getJSONObject("contextElement").getJSONObject("entityId").getString("type")));
		
		entId.setIsPattern(contElemJson.getJSONObject("contextElement").getJSONObject("entityId").getBoolean("isPattern"));
		
		contextElem.setEntityId(entId);
		
		//read values for ContextAttributeList
		List<ContextAttribute> contAttrList = new ArrayList<>();
		
		JSONArray attributes = contElemJson.getJSONObject("contextElement").getJSONArray("attributes");
		
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
}