package com.colin.app.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebCorsConfig extends WebMvcConfigurerAdapter {

	@Value("${server.cors.origin}")
	private String ORIGIN;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/secure/api/**")
    			.allowedOrigins(ORIGIN)
    			.allowedHeaders("*")
    			.allowedMethods("GET", "POST", "DELETE")
    			.exposedHeaders( "Authorization" );
    }
}