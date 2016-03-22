package org.tanujb.gateway.security.service;

import org.tanujb.gateway.security.vo.DelegationRequest;
import org.tanujb.gateway.security.vo.DelegationResponse;

/**
 * Service for delegating the requests
 * 
 * @author tbhakre
 *
 */
public interface DelegationService {

	/**
	 * This method delegates the incoming request to correct micro service
	 * 
	 * @param request
	 *            Request details for delegation
	 * @return Response after delegation
	 */
	DelegationResponse processRequest(DelegationRequest request);

}
