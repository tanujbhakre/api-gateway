package org.tanujb.gateway.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tanujb.gateway.config.spring.ContextPathConfig;
import org.tanujb.gateway.service.ContextRootProvider;

@Service
public class PropertiesContextRootProvider implements ContextRootProvider {

	@Autowired
	private Set<ContextPathConfig> config;

	@Override
	public String getContextRoot(String url) {
		System.out.println(config);
		return "http://localhost:8666/dia-mockws-war";
	}
}
