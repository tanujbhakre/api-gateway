package org.tanujb.gateway.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tanujb.gateway.security.vo.DelegationResponse;

public interface DelegationService {

	DelegationResponse processRequest(HttpServletRequest request,
			HttpServletResponse response);

}
