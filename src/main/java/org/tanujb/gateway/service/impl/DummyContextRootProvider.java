package org.tanujb.gateway.service.impl;

import org.tanujb.gateway.service.ContextRootProvider;

public class DummyContextRootProvider implements ContextRootProvider {

	@Override
	public String getContextRoot(String url) {
		if (url.contains("api/datastore")) {
			return "https://data.gov.in";
		} else if (url.contains("dia/") || url.contains("healthcheck")) {
			return "http://localhost:8666/dia-mockws-war";
		}
		return "https://data.gov.in";
	}

}
