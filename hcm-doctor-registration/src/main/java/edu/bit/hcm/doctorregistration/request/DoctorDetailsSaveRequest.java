package edu.bit.hcm.doctorregistration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.DoctorDTO;
import edu.bit.hcm.framework.service.ServerRequest;

public class DoctorDetailsSaveRequest implements ServerRequest<DoctorDTO>{

	private final String URL = "http://localhost:8889/commonapi/doctor/savedetails";
	private DoctorDTO doctorDTO;
	
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
		this.doctorDTO = t;
	}

	@Override
	public DoctorDTO getDTO() {
		return this.doctorDTO;
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
