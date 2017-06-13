package com.wd.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebCORSConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry
			.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST")
			.allowCredentials(true)
			.maxAge(3600);
	
	}

	
	
}
