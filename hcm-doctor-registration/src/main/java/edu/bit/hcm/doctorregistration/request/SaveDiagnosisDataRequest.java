package edu.bit.hcm.doctorregistration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.DiagnosisDTO;
import edu.bit.hcm.framework.service.ServerRequest;

public class SaveDiagnosisDataRequest implements ServerRequest<DiagnosisDTO>{

	private DiagnosisDTO dto;
	private String URL = "http://localhost:8889/commonapi/diagnosis/savediagnosis";
	
	@Override
	public String convertToJSON(DiagnosisDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public void setDTO(DiagnosisDTO t) {
		this.dto = t;		
	}

	@Override
	public DiagnosisDTO getDTO() {
		return this.dto;
	}

	@Override
	public Class getResponseType() {
		return String.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.POST;
	}

}
