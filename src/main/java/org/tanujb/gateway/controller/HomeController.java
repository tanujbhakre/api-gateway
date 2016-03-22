package org.tanujb.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resources present in the application
 * 
 * @author tbhakre
 *
 */

@RestController
public class HomeController {

	@RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
	public String test() {
		return "This resource does not require authentication";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getAuthorizedResource() {
		return "This resource requires authentication. User is authenticated to see this message.";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminResource() {
		return "This resource requires authentication. User is authenticated and has ADMIN role.";
	}
}
