package edu.bit.hcm.patientregistration;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.PatientDTO;
import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;
import edu.bit.hcm.patientregistration.request.PatientDetailsSaveRequest;
import edu.bit.hcm.patientregistration.request.RetriveAllPatientsRequest;
import edu.bit.hcm.wrapper.PatientDTOListWrapper;

public class PatientDetailsRegistrationAPIConnector {

	public String createNewPatient(PatientDTO dto) throws JsonGenerationException, JsonMappingException, IOException {
		PatientDetailsSaveRequest detailsSaveRequest = new PatientDetailsSaveRequest();
		detailsSaveRequest.setDTO(dto);

		@SuppressWarnings("unchecked")
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), detailsSaveRequest);

		System.out.println(resp.getBody());
		return resp.getBody();
	}

	@SuppressWarnings("unchecked")
	public PatientDTOListWrapper getAllSpecialization()
			throws JsonGenerationException, JsonMappingException, IOException {
		RetriveAllPatientsRequest retriveAllPatientsRequest = new RetriveAllPatientsRequest();

		ResponseEntity<PatientDTOListWrapper> resp = (ResponseEntity<PatientDTOListWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), retriveAllPatientsRequest);

		return resp.getBody();
	}

}
