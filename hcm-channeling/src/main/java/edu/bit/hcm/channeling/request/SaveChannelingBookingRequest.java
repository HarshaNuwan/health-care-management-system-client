package edu.bit.hcm.channeling.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.ChannelingDTO;
import edu.bit.hcm.framework.service.ServerRequest;

public class SaveChannelingBookingRequest implements ServerRequest<ChannelingDTO>{
	
	private ChannelingDTO dto;
	private String URL = "http://localhost:8889/commonapi/channeling/savechanneldetails";

	@Override
	public String convertToJSON(ChannelingDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAPIURL() {
		return URL;
	}

	@Override
	public void setDTO(ChannelingDTO t) {
		this.dto = t;
	}

	@Override
	public ChannelingDTO getDTO() {
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
