package edu.neu.cs6510.controller;

import edu.neu.cs6510.repositories.PropertyRepository;
import edu.neu.cs6510.services.PropertyService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PropertyController {

	@Autowired
	PropertyService propertyService;

	/**
	 * Method to get all the properties.
	 * 
	 * @return List of all properties in the response.
	 */
	@GetMapping("/api/property/all")
	public ResponseMessage findAllProperties() {
		return propertyService.findAllProperties();
	}

	/**
	 * Method to give information about a property by property id.
	 * 
	 * @param ids
	 *            list of properties
	 * @return List of Property DTO in the response which has property information
	 */
	@ApiOperation(value = "find property information by ids")
	@GetMapping("/api/queryPropertyById")
	public ResponseMessage findPropertyById(@RequestParam("id") List<Integer> ids) {
		return Result.success(propertyService.findPropertyById(ids));
	}
}
