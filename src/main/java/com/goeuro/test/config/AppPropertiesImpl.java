package com.goeuro.test.config;

import com.goeuro.test.exception.GoEuroTestException;

public class AppPropertiesImpl implements AppProperties {

	private final String apiUrl; // required
	private final String defaultOutputFileName; // required
	
	private AppPropertiesImpl(AppPropertiesImplBuilder builder) {
		this.apiUrl = builder.apiUrl;
		this.defaultOutputFileName = builder.defaultOutputFileName;
	}

	@Override
	public String getApiUrl() {
		return apiUrl;
	}
	
	@Override
	public String getDefaultOutputFileName() {
		return defaultOutputFileName;
	}
	
	public static class AppPropertiesImplBuilder {
		
		private final String apiUrl;
		private final String defaultOutputFileName;
		
		public AppPropertiesImplBuilder(String apiUrl, String defaultOutputFileName) {
			
			if (apiUrl == null || apiUrl.isEmpty()) {
				throw new GoEuroTestException("missing required property: apiUrl");
			}
			
			if (defaultOutputFileName == null || defaultOutputFileName.isEmpty()) {
				throw new GoEuroTestException("missing required property: defaultOutputFileName");
			}
			
			this.apiUrl = apiUrl;
			this.defaultOutputFileName = defaultOutputFileName;
		}

		public AppPropertiesImpl build() {
			return new AppPropertiesImpl(this);
		}
	}
}
