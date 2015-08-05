package com.goeuro.test.service;

import com.goeuro.test.dto.Location;

public interface InvokeApiService {

	public Location[] getLocations(final String apiUrl, final String searchText);
}
