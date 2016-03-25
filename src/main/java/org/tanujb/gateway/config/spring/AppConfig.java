package org.tanujb.gateway.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}