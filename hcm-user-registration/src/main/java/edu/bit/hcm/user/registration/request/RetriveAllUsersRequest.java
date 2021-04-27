package edu.bit.hcm.user.registration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.framework.service.ServerRequest;
import edu.bit.hcm.wrapper.UserDTOListWrapper;

public class RetriveAllUsersRequest implements ServerRequest<UserDTOListWrapper> {
	private final String URL = "http://localhost:8889/user/getUsers";
	private UserDTOListWrapper userList;

	@Override
	public String convertToJSON(UserDTOListWrapper t) {
		return null;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public void setDTO(UserDTOListWrapper t) {
		this.userList = t;

	}

	@Override
	public UserDTOListWrapper getDTO() {
		return this.userList;
	}

	@Override
	public Class getResponseType() {
		return UserDTOListWrapper.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.GET;
	}

}
