package org.tanujb.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping(value = "/unauthorized", method =
			RequestMethod.GET)
	@ResponseBody
	public String test(){
		return "This resource does not require authentication";
	}
	
	@RequestMapping(value = "/user", method =
			RequestMethod.GET)
	@ResponseBody
	public String getAuthorizedResource(){
		return "This resource requires authentication. User is authenticated to see this message.";
	}
	
	@RequestMapping(value = "/admin", method =
			RequestMethod.GET)
	@ResponseBody
	public String getAdminResource(){
		return "This resource requires authentication. User is authenticated and has ADMIN role.";
	}
}
