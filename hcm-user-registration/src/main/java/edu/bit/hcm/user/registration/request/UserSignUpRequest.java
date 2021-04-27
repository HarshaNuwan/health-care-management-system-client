package edu.bit.hcm.user.registration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.UserDTO;
import edu.bit.hcm.framework.service.ServerRequest;

public class UserSignUpRequest implements ServerRequest<UserDTO> {

	private final String URL = "http://localhost:8889/user/signup";
	private UserDTO userDTO;

	@Override
	public String convertToJSON(UserDTO t) {
		return null;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public void setDTO(UserDTO t) {
		this.userDTO = t;
	}

	@Override
	public UserDTO getDTO() {
		return this.userDTO;
	}

	@Override
	public Class getResponseType() {
		return String.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.POST;
	}

}
