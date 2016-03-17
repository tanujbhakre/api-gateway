package org.tanujb.gateway.security.vo;

public enum UserRole {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
	
	private String roleName;
	
	UserRole(String roleName){
		this.roleName=roleName;
	}

	public String getRoleName() {
		return roleName;
	}
}
