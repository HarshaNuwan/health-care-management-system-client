package edu.bit.hcm.framework.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;

import edu.bit.hcm.framework.service.ServerRequest;

public class RESTConnector {
	private static final Logger logger = LoggerFactory.getLogger(RESTConnector.class);

	private static RestTemplate restTemplate;
	private static ObjectMapper objectMapper;

	static {
		restTemplate = new RestTemplate();
		objectMapper = new ObjectMapper();
	}

	@SuppressWarnings("unchecked")
	public static ResponseEntity<?> getResponse(final String jwtToken, final ServerRequest<?> serverRequest)
			throws JsonGenerationException, JsonMappingException, IOException {
		HttpHeaders headers = new HttpHeaders();
		
		if(null != jwtToken) {
			headers.add("Authorization", "Bearer " + jwtToken);
		}
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		String jsonString = null;
		HttpEntity<String> entity = null;
		if(null != serverRequest.getDTO()) {
			jsonString = objectMapper.writeValueAsString(serverRequest.getDTO());
			entity = new HttpEntity<>(jsonString, headers);
			logger.info(jsonString);
		}else {
			entity = new HttpEntity<>(null, headers);
		}
		
		restTemplate.setErrorHandler(new ResponseErrorHandler() {
			
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				logger.info(response.getStatusText());
				return false;
			}
			
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				logger.info(response.getStatusText() + " Handle error");
				
			}
		});
		
		/*ObjectMapper lax = new ObjectMapper().configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		MappingJacksonHttpMessageConverter c = new MappingJacksonHttpMessageConverter();
		c.setObjectMapper(lax);

		List<HttpMessageConverter<?>> list = new ArrayList<>();
		list.add(c);*/

		return restTemplate.exchange(serverRequest.getAPIURL(), serverRequest.getRequestMethod(), entity,
				serverRequest.getResponseType());
	}
	
	public static String getFieldFromJSON(String fieldName, String JSONString) throws JsonParseException, JsonMappingException, IOException {
		final ObjectNode node = new ObjectMapper().readValue(JSONString, ObjectNode.class);
		
		if(node.has(fieldName)) {
			return node.get(fieldName).asText();
		}
		return null;
	}
	
	
}
