package com.goeuro.test.service;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.goeuro.test.dto.Location;
import com.goeuro.test.exception.GoEuroTestException;

@Service
public class CSVFileWriterServiceImpl implements CSVFileWriterService {

	private static final Logger logger = LoggerFactory.getLogger(CSVFileWriterServiceImpl.class);
	
	// CSV file header
	private static final Object[] FILE_HEADER = {"_id", "name", "type", "latitude", "longitude"};

	@Override
	public void saveRecords(final String fileName, final Object[] records) {
		
		// Create the CSVFormat object
		CSVFormat format = CSVFormat.RFC4180;
		
		// try-with-resources
		try (final CSVPrinter csvFilePrinter = new CSVPrinter(new FileWriter(fileName), format)) {

			// Create CSV file header
			csvFilePrinter.printRecord(FILE_HEADER);
			
			for (Object record : records) {
				
				if (record == null) {
					continue;
				}
				
				Location location = (Location)record;
				
				String id = location.getId();
				String name = location.getName();
				String type = location.getType();
				String latitude = null;
				String longitude = null;
				
				if (location.getGeoPosition() != null) {
					latitude = location.getGeoPosition().getLatitude();
					longitude = location.getGeoPosition().getLongitude();
				}
				
				List<String> locationDataRecord = new ArrayList<String>();
				
				locationDataRecord.add(id);
				locationDataRecord.add(name);
				locationDataRecord.add(type);
				locationDataRecord.add(latitude);
				locationDataRecord.add(longitude);
				
				// Write a location object to the CSV file.
				csvFilePrinter.printRecord(locationDataRecord);
			}
			
		} catch (Exception e) {
			throw new GoEuroTestException("failed to write to output CSV file: " + fileName, e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("CSV file with name '{}' was created successfully.", fileName);
		}
	}
}
