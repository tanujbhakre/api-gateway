package org.tanujb.gateway.security.service.impl;

import org.springframework.stereotype.Service;
import org.tanujb.gateway.security.service.ContextRootProvider;

@Service
public class DummyContextRootProvider implements ContextRootProvider {

	@Override
	public String getContextRoot(String url) {
		if (url.contains("api/datastore")) {
			return "https://data.gov.in";
		}
		return "https://data.gov.in";
	}

}
