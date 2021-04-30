package edu.bit.hcm.doctorregistration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.DoctorDTO;
import edu.bit.hcm.framework.service.ServerRequest;

public class DeleteDoctorRequest implements ServerRequest<DoctorDTO>{
	
	private DoctorDTO dto;
	private String URL = "http://localhost:8889/commonapi/doctor/delete";

	@Override
	public String convertToJSON(DoctorDTO t) {
		return null;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public void setDTO(DoctorDTO t) {
		this.dto = t;
	}

	@Override
	public DoctorDTO getDTO() {
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
