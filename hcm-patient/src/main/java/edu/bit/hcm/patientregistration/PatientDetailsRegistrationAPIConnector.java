package edu.bit.hcm.patientregistration;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.PatientDTO;
import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;
import edu.bit.hcm.patientregistration.request.PatientDetailsSaveRequest;

public class PatientDetailsRegistrationAPIConnector {
	
	public String createNewPatient(PatientDTO dto) throws JsonGenerationException, JsonMappingException, IOException {
		PatientDetailsSaveRequest detailsSaveRequest = new PatientDetailsSaveRequest();
		detailsSaveRequest.setDTO(dto);
		
		@SuppressWarnings("unchecked")
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector.getResponse(LoggedUser.getInstance().getJwtToken(), detailsSaveRequest);
		
		System.out.println(resp.getBody());
		return resp.getBody();
	}

}
