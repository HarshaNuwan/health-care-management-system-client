package edu.bit.hcm.doctorregistration.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.ChannelingDTO;
import edu.bit.hcm.framework.service.ServerRequest;
import edu.bit.hcm.wrapper.ChannelingDTOWrapper;

public class RetriveChannelingDataByDateAndDoctorIDRequest implements ServerRequest<ChannelingDTOWrapper> {

	private ChannelingDTOWrapper channelingListWrapper;
	private ChannelingDTO channelingDTO;
	private String URL = "http://localhost:8889/commonapi/channeling/findbydoctoranddate";

	@Override
	public String convertToJSON(ChannelingDTOWrapper t) {
		return null;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	public String getAPIURL() {
		return this.URL;
	}

	@Override
	public void setDTO(ChannelingDTOWrapper t) {
		this.channelingListWrapper = t;
	}

	@Override
	public ChannelingDTOWrapper getDTO() {
		return this.channelingListWrapper;
	}

	@Override
	public Class getResponseType() {
		return ChannelingDTOWrapper.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.GET;
	}

}
