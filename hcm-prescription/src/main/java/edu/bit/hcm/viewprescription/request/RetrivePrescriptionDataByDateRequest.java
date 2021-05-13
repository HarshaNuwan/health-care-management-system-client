package edu.bit.hcm.viewprescription.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.DiagnosisDTO;
import edu.bit.hcm.framework.service.ServerRequest;
import edu.bit.hcm.wrapper.DiagnosisDTOListWrapper;

public class RetrivePrescriptionDataByDateRequest implements ServerRequest<DiagnosisDTO> {

	private DiagnosisDTO dto;
	private String URL = "";

	@Override
	public String convertToJSON(DiagnosisDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAPIURL() {
		// TODO Auto-generated method stub
		return this.URL;
	}
	
	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	public DiagnosisDTO getDTO() {
		// TODO Auto-generated method stub
		return dto;
	}

	@Override
	public void setDTO(DiagnosisDTO t) {
		this.dto = t;

	}

	@Override
	public Class getResponseType() {
		// TODO Auto-generated method stub
		return DiagnosisDTOListWrapper.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		// TODO Auto-generated method stub
		return HttpMethod.GET;
	}

}
