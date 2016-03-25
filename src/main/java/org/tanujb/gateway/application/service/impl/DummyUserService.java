package org.tanujb.gateway.application.service.impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.tanujb.gateway.application.service.UserService;
import org.tanujb.gateway.vo.User;
import org.tanujb.gateway.vo.UserAuthority;
import org.tanujb.gateway.vo.UserRole;

@Service
public class DummyUserService implements UserService {

	private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;
	
	
	@Override
	public User findByUsername(String username) {
		User user = null;
		if ("user".equals(username)) {
			user = new User();
			user.setUsername("user");
			user.setPassword("123456");
			user.setExpires(Calendar.getInstance().getTimeInMillis() + TEN_DAYS);
			UserAuthority u1 = new UserAuthority(UserRole.USER.getRoleName());
			Set<UserAuthority> authorities = new HashSet<UserAuthority>();
			authorities.add(u1);
			user.setAuthorities(authorities);
		} else if ("admin".equals(username)) {
			user = new User();
			user.setUsername("admin");
			user.setPassword("123456");
			user.setExpires(Calendar.getInstance().getTimeInMillis() + TEN_DAYS);
			UserAuthority u1 = new UserAuthority(UserRole.USER.getRoleName());
			UserAuthority u2 = new UserAuthority(UserRole.ADMIN.getRoleName());
			Set<UserAuthority> authorities = new HashSet<UserAuthority>();
			authorities.add(u1);
			authorities.add(u2);
			user.setAuthorities(authorities);
		}
		return user;
	}

}
