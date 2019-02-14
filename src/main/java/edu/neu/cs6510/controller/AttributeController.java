package edu.neu.cs6510.controller;

import edu.neu.cs6510.model.AttributeMapping;
import edu.neu.cs6510.model.AttributeValue;
import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.repositories.AttributeMappingRepository;
import edu.neu.cs6510.repositories.LookUpRepository;
import edu.neu.cs6510.services.AttributeService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AttributeController {

    @Autowired
    AttributeService attributeService;


    @ApiOperation(value = "find Availbe Attr by location level")
    @GetMapping("/api/attributes")
    public ResponseMessage findAllAttributesForSpecificStates(@RequestParam(value = "level") String level) {
        return attributeService.findAllAttributesForSpecificStates(level);
    }


    @ApiOperation(value = "find attributes for a collection")
    @GetMapping("/api/attributes/collection")
    public ResponseMessage findAllAttributesForACollection(@RequestParam(value = "collection") List<Integer> collection) {
        return attributeService.findAllAttributesForACollection(collection);
    }

    @ApiOperation(value = "find attributes for a collection and property")
    @GetMapping("/api/attributes/collection&property")
    public ResponseMessage findAllAttributesForCollectionAndProperty(
            @RequestParam(value = "collection") List<Integer> collection,
            @RequestParam(value = "property") List<Integer> property) {
        return attributeService.findAllAttributesForCollectionAndProperty(collection, property);
    }

    @ApiOperation(value = "find attributes for a collection and property for specific states")
    @GetMapping("/api/attributes/collection&property&states")
    public ResponseMessage findAllAttributesForCollectionAndPropertyAndStates(@RequestParam(value = "collection") List<Integer> collection,
                                                                                   @RequestParam(value = "property") List<Integer> property,
                                                                                   @RequestParam(value = "location") List<Integer> states) {
        return attributeService.findAllAttributesForCollectionAndPropertyAndStates(collection, property, states);
    }

    @ApiOperation(value = "find attributes by specified id")
    @GetMapping("/api/attributes/attributeIds")
    public ResponseMessage findAllAttributesByAttributeIds(
            @RequestParam(value = "attributes") List<Integer> attributeIds) {
        return attributeService.findAllAttributesByAttributeIds(attributeIds);
    }

    @ApiOperation(value = "find attributes by specified id for specific states")
    @GetMapping("/api/attributes/attributeIds&states")
    public ResponseMessage findAllAttributesByAttributeIdsForStates(
            @RequestParam(value = "attributes") List<Integer> attributeIds,
            @RequestParam(value = "state") List<Integer> stateIds) {
        return attributeService.findAllAttributesByAttributeIdsForStates(attributeIds, stateIds);
    }


    @ApiOperation(value = "find attributes by specified id for specific states within year range")
    @GetMapping("/api/attributes/attributeIds&states&yearRange")
    public ResponseMessage findAllAttributesByAttributeIdsForStatesInYears(
            @RequestParam(value = "attributes") List<Integer> attributeIds,
            @RequestParam(value = "state") List<Integer> stateIds,
            @RequestParam(value = "yearRange") List<Integer> yearRange) {
        return attributeService.findAllAttributesByAttributeIdsForStatesInYears(attributeIds, stateIds, yearRange);
    }

    @ApiOperation(value = "find attributes by specified id for specific states within year range")
    @GetMapping("/api/attributes/attributeIds&states&yearList")
    public ResponseMessage findAllAttributesByIdsForStatesAndYears(
            @RequestParam(value = "attributes") List<Integer> attributeIds,
            @RequestParam(value = "state") List<Integer> stateIds,
            @RequestParam(value = "yearList") List<Integer> yearList) {
        return attributeService.findAllAttributesByIdsForStatesAndYears(attributeIds, stateIds, yearList);
    }

}

