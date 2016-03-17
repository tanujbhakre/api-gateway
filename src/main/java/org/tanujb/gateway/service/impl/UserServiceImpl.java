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
public class UserServiceImpl implements UserService {

	@Override
	public User findByUsername(String username) {
		User user = null;
		if ("tbhakre".equals(username)) {
			user = new User();
			user.setUsername("tbhakre");
			user.setPassword("123456");
			user.setExpires(Calendar.getInstance().getTimeInMillis() + 10000);
			/**/UserAuthority u1=new UserAuthority(UserRole.USER.getRoleName());
			UserAuthority u2=new UserAuthority(UserRole.USER.getRoleName());
			Set<UserAuthority> authorities=new HashSet<UserAuthority>();
			authorities.add(u1);
			authorities.add(u2);
			user.setAuthorities(authorities);
			/*Set<UserRole> roles =new HashSet<UserRole>();
			roles.add(UserRole.ADMIN);
			roles.add(UserRole.USER);
			user.setRoles(roles);*/
		}
		return user;
	}

}
