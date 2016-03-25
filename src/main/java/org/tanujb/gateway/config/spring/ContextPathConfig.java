package org.tanujb.gateway.config.spring;

import java.util.Set;

public class ContextPathConfig {

	private Set<String> patterns;
	private String context;

	public Set<String> getPatterns() {
		return patterns;
	}

	public void setPatterns(Set<String> patterns) {
		this.patterns = patterns;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		String value = context + ", [ ";
		for (String pattern : patterns) {
			value += pattern + " ";
		}
		value += " ] ";
		return value;
	}
}
