package org.tanujb.gateway.security.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.tanujb.gateway.security.service.CryptoService;
import org.tanujb.gateway.security.service.TokenAuthenticationService;
import org.tanujb.gateway.security.vo.User;
import org.tanujb.gateway.security.vo.UserAuthentication;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService{

	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;
	private static final String KEY = "xGfr#4@@6888KwetyComplct8Nobdfdujkogtr6";
	private final CryptoService tokenHandler;

	
	public TokenAuthenticationServiceImpl() {
		tokenHandler = new JWTCryptoService(KEY.getBytes());
	}

	@Override
	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		final User user = authentication.getDetails();
		user.setExpires(System.currentTimeMillis() + TEN_DAYS);
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
