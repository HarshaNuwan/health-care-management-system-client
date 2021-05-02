package edu.bit.hcm.doctorregistration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.framework.service.ServerRequest;
import edu.bit.hcm.wrapper.SpecializationDTOListWrapper;

public class RetriveAllSpecializationListRequest implements ServerRequest<SpecializationDTOListWrapper> {

	private SpecializationDTOListWrapper specializationDTOListWrapper;
	private String URL = "http://localhost:8889/commonapi/doctor/getspecialization";

	@Override
	public String convertToJSON(SpecializationDTOListWrapper t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAPIURL() {
		// TODO Auto-generated method stub
		return this.URL;
	}

	@Override
	public void setDTO(SpecializationDTOListWrapper t) {
		this.specializationDTOListWrapper = t;
	}

	@Override
	public SpecializationDTOListWrapper getDTO() {
		return this.specializationDTOListWrapper;
	}

	@Override
	public Class getResponseType() {
		// TODO Auto-generated method stub
		return SpecializationDTOListWrapper.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		// TODO Auto-generated method stub
		return HttpMethod.GET;
	}

}
