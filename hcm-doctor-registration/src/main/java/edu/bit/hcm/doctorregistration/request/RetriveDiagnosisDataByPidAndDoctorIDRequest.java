package edu.bit.hcm.doctorregistration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.DiagnosisDTO;
import edu.bit.hcm.framework.service.ServerRequest;
import edu.bit.hcm.wrapper.DiagnosisDTOListWrapper;

public class RetriveDiagnosisDataByPidAndDoctorIDRequest implements ServerRequest<DiagnosisDTO> {

	private DiagnosisDTO dto;
	private String URL = "";

	@Override
	public String convertToJSON(DiagnosisDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	public void setDTO(DiagnosisDTO t) {
		this.dto = t;
	}

	@Override
	public DiagnosisDTO getDTO() {
		return dto;
	}

	@Override
	public Class getResponseType() {
		return DiagnosisDTOListWrapper.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.GET;
	}

}
