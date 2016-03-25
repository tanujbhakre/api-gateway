package org.tanujb.gateway.config.spring;

import java.io.IOException;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.tanujb.gateway.exception.ApplicationRuntimeException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tells spring container to be web aware
 * 
 * @author tbhakre
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan({ "org.tanujb.gateway.*" })
@Import({ SecurityConfig.class })
public class AppConfig {

	private static String CONTEXT_CONFIG_FILE = "/environment/context-path-mapping.json";

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public Set<ContextPathConfig> contextPathConfig() {
		System.out
				.println("***********************************************************************************************************************");
		Set<ContextPathConfig> config = null;
		ClassPathResource cpr = new ClassPathResource(CONTEXT_CONFIG_FILE);
		try {
			config = new ObjectMapper().readValue(cpr.getFile(),
					new TypeReference<Set<ContextPathConfig>>() {
					});
			for (ContextPathConfig context : config) {
				System.out.println(context);
			}
		} catch (IOException e) {
			throw new ApplicationRuntimeException(
					"Error reading context path config", e);
		}
		return config;
	}
}