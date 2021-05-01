package edu.bit.hcm.patientregistration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.PatientDTO;
import edu.bit.hcm.framework.service.ServerRequest;

public class PatientDetailsSaveRequest implements ServerRequest<PatientDTO> {

	private final String URL = "http://localhost:8889/commonapi/patient/save";
	private PatientDTO patinetDTO;

	@Override
	public String convertToJSON(PatientDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAPIURL() {
		// TODO Auto-generated method stub
		return this.URL;
	}

	@Override
	public void setDTO(PatientDTO t) {
		this.patinetDTO = t;

	}

	@Override
	public PatientDTO getDTO() {
		// TODO Auto-generated method stub
		return this.patinetDTO;
	}

	@Override
	public Class getResponseType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		// TODO Auto-generated method stub
		return HttpMethod.POST;
	}

}
