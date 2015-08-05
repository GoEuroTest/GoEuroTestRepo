package com.goeuro.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	private String id;
	private String name;
	private String type;
	private GeoPosition geoPosition;
	
	@JsonProperty("_id")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonProperty("geo_position")
	public GeoPosition getGeoPosition() {
		return geoPosition;
	}
	
	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", type=" + type
				+ ", geoPosition=" + geoPosition + "]";
	}	
}
