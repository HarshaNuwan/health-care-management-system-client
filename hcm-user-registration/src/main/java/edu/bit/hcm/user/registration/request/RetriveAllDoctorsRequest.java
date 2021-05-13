package edu.bit.hcm.user.registration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.framework.service.ServerRequest;
import edu.bit.hcm.wrapper.DoctorDTOListWrapper;

public class RetriveAllDoctorsRequest implements ServerRequest<DoctorDTOListWrapper>{
	
	private DoctorDTOListWrapper listWrapper;
	private String URL = "http://localhost:8889/commonapi/doctor/getalldoctors";

	@Override
	public String convertToJSON(DoctorDTOListWrapper t) {
		return null;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public void setDTO(DoctorDTOListWrapper t) {
		this.listWrapper = t;
	}

	@Override
	public DoctorDTOListWrapper getDTO() {
		return this.listWrapper;
	}

	@Override
	public Class getResponseType() {
		// TODO Auto-generated method stub
		return DoctorDTOListWrapper.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.GET;
	}

}
