package org.tanujb.gateway.service.impl;

import java.io.IOException;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.tanujb.gateway.config.spring.ContextPathConfig;
import org.tanujb.gateway.exception.ApplicationRuntimeException;
import org.tanujb.gateway.exception.ContextNotFoundException;
import org.tanujb.gateway.service.ContextRootProvider;
import org.tanujb.gateway.util.GatewayUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PropertiesContextRootProvider implements ContextRootProvider {

	private static String CONTEXT_CONFIG_FILE = "/environment/context-path-mapping.json";

	private Set<ContextPathConfig> configurations;

	public PropertiesContextRootProvider() {

		ClassPathResource cpr = new ClassPathResource(CONTEXT_CONFIG_FILE);
		try {
			configurations = new ObjectMapper().readValue(cpr.getFile(),
					new TypeReference<Set<ContextPathConfig>>() {
					});
		} catch (IOException e) {
			throw new ApplicationRuntimeException(
					"Error reading context path config", e);
		}
	}

	@Override
	public String getContextRoot(String url) {
		for (ContextPathConfig config : configurations) {
			Set<String> patterns = config.getPatterns();
			if (GatewayUtil.matchesAnyExpression(url, patterns)) {
				return config.getContext();
			}
		}
		throw new ContextNotFoundException(
				"No context found for incoming request");
	}
}
