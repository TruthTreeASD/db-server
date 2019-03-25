package edu.neu.cs6510.dto;

import java.util.ArrayList;
import java.util.List;

import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.model.LookUpData;

/**
 * LocationInfoDTO represents a date-time object.
 * This date-time object represents a part of Location class information and has:
 * 1. location          : Location object having parameters like latitude, longitude, id, type_code, etc
 * 2. attributeValues   : list of LookUpData objects containing values for attributes as per locations
 * 						  for specified years
 */
public class LocationInfoDTO {

	Location location;
	
	List<LookUpData> attributeValues;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<LookUpData> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(List<LookUpData> attributeValues) {
		this.attributeValues = attributeValues;
	}

	public LocationInfoDTO(Location location) {
		this.location = location;
		this.attributeValues = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "LocationInfoDTO{" +
				"location=" + location +
				", attributeValues=" + attributeValues +
				'}';
	}
}
