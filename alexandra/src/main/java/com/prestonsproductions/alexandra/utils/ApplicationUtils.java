package com.prestonsproductions.alexandra.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.typesafe.config.Config;

/**
 * @author Preston Frazier
 *
 */
@Component
public class ApplicationUtils {
	private final static Logger logger = LoggerFactory.getLogger(ApplicationUtils.class);
	private static Config properties;
	
	@Autowired
	public ApplicationUtils(Config config) {
		ApplicationUtils.properties = config;
	}
	
	public static String getEnvironmentName() {
		logger.debug("Processing Alexandra Utility");	
		return properties.getString("com.prestonsproductions.alexandra.ENVIRONMENT");
	}
    

}
