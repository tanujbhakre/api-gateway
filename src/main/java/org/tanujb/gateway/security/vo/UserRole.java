package org.tanujb.gateway.security.vo;

/**
 * Different possible roles a user can have
 * 
 * @author tbhakre
 *
 */
public enum UserRole {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

	private String roleName;

	UserRole(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}
}
