package edu.bit.hcm.patientregistration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.framework.service.ServerRequest;
import edu.bit.hcm.wrapper.PatientDTOListWrapper;

public class RetriveAllPatientsRequest implements ServerRequest<PatientDTOListWrapper> {

	PatientDTOListWrapper dto;
	private String URL = "http://localhost:8889/commonapi/patient/getallpatients";

	@Override
	public String convertToJSON(PatientDTOListWrapper t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public void setDTO(PatientDTOListWrapper t) {
		this.dto = t;
	}

	@Override
	public PatientDTOListWrapper getDTO() {
		return this.dto;
	}

	@Override
	public Class getResponseType() {
		return PatientDTOListWrapper.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.GET;
	}

}
