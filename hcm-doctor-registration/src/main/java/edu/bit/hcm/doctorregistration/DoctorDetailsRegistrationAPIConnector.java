package edu.bit.hcm.doctorregistration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.DiagnosisDTO;
import edu.bit.hcm.DoctorDTO;
import edu.bit.hcm.doctorregistration.request.DeleteDoctorRequest;
import edu.bit.hcm.doctorregistration.request.DoctorDetailsSaveRequest;
import edu.bit.hcm.doctorregistration.request.RetriveAllDoctorsRequest;
import edu.bit.hcm.doctorregistration.request.RetriveChannelingDataByDateAndDoctorIDRequest;
import edu.bit.hcm.doctorregistration.request.RetriveDiagnosisDataByPidAndDoctorIDRequest;
import edu.bit.hcm.doctorregistration.request.SaveDiagnosisDataRequest;
import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;
import edu.bit.hcm.wrapper.ChannelingDTOWrapper;
import edu.bit.hcm.wrapper.DiagnosisDTOListWrapper;
import edu.bit.hcm.wrapper.DoctorDTOListWrapper;

public class DoctorDetailsRegistrationAPIConnector {

	public String createNewDoctor(DoctorDTO dto) throws JsonGenerationException, JsonMappingException, IOException {
		DoctorDetailsSaveRequest detailsSaveRequest = new DoctorDetailsSaveRequest();
		detailsSaveRequest.setDTO(dto);

		@SuppressWarnings("unchecked")
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), detailsSaveRequest);

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

	@SuppressWarnings("unchecked")
	public ChannelingDTOWrapper getChannelingDetailsByDateAndDoctorId(Date date, int doctorId)
			throws JsonGenerationException, JsonMappingException, IOException {
		RetriveChannelingDataByDateAndDoctorIDRequest byDateAndDoctorIDRequest = new RetriveChannelingDataByDateAndDoctorIDRequest();
		byDateAndDoctorIDRequest.setURL("http://localhost:8889/commonapi/channeling/findbydoctoranddate?date="
				+ new SimpleDateFormat("yyyy-MM-dd").format(date) + "&doctorId=" + doctorId);

		ResponseEntity<ChannelingDTOWrapper> resp = (ResponseEntity<ChannelingDTOWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), byDateAndDoctorIDRequest);

		return resp.getBody();
	}

	@SuppressWarnings("unchecked")
	public DiagnosisDTOListWrapper getDiagnosisDetaislByPidAndDoctorId(int pid, int doctorId)
			throws JsonGenerationException, JsonMappingException, IOException {
		RetriveDiagnosisDataByPidAndDoctorIDRequest retriveDiagnosisDataByPidAndDoctorIDRequest = new RetriveDiagnosisDataByPidAndDoctorIDRequest();
		retriveDiagnosisDataByPidAndDoctorIDRequest
				.setURL("http://localhost:8889/commonapi/diagnosis/getallbypidanddoctorid?pid=" + pid + "&doctorId="
						+ doctorId);
		ResponseEntity<DiagnosisDTOListWrapper> resp = (ResponseEntity<DiagnosisDTOListWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), retriveDiagnosisDataByPidAndDoctorIDRequest);
		System.out.println(resp.getBody());
		return resp.getBody();

	}
	
	public String createNewDiagnosis(DiagnosisDTO dto) throws JsonGenerationException, JsonMappingException, IOException {
		SaveDiagnosisDataRequest saveDiagnosisDataRequest = new SaveDiagnosisDataRequest();
		saveDiagnosisDataRequest.setDTO(dto);

		@SuppressWarnings("unchecked")
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), saveDiagnosisDataRequest);

		return resp.getBody();
	}

}
