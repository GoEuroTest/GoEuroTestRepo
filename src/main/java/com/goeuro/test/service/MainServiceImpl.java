package com.goeuro.test.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.goeuro.test.UserInput;
import com.goeuro.test.config.AppProperties;
import com.goeuro.test.dto.Location;

@Service
public class MainServiceImpl implements MainService {

	private static final Logger logger = LoggerFactory.getLogger(MainServiceImpl.class);
	
	@Resource
	private AppProperties appProperties;
	
	@Resource
	private UserInputService userInputService;
	
	@Resource
	private InvokeApiService invokeApiService;
	
	@Resource
	private CSVFileWriterService csvFileWriterService;
	
	@Override
	public void execute(final String searchText) {
		
		UserInput userInput = userInputService.getUserInput(searchText);
		
		if (logger.isDebugEnabled()) {
			logger.debug("userInput={}", userInput);
		}
		
		final String apiUrl = appProperties.getApiUrl();
		
		Location[] locations = invokeApiService.getLocations(apiUrl, searchText);
		
		final String fileName = appProperties.getDefaultOutputFileName();
		
		csvFileWriterService.saveRecords(fileName, locations);
	}
}
