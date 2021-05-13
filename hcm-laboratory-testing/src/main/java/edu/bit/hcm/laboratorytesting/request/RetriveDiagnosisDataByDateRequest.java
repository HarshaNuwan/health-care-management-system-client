package edu.bit.hcm.laboratorytesting.request;

import org.springframework.http.HttpMethod;

import edu.bit.hcm.DiagnosisDTO;
import edu.bit.hcm.framework.service.ServerRequest;
import edu.bit.hcm.wrapper.DiagnosisDTOListWrapper;

public class RetriveDiagnosisDataByDateRequest implements ServerRequest<DiagnosisDTOListWrapper> {

	private DiagnosisDTOListWrapper diagnosisDTOListWrapper;
	private DiagnosisDTO diagnosisDTO;
	private String URL = "http://localhost:8889/commonapi/diagnosis/getbyDate";

	@Override
	public String convertToJSON(DiagnosisDTOListWrapper t) {
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
	public void setDTO(DiagnosisDTOListWrapper t) {
		this.diagnosisDTOListWrapper = t;
	}

	@Override
	public DiagnosisDTOListWrapper getDTO() {
		return this.diagnosisDTOListWrapper;
	}

	@Override
	public Class getResponseType() {
		return DiagnosisDTOListWrapper.class;
	}

	@Override
	public HttpMethod getRequestMethod() {
		return HttpMethod.GET;
	}

}
