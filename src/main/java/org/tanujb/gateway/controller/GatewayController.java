package org.tanujb.gateway.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tanujb.gateway.security.exception.ApplicationRuntimeException;
import org.tanujb.gateway.security.service.DelegationService;
import org.tanujb.gateway.security.vo.DelegationRequest;
import org.tanujb.gateway.security.vo.DelegationResponse;
import org.tanujb.gateway.util.GatewayUtil;

@RestController
public class GatewayController {

	@Autowired
	private DelegationService service;

	@RequestMapping(value = "/**", method = { RequestMethod.GET,
			RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
			RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH,
			RequestMethod.TRACE })
	public void processRequest(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		DelegationRequest request = new DelegationRequest(
				httpRequest.getMethod(), GatewayUtil.getURL(httpRequest),
				GatewayUtil.getPlaceHolderDetails(httpRequest),
				GatewayUtil.getHeadersInfo(httpRequest),
				GatewayUtil.getRequestBody(httpRequest));

		DelegationResponse response = service.processRequest(request);
		// Setting response status
		httpResponse.setStatus(response.getStatus());
		// Setting response header
		for (Map.Entry<String, String> entry : response.getResponseHeader()
				.entrySet()) {
			httpResponse.setHeader(entry.getKey(), entry.getValue());
		}
		// Setting response body
		byte[] responseBytes = response.getBody();
		if (responseBytes != null && responseBytes.length > 0) {
			try {
				OutputStream out = httpResponse.getOutputStream();
				out.write(responseBytes);
				out.close();
			} catch (IOException e) {
				throw new ApplicationRuntimeException(
						"Exception occured while writing response", e);
			}
		}
	}
}
