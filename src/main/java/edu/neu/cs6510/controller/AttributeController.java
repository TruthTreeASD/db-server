package edu.neu.cs6510.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.cs6510.services.AttributeService;
import edu.neu.cs6510.util.http.ResponseMessage;
import io.swagger.annotations.ApiOperation;

/**
 * Controller Class for Getting Attributes
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class AttributeController {

	@Autowired
	AttributeService attributeService;

	/**
	 * A method that takes a level as a parameter and gives related attributes.
	 * 
	 * @param level
	 *            is the state, city or county
	 * @return attributes for the level
	 */
	@ApiOperation(value = "find Availbe Attr by location level")
	@GetMapping("/api/attributes")
	public ResponseMessage findAllAttributesForSpecificStates(
			@RequestParam(value = "level", required = false) String level) {
		return attributeService.findAllAttributesForSpecificStates(level);
	}

	/**
	 * A method that takes a list of collections as a parameter and returns
	 * Attributes for the collections.
	 * 
	 * @param collection
	 *            as a list
	 * @return matching attributes
	 */
	@ApiOperation(value = "find attributes for a collection")
	@GetMapping("/api/attributes/collection")
	public ResponseMessage findAllAttributesForACollection(
			@RequestParam(value = "collection") List<Integer> collection) {
		return attributeService.findAllAttributesForACollection(collection);
	}

	/**
	 * A method that takes list of collection and properties as params and gives
	 * attributes matching with the collection and properties.
	 * 
	 * @param collection
	 *            list of collections to be checked
	 * @param property
	 *            list of properties for collection
	 * @return
	 */
	@ApiOperation(value = "find attributes for a collection and property [not in use]")
	@GetMapping("/api/attributes/collection&property")
	public ResponseMessage findAllAttributesForCollectionAndProperty(
			@RequestParam(value = "collection") List<Integer> collection,
			@RequestParam(value = "property") List<Integer> property) {
		return attributeService.findAllAttributesForCollectionAndProperty(collection, property);
	}

	/**
	 * Find the collection and properties for the states which donot have null
	 * values.
	 * 
	 * @param collection
	 *            list of collection
	 * @param property
	 *            list of properties
	 * @param states
	 *            list of locations
	 * @return list of attributes
	 */
	@ApiOperation(value = "find attributes for a collection and property for specific states")
	@GetMapping("/api/attributes/collection&property&states")
	public ResponseMessage findAllAttributesForCollectionAndPropertyAndStates(
			@RequestParam(value = "collection") List<Integer> collection,
			@RequestParam(value = "property") List<Integer> property,
			@RequestParam(value = "location") List<Integer> states) {
		return attributeService.findAllAttributesForCollectionAndPropertyAndStates(collection, property, states);
	}

	/**
	 * Attributes for attribute id
	 * 
	 * @param attributeIds
	 * @return matching attributes
	 */
	@ApiOperation(value = "find attributes by specified id")
	@GetMapping("/api/attributes/attributeIds")
	public ResponseMessage findAllAttributesByAttributeIds(
			@RequestParam(value = "attributes") List<Integer> attributeIds) {
		return attributeService.findAllAttributesByAttributeIds(attributeIds);
	}

	/**
	 * method to find attributes by specified id for specific states
	 * 
	 * @param attributeIds
	 * @param stateIds
	 * @return attribute in response
	 */
	@ApiOperation(value = "find attributes by specified id for specific states")
	@GetMapping("/api/attributes/attributeIds&states")
	public ResponseMessage findAllAttributesByAttributeIdsForStates(
			@RequestParam(value = "attributes") List<Integer> attributeIds,
			@RequestParam(value = "state") List<Integer> stateIds) {
		return attributeService.findAllAttributesByAttributeIdsForStates(attributeIds, stateIds);
	}

	/**
	 * Method to find attributes by specified id for specific states within year
	 * range
	 * 
	 * @param attributeIds
	 *            list of attributes
	 * @param stateIds
	 *            list of states
	 * @param yearRange
	 *            the range of year
	 * @return values for the states for given years and ranges
	 */
	@ApiOperation(value = "find attributes by specified id for specific states within year range")
	@GetMapping("/api/attributes/attributeIds&states&yearRange")
	public ResponseMessage findAllAttributesByAttributeIdsForStatesInYears(
			@RequestParam(value = "attributes") List<Integer> attributeIds,
			@RequestParam(value = "state") List<Integer> stateIds,
			@RequestParam(value = "yearRange") List<Integer> yearRange) {
		return attributeService.findAllAttributesByAttributeIdsForStatesInYears(attributeIds, stateIds, yearRange);
	}

	/**
	 * method to find attributes by specified id for specific states within year
	 * range
	 * 
	 * @param attributeIds
	 *            list of attributes
	 * @param stateIds
	 *            list of states
	 * @param yearRange
	 *            the range of year
	 * @return values for the states for given years and ranges
	 */
	@ApiOperation(value = "find attributes by specified id for specific states within year range")
	@GetMapping("/api/attributes/attributeIds&states&yearList")
	public ResponseMessage findAllAttributesByIdsForStatesAndYears(
			@RequestParam(value = "attributes") List<Integer> attributeIds,
			@RequestParam(value = "state") List<Integer> stateIds,
			@RequestParam(value = "yearList") List<Integer> yearList) {
		return attributeService.findAllAttributesByIdsForStatesAndYears(attributeIds, stateIds, yearList);
	}

	/**
	 * Method that gives attribute information fo rthe given ids
	 * @param ids which is the attribute id
	 * @return matching attributes
	 */
	@ApiOperation(value = "find attribute information by ids")
	@GetMapping("/api/queryAttriById")
	public ResponseMessage queryAttriById(@RequestParam(value = "ids") List<Integer> ids) {

		return attributeService.findAttriByIds(ids);
	}

}
