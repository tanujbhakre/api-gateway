package org.tanujb.gateway.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({ "org.tanujb.gateway.*" })
@Import({ SecurityConfig.class })
public class AppConfig {

}