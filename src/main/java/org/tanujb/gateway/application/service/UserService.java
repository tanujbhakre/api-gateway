package org.tanujb.gateway.application.service;

import org.tanujb.gateway.vo.User;

/**
 * User service which provides user information
 * 
 * @author tbhakre
 *
 */
public interface UserService {

	/**
	 * This method returns the user details for the provided user name
	 * 
	 * @param username
	 *            User name for which user details are required
	 * @return User object for the passed user name
	 */
	User findByUsername(String username);
}
