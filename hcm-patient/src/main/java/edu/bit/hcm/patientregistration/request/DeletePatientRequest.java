package edu.bit.hcm.patientregistration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.PatientDTO;
import edu.bit.hcm.framework.service.ServerRequest;

public class DeletePatientRequest implements ServerRequest<PatientDTO> {

	private final String URL = "http://localhost:8889/commonapi/patient/delete";
	private PatientDTO patinetDTO;

	@Override
	public String convertToJSON(PatientDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public void setDTO(PatientDTO t) {
		this.patinetDTO = t;
	}

	@Override
	public PatientDTO getDTO() {
		return this.patinetDTO;
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
