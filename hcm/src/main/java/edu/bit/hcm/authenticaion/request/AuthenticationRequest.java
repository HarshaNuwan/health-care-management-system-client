package edu.bit.hcm.authenticaion.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.authenticaion.AuthenticationConnector.AuthenticateUser;
import edu.bit.hcm.framework.service.ServerRequest;

public class AuthenticationRequest implements ServerRequest<AuthenticateUser> {

	private final String URL = "http://localhost:8889/user/authenticate";
	private AuthenticateUser dto;

	@Override
	public String convertToJSON(AuthenticateUser t) {
		return null;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public AuthenticateUser getDTO() {
		return dto;
	}

	@Override
	public Class getResponseType() {
		return String.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.POST;
	}

	@Override
	public void setDTO(AuthenticateUser t) {
		this.dto = t;

	}

}
