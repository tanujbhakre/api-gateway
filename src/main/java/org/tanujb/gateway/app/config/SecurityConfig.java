package org.tanujb.gateway.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.tanujb.gateway.security.service.TokenAuthenticationService;
import org.tanujb.gateway.security.service.impl.UserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.exceptionHandling().and()
		.anonymous().and()
		.servletApi().and()
		.headers().cacheControl().and()
		.authorizeRequests()
		//allow anonymous POSTs to login
		.antMatchers(HttpMethod.POST, "/api/login").permitAll()	
		.antMatchers(HttpMethod.GET, "/test").permitAll()
		//all other request need to be authenticated
		.anyRequest().hasRole("USER").and()				
		// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
		.addFilterBefore(new StatelessLoginFilter("/api/login", tokenAuthenticationService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		// custom Token based authentication based on the header previously given to the client
		.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class).csrf().disable();
		
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}
}