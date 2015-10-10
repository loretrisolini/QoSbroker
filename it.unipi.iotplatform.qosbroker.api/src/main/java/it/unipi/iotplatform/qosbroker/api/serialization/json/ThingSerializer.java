package it.unipi.iotplatform.qosbroker.api.serialization.json;

import it.unipi.iotplatform.qosbroker.api.datamodel.Thing;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElementResponse;
import eu.neclab.iotplatform.ngsi.api.datamodel.QueryContextRequest;

public class ThingSerializer extends JsonSerializer<Thing>{
	
	@Override
	public void serialize(Thing value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		jgen.writeStartObject();
		jgen.writeArrayFieldStart("thing");
		
		provider.defaultSerializeValue(value, jgen);
		
		jgen.writeEndArray();
		jgen.writeEndObject();

	}

	
}
