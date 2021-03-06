package org.tanujb.gateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tanujb.gateway.application.service.UserService;
import org.tanujb.gateway.vo.User;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
	
	@Autowired
	private UserService userService;
	
	@Override
	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		detailsChecker.check(user);
		return user;
	}
}
