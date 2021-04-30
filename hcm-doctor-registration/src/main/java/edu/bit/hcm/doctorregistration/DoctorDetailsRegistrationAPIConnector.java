package edu.bit.hcm.doctorregistration;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.DoctorDTO;
import edu.bit.hcm.doctorregistration.request.DeleteDoctorRequest;
import edu.bit.hcm.doctorregistration.request.DoctorDetailsSaveRequest;
import edu.bit.hcm.doctorregistration.request.RetriveAllDoctorsRequest;
import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;
import edu.bit.hcm.wrapper.DoctorDTOListWrapper;

public class DoctorDetailsRegistrationAPIConnector {
	
	public String createNewDoctor(DoctorDTO dto) throws JsonGenerationException, JsonMappingException, IOException {
		DoctorDetailsSaveRequest detailsSaveRequest = new DoctorDetailsSaveRequest();
		detailsSaveRequest.setDTO(dto);
		
		@SuppressWarnings("unchecked")
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector.getResponse(LoggedUser.getInstance().getJwtToken(), detailsSaveRequest);
		
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	public DoctorDTOListWrapper getAllDoctors() throws JsonGenerationException, JsonMappingException, IOException {
		RetriveAllDoctorsRequest retriveAllDoctorsRequest = new RetriveAllDoctorsRequest(); 

		ResponseEntity<DoctorDTOListWrapper> resp = (ResponseEntity<DoctorDTOListWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), retriveAllDoctorsRequest);

		return resp.getBody();
	}
	
	public String deleteDoctor(DoctorDTO doctorDTO) throws JsonGenerationException, JsonMappingException, IOException {
		DeleteDoctorRequest deleteDoctorRequest = new DeleteDoctorRequest();
		deleteDoctorRequest.setDTO(doctorDTO);
		@SuppressWarnings("unchecked")
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), deleteDoctorRequest);

		return resp.getBody();
	}
	
	
	
	
}
