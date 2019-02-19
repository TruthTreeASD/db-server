package edu.neu.cs6510.dto;

import java.util.ArrayList;
import java.util.List;

import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.model.LookUpData;

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

}
