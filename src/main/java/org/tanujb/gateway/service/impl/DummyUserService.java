package org.tanujb.gateway.service.impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.tanujb.gateway.security.vo.User;
import org.tanujb.gateway.security.vo.UserAuthority;
import org.tanujb.gateway.security.vo.UserRole;
import org.tanujb.gateway.service.UserService;

@Service
public class DummyUserService implements UserService {

	@Override
	public User findByUsername(String username) {
		User user = null;
		if ("user".equals(username)) {
			user = new User();
			user.setUsername("user");
			user.setPassword("123456");
			user.setExpires(Calendar.getInstance().getTimeInMillis() + 10000);
			UserAuthority u1 = new UserAuthority(UserRole.USER.getRoleName());
			Set<UserAuthority> authorities = new HashSet<UserAuthority>();
			authorities.add(u1);
			user.setAuthorities(authorities);
		} else if ("admin".equals(username)) {
			user = new User();
			user.setUsername("admin");
			user.setPassword("123456");
			user.setExpires(Calendar.getInstance().getTimeInMillis() + 10000);
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
