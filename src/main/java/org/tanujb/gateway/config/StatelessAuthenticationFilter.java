package org.tanujb.gateway.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.tanujb.gateway.service.TokenAuthenticationService;

/**
 * This filter is used for authentication of incoming request by checking
 * presence of token in incoming request
 * 
 * @author tbhakre
 *
 */
class StatelessAuthenticationFilter extends GenericFilterBean {

	private final TokenAuthenticationService tokenAuthenticationService;

	protected StatelessAuthenticationFilter(TokenAuthenticationService taService) {
		this.tokenAuthenticationService = taService;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		// Getting the authentication
		Authentication authentication = tokenAuthenticationService
				.getAuthentication((HttpServletRequest) req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res); // always continue
		
	}
}