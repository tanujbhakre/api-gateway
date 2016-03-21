package org.tanujb.gateway.security.vo;

import java.util.Map;

public class DelegationResponse {

	private byte[] body;
	private int status;
	private Map<String, String> responseHeader;

	public DelegationResponse(int status, Map<String, String> responseHeader,
			byte[] body) {
		this.status = status;
		this.responseHeader = responseHeader;
		this.body = body;

	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int responseStatus) {
		this.status = responseStatus;
	}

	public Map<String, String> getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(Map<String, String> responseHeader) {
		this.responseHeader = responseHeader;
	}

}
