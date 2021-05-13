package edu.bit.hcm.laboratorytesting;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.ResponseEntity;

import edu.bit.hcm.framework.util.LoggedUser;
import edu.bit.hcm.framework.util.RESTConnector;
import edu.bit.hcm.laboratorytesting.request.RetriveDiagnosisDataByDateRequest;
import edu.bit.hcm.laboratorytesting.request.UpdateLabTestRecordRequest;
import edu.bit.hcm.wrapper.DiagnosisDTOListWrapper;

public class LaboratoryTestingAPIConnector {

	@SuppressWarnings("unchecked")
	public DiagnosisDTOListWrapper getDiagnosisDetaislByDate(String date)
			throws JsonGenerationException, JsonMappingException, IOException {
		RetriveDiagnosisDataByDateRequest byDateRequest = new RetriveDiagnosisDataByDateRequest();
		byDateRequest.setURL("http://localhost:8889/commonapi/diagnosis/getbyDate?date=" + date);

		ResponseEntity<DiagnosisDTOListWrapper> resp = (ResponseEntity<DiagnosisDTOListWrapper>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), byDateRequest);
		return resp.getBody();

	}

	@SuppressWarnings("unchecked")
	public String updateLabTestRecord(int diagnosisId) throws JsonGenerationException, JsonMappingException, IOException {
		UpdateLabTestRecordRequest labTestRecordRequest = new UpdateLabTestRecordRequest();
		labTestRecordRequest
				.setURL("http://localhost:8889/commonapi/diagnosis/completelabreport?diagnosisId=" + diagnosisId);
		
		ResponseEntity<String> resp = (ResponseEntity<String>) RESTConnector
				.getResponse(LoggedUser.getInstance().getJwtToken(), labTestRecordRequest);
		return resp.getBody();
	}
}
