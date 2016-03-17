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
	public String tet(){
		return "running";
	}
}
