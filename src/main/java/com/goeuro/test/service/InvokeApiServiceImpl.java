package com.goeuro.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.goeuro.test.dto.Location;

@Service
public class InvokeApiServiceImpl implements InvokeApiService {
	
	private static final Logger logger = LoggerFactory.getLogger(InvokeApiServiceImpl.class);

	@Resource
	private RestTemplate restTemplate;

	@Override
	public Location[] getLocations(final String apiUrl, final String searchText) {

		final String urlWithParam = apiUrl + searchText;
		
		if (logger.isDebugEnabled()) {
			logger.debug("going to issue a request to the following api url (including search parameter): {}", urlWithParam);
		}

		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		// Set the Accept and Content type header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<?> entity = new HttpEntity<Object>(headers);

		// Make the HTTP GET request, marshalling the response from JSON to an array of Locations
		ResponseEntity<Location[]> responseEntity = restTemplate.exchange(urlWithParam, HttpMethod.GET, 
																				entity, Location[].class);
				
		Location[] locations = responseEntity.getBody();
		
		if (logger.isDebugEnabled()) {
			logger.debug("retrieved {} locations from the api that matches the search term '{}'", locations.length, searchText);
			for (int i = 0 ; i < locations.length ; i++) {
				logger.debug("locations[{}]={}", i, locations[i]);
			}
		}
		
		return locations;
	}

}
