package org.tanujb.gateway.service;

import org.tanujb.gateway.security.vo.User;

public interface UserService {
	User findByUsername(String username);
}
