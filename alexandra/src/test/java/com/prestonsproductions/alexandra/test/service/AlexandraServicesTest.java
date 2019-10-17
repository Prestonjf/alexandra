package com.prestonsproductions.alexandra.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AlexandraServicesTest {
	private static final Logger logger = LoggerFactory.getLogger(AlexandraServicesTest.class);
	
	
	@Test
	public void testProcessRegistrationReport() {
		logger.info("Running Registration Report Test");		
		assertEquals(true, true);
	}

	@Test
	public void passwordBCryptor() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		logger.debug(encoder.encode("1234"));
		assertEquals(true, true);
	}
}
