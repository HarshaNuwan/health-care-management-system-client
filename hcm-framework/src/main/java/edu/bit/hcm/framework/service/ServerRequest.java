package edu.bit.hcm.framework.service;

import org.springframework.http.HttpMethod;

public interface ServerRequest<T> {
	
	public String convertToJSON(T t);

	public String getAPIURL();
	
	public void setDTO(T t);

	public T getDTO();

	public Class getResponseType();
	
	public HttpMethod getRequestMethod();
}
