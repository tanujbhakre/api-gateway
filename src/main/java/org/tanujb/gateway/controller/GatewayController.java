package org.tanujb.gateway.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class GatewayController {

	@RequestMapping(value = "/**", method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.PUT, RequestMethod.DELETE })
	@ResponseBody
	public void processRequest(HttpServletRequest httpRequest) {
		
		
	}
}
