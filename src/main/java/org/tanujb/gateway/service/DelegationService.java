package org.tanujb.gateway.service;

import javax.servlet.http.HttpServletRequest;

public interface DelegationService {

	byte[] processRequest(HttpServletRequest request);

}
