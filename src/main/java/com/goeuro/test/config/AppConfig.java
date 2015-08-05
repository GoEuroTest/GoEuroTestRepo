package com.goeuro.test.config;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

// Marks this class as configuration
@Configuration
// Specifies which package to scan
@ComponentScan(basePackageClasses = {com.goeuro.test.service.ScanMarker.class})
@PropertySource({"classpath:spring-profiles-active.properties", 
				 "classpath:app-${spring.profiles.active}.properties"})
public class AppConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	private static final String PROPERTY_NAME_API_URL = "api.url";
	private static final String PROPERTY_NAME_DEFAULT_OUTPUT_FILE_NAME = "default.output.file.name";
	
	@Resource
	private Environment env;
	
	@Bean
	public AppProperties appProperties() {
		
		final String apiUrl = env.getProperty(PROPERTY_NAME_API_URL);
		final String defaultOutputFileName = env.getProperty(PROPERTY_NAME_DEFAULT_OUTPUT_FILE_NAME);
		
		if (logger.isDebugEnabled()) {
			logger.debug("apiUrl={}", apiUrl);
			logger.debug("defaultOutputFileName={}", defaultOutputFileName);
		}
		
		return new AppPropertiesImpl.AppPropertiesImplBuilder(apiUrl, defaultOutputFileName).build();
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    
		// RestTemplate is thread-safe
		RestTemplate restTemplate = new RestTemplate();
		
		// Add the Jackson message converter
		List<HttpMessageConverter<?>> msgConverters = restTemplate.getMessageConverters();
		msgConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(msgConverters);
		
		return restTemplate;
	}
}
