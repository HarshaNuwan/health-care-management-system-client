package edu.bit.hcm.laboratorytesting.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.framework.service.ServerRequest;

public class UpdateLabTestRecordRequest implements ServerRequest<String>{

	private String response;
	private String URL;
	
	@Override
	public String convertToJSON(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public void setDTO(String t) {
		this.response = t;
		
	}

	@Override
	public String getDTO() {
		return response;
	}

	@Override
	public Class getResponseType() {
		return String.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.GET;
	}

}
