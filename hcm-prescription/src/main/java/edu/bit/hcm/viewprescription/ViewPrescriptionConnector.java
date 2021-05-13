package edu.bit.hcm.viewprescription;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;
import edu.bit.hcm.viewprescription.request.RetrivePrescriptionDataByDateRequest;
import edu.bit.hcm.wrapper.DiagnosisDTOListWrapper;

public class ViewPrescriptionConnector {
	
	@SuppressWarnings("unchecked")
	public DiagnosisDTOListWrapper getPrescriptionDetaislByDate(String fDate)
			throws JsonGenerationException, JsonMappingException, IOException {
		RetrivePrescriptionDataByDateRequest retrivePrescriptionDataByDateRequest = new RetrivePrescriptionDataByDateRequest();
		retrivePrescriptionDataByDateRequest.setURL("http://localhost:8889/commonapi/diagnosis/diagnosis/getallbydateforpharmacy?fDate=" + fDate);
		
		ResponseEntity<DiagnosisDTOListWrapper> resp = (ResponseEntity<DiagnosisDTOListWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), retrivePrescriptionDataByDateRequest);
		System.out.println(resp.getBody());
		return resp.getBody();

	}

}
