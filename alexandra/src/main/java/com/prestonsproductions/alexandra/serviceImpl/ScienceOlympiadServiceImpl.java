package com.prestonsproductions.alexandra.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestonsproductions.alexandra.dao.UseCaseDAO;
import com.prestonsproductions.alexandra.service.HttpRequestService;
import com.prestonsproductions.alexandra.service.ScienceOlympiadService;
import com.typesafe.config.Config;


/**
 * @author Preston Frazier
 *
 */
@Service("scienceOlympiadService")
public class ScienceOlympiadServiceImpl implements ScienceOlympiadService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UseCaseDAO springTemplateTableDAO;
	@Autowired
	HttpRequestService httpRequestService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	private Config properties;
	
	@Override
	public String processRegistrationReport() {	
		logger.debug("Processing Registration Report");
		return "Done " + properties.getString("com.prestonsproductions.alexandra.ENVIRONMENT");
	}
	
	
}
