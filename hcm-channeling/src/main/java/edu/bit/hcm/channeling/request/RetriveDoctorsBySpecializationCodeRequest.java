package edu.bit.hcm.channeling.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.framework.service.ServerRequest;
import edu.bit.hcm.wrapper.DoctorDTOListWrapper;

public class RetriveDoctorsBySpecializationCodeRequest implements ServerRequest<DoctorDTOListWrapper>{
	
	private DoctorDTOListWrapper doctorDTOListWrapper;
	private String URL = "";

	@Override
	public String convertToJSON(DoctorDTOListWrapper t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	public String getAPIURL() {
		// TODO Auto-generated method stub
		return this.URL;
	}

	@Override
	public void setDTO(DoctorDTOListWrapper t) {
		this.doctorDTOListWrapper =t;
		
	}

	@Override
	public DoctorDTOListWrapper getDTO() {
		// TODO Auto-generated method stub
		return this.doctorDTOListWrapper;
	}

	@Override
	public Class getResponseType() {
		// TODO Auto-generated method stub
		return DoctorDTOListWrapper.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		// TODO Auto-generated method stub
		return HttpMethod.GET;
	}

}
