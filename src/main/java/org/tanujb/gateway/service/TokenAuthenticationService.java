package org.tanujb.gateway.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.tanujb.gateway.vo.UserAuthentication;

public interface TokenAuthenticationService {

	/**
	 * This method creates token and adds it to response
	 * 
	 * @param response
	 *            Response in which token is to be added
	 * @param authentication
	 *            Details to be encrypted inside token
	 */
	void addAuthentication(HttpServletResponse response,
			UserAuthentication authentication);

	/**
	 * This method reads the token details from incoming request and returns
	 * authentication and decrypts the token
	 * 
	 * @param request
	 *            Object containing the token details
	 * @return Authentication object created after decrypting token
	 */
	Authentication getAuthentication(HttpServletRequest request);

}
