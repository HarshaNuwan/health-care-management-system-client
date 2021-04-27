package edu.bit.hcm.authenticaion;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.authenticaion.request.AuthenticationRequest;
import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;

public class AuthenticationConnector {

	
	public AuthenticationConnector() {
	}
	
	@SuppressWarnings("unchecked")
	public boolean doLogin(String userName, String password) throws JsonGenerationException, JsonMappingException, IOException {

		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setDTO(new AuthenticateUser(userName, password));
		
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector.getResponse(LoggedUser.getInstance().getJwtToken(), authenticationRequest);
		
		LoggedUser.getInstance().setJwtToken(RESTConnector.getFieldFromJSON("token", resp.getBody())).setUserName(userName);
		
		if(resp == null || RESTConnector.getFieldFromJSON("token", resp.getBody()) == null) {
			return false;
		}
		return true;

	}

	public class AuthenticateUser {
		private String username;
		private String password;

		public AuthenticateUser(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String userName) {
			this.username = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}
}
