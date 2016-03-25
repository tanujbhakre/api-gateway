package org.tanujb.gateway.vo;

import org.springframework.security.core.GrantedAuthority;

/**
 * Authorities granted to user
 * 
 * @author tbhakre
 *
 */
public class UserAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private String authority;

	public UserAuthority() {
	}

	public UserAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserAuthority))
			return false;

		UserAuthority ua = (UserAuthority) obj;
		return ua.getAuthority() == this.getAuthority()
				|| ua.getAuthority().equals(this.getAuthority());
	}

	@Override
	public int hashCode() {
		return getAuthority() == null ? 0 : getAuthority().hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getAuthority();
	}
}
