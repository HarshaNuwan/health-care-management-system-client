package edu.bit.hcm.doctorregistration;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.doctorregistration.request.RetriveAllSpecializationListRequest;
import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;
import edu.bit.hcm.wrapper.SpecializationDTOListWrapper;

public class SpecializationConnector {

	@SuppressWarnings("unchecked")
	public SpecializationDTOListWrapper getAllSpecialization() throws JsonGenerationException, JsonMappingException, IOException {
		RetriveAllSpecializationListRequest retriveAllSpecializationListRequest = new RetriveAllSpecializationListRequest();

		ResponseEntity<SpecializationDTOListWrapper> resp = (ResponseEntity<SpecializationDTOListWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), retriveAllSpecializationListRequest);

		return resp.getBody();
	}
}
