package org.tanujb.gateway.service;

/**
 * This method provides context root on the basis of incoming URL
 * 
 * @author tbhakre
 *
 */
public interface ContextRootProvider {

	String getContextRoot(String url);

}
