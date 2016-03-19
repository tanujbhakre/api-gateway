package org.tanujb.gateway.security.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.tanujb.gateway.security.service.CryptoService;
import org.tanujb.gateway.security.service.TokenAuthenticationService;
import org.tanujb.gateway.security.vo.User;
import org.tanujb.gateway.security.vo.UserAuthentication;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService{

	@Autowired
	private CryptoService tokenHandler;
	
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

	@Override
	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		final User user = authentication.getDetails();
		response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
	}

	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
