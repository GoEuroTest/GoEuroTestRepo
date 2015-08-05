package com.goeuro.test.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.goeuro.test.UserInput;
import com.goeuro.test.exception.GoEuroTestException;

@Service
public class UserInputServiceImpl implements UserInputService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInputServiceImpl.class);

	@Override
	public UserInput getUserInput(String searchText) {

		UserInput userInput;
		
		if (searchText != null) {
			userInput = new UserInput(searchText);
		} else {
			
			// if the user did't specify a search text parameter on the command-line,
			// ask her/him to do so now.
			
			// try-with-resources
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

				while (true) {

					System.out.println("Enter text phrase to search the api:");
					
					searchText = br.readLine();

					if (searchText == null) {
						continue;
					} else {
						searchText = searchText.trim();
						
						if (searchText.isEmpty()) {
							continue;
						}
					}
					
					break;
				}

			} catch (IOException e) {
				throw new GoEuroTestException("failed to read user input", e);
			}
			
			userInput =  new UserInput(searchText);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("userInput={}", userInput);
		}
		
		return userInput;
	}
}
