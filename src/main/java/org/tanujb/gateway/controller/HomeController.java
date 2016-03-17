package org.tanujb.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping(value = "/test", method =
			RequestMethod.GET)
	@ResponseBody
	public String test(){
		return "This resource does not require authentication";
	}
	
	@RequestMapping(value = "/authorized", method =
			RequestMethod.GET)
	@ResponseBody
	public String getAuthorizedResource(){
		return "This resource requires authentication. User is authenticated to see this message.";
	}
}
