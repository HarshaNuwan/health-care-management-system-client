package edu.bit.hcm.channeling;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.ChannelingDTO;
import edu.bit.hcm.channeling.request.RetriveChannelingDataByDateAndDoctorIDRequest;
import edu.bit.hcm.channeling.request.RetriveDoctorsBySpecializationCodeRequest;
import edu.bit.hcm.channeling.request.SaveChannelingBookingRequest;
import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;
import edu.bit.hcm.wrapper.ChannelingDTOWrapper;
import edu.bit.hcm.wrapper.DoctorDTOListWrapper;

public class ChannelingConnector {

	@SuppressWarnings("unchecked")
	public ChannelingDTOWrapper getChannelingDetailsByDateAndDoctorId(ChannelingDTO channelingDTO)
			throws JsonGenerationException, JsonMappingException, IOException {
		RetriveChannelingDataByDateAndDoctorIDRequest byDateAndDoctorIDRequest = new RetriveChannelingDataByDateAndDoctorIDRequest();
		byDateAndDoctorIDRequest.setURL("http://localhost:8889/commonapi/channeling/findbydoctoranddate?date="
				+ new SimpleDateFormat("yyyy-MM-dd").format(channelingDTO.getDate()) + "&doctorId="
				+ channelingDTO.getDoctorId());

		ResponseEntity<ChannelingDTOWrapper> resp = (ResponseEntity<ChannelingDTOWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), byDateAndDoctorIDRequest);

		return resp.getBody();
	}

	@SuppressWarnings("unchecked")
	public DoctorDTOListWrapper getDoctorsBySpecializationCode(int spc)
			throws JsonGenerationException, JsonMappingException, IOException {
		RetriveDoctorsBySpecializationCodeRequest bySpecializationCodeRequest = new RetriveDoctorsBySpecializationCodeRequest();
		bySpecializationCodeRequest
				.setURL("http://localhost:8889/commonapi/doctor/findbyspecializationcode?spc=" + spc);
		System.out.println(">>>" + spc);
		ResponseEntity<DoctorDTOListWrapper> resp = (ResponseEntity<DoctorDTOListWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), bySpecializationCodeRequest);

		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	public String saveChannleBooking(ChannelingDTO channelingDTO)
			throws JsonGenerationException, JsonMappingException, IOException {
		SaveChannelingBookingRequest channelingBookingRequest = new SaveChannelingBookingRequest();
		channelingBookingRequest.setDTO(channelingDTO);

		
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), channelingBookingRequest);

		return resp.getBody();
	}
}
