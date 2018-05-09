package org.eventapp.utilities.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.eventapp.dtos.LocationDto;

import java.io.IOException;

public class LocationDeserializer extends StdDeserializer<LocationDto> {
  
  public LocationDeserializer(Class<?> vc) {
    super(vc);
  }
  
  @Override
  public LocationDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
    throws IOException, JsonProcessingException {
    
    LocationDto locationDto = new LocationDto();
    String geometry = "geometry";
    while (!jsonParser.isClosed()) {
      JsonToken jsonToken = jsonParser.nextToken();
  
      if(JsonToken.FIELD_NAME.equals(jsonToken)){
        String fieldName = jsonParser.getCurrentName();
        System.out.println(fieldName);
    
        jsonToken = jsonParser.nextToken();
        
        
    
        if(geometry.equals(fieldName)){
        
//          locationDto.setLatitude();
        }
      }
    }
    
    return null;
  }
}
