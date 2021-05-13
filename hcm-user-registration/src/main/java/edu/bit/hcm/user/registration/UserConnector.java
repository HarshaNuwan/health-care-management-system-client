package edu.bit.hcm.user.registration;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.UserDTO;
import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;
import edu.bit.hcm.user.registration.request.DeleteUserRequest;
import edu.bit.hcm.user.registration.request.RetriveAllDoctorsRequest;
import edu.bit.hcm.user.registration.request.RetriveAllUsersRequest;
import edu.bit.hcm.user.registration.request.UserSignUpRequest;
import edu.bit.hcm.wrapper.DoctorDTOListWrapper;
import edu.bit.hcm.wrapper.UserDTOListWrapper;

public class UserConnector {

	@SuppressWarnings("unchecked")
	public void createNewUser(UserDTO dto) throws JsonGenerationException, JsonMappingException, IOException {
		UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
		userSignUpRequest.setDTO(dto);

		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector.getResponse(null, userSignUpRequest);

		// LoggedUser.getInstance().setJwtToken(RESTConnector.getFieldFromJSON("token",
		// resp.getBody())).setUserName(userName);

	}

	@SuppressWarnings("unchecked")
	public UserDTOListWrapper getAllUsers() throws JsonGenerationException, JsonMappingException, IOException {
		RetriveAllUsersRequest retriveAllUsersRequest = new RetriveAllUsersRequest();

		ResponseEntity<UserDTOListWrapper> resp = (ResponseEntity<UserDTOListWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), retriveAllUsersRequest);

		return resp.getBody();
	}
	
	public void deleteUser(UserDTO dto) throws JsonGenerationException, JsonMappingException, IOException {
		DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
		deleteUserRequest.setDTO(dto);
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector.getResponse(LoggedUser.getInstance().getJwtToken(), deleteUserRequest);
		
		
	}
	
	@SuppressWarnings("unchecked")
	public DoctorDTOListWrapper getAllDoctors()
			throws JsonGenerationException, JsonMappingException, IOException {
		RetriveAllDoctorsRequest retriveAllDoctorsRequest = new RetriveAllDoctorsRequest();
		ResponseEntity<DoctorDTOListWrapper> resp = (ResponseEntity<DoctorDTOListWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), retriveAllDoctorsRequest);

		return resp.getBody();
	}
}
