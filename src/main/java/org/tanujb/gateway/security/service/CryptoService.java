package org.tanujb.gateway.security.service;

import org.tanujb.gateway.security.vo.User;

/**
 * Service for encryption and decryption of tokens 
 * @author tbhakre
 *
 */
public interface CryptoService {

	/**
	 * Creates token for the provided user
	 * @param user User to be encrypted in token
	 * @return
	 */
	String createTokenForUser(User user);

	/**
	 * Parses token and retrieves User object inside it
	 * @param token
	 * @return
	 */
	User parseUserFromToken(String token);

}
