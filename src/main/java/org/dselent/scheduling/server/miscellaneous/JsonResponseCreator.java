package org.dselent.scheduling.server.miscellaneous;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResponseCreator
{
		public static String getJSONResponse(List<Object> success, List<Object> error) throws JsonProcessingException
		{
			return getJSONResponse(success, error, new ObjectMapper());
		}
		  
		public static String getJSONResponse(List<Object> success, List<Object> error,  ObjectMapper mapper) throws JsonProcessingException
		{
			Map<String, Object> map = new HashMap<String, Object>();
		    map.put("success", success);
		    map.put("errors", error);
		    
		    return mapper.writeValueAsString(map);
		}
}
