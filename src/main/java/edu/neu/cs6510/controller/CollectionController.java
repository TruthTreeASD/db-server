package edu.neu.cs6510.controller;

import edu.neu.cs6510.dto.*;
import edu.neu.cs6510.model.AttributeMapping;
import edu.neu.cs6510.model.Property;
import edu.neu.cs6510.repositories.AttributeMappingRepository;
import edu.neu.cs6510.repositories.CollectionRepository;
import edu.neu.cs6510.repositories.PropertyRepository;
import edu.neu.cs6510.services.CollectionService;
import edu.neu.cs6510.util.BeanMapper;
import edu.neu.cs6510.util.cache.CacheService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    /**
     * Method that gives all the collectionns.
     * @return collection list in the response
     */
    @GetMapping("/api/collection/all")
    public ResponseMessage findAllCollections() {
        return collectionService.findAllCollections();
    }

    /**
     * Method to return attributes by either level, year or id
     * @param level state, city or county
     * @param year 
     * @param id attribute id
     * @return
     */
    @ApiOperation(value = "find Availbe Attr by location/ location level/ given year")
    @GetMapping("/api/collections")
    public ResponseMessage findAvailbeAttr1(@RequestParam(value = "level", required = false) String level,
                                           @RequestParam(value = "year", required = false) Integer year,
                                           @RequestParam(value = "id", required = false) Integer id) {
        return collectionService.findAvailbeAttr1(level, year, id);
    }
    
    /**
     * Method to return time-range for given location and attribute id
     * @param level state, city or county
     * @param attributes list of attributes
     * @return Collections for the location and attribute id
     */
    @ApiOperation(value = "find time-range for given location level and attr id")
    @GetMapping("/api/time_range")
    public ResponseMessage<List<CollectionTimeRangeDTO>> findAvailbeAttr1(@RequestParam(value = "level") String level, @RequestParam("attributes") List<Integer> attributes) {

        return collectionService.findAvailbeAttr(level, attributes);
    }

    /**
     * Method to find attributes by collection id
     * @param id the collection id
     * @return matching Attributes
     */
    @ApiOperation(value = "find Availbe Attr by collection id")
    @GetMapping("/api/collectionSet")
    public ResponseMessage findAvailbeAttr(@RequestParam(value = "id") Integer id) {
        return collectionService.findAvailbeAttr(id);
    }

    /**
     * Method to find Availbe Attr by collection and property pairs
     * @param collection_ids 
     * @param property_ids
     * @return Matching attributes
     */
    @ApiOperation(value = "find Availbe Attr by collection and property pairs")
    @GetMapping("/api/queryAttriIdByCombination")
    public ResponseMessage findAvailbeAttr(@RequestParam(value = "collection_ids") List<Integer> collection_ids,
                                           @RequestParam(value = "property_ids") List<Integer> property_ids) {

        return collectionService.findAvailbeAttr(collection_ids, property_ids);
    }

    
    /**
     * Information for collections for the given ids
     * @param ids collection ids
     * @return Collection Information
     */
    @ApiOperation(value = "find collection information by ids")
    @GetMapping("/api/queryCollectionById")
    public ResponseMessage findCollectionByIds(@RequestParam("id") List<Integer> ids) {
        return Result.success(collectionService.findCollectionByIds(ids));
    }

    /**
     * Method to find Available Attr by location/ location level/ given year
     * @param level either state, city or county
     * @param year 
     * @param id is the Location id
     * @return available attribute list
     */
    @ApiOperation(value = "find Available Attr by location/ location level/ given year")
    @GetMapping("/api/queryAvailableAttributes")
    public ResponseMessage findAvailbeAttr(@RequestParam(value = "level", required = false) String level,
                                           @RequestParam(value = "year", required = false, defaultValue = "-1") Integer year,
                                           @RequestParam(value = "locationId", required = false) Integer id) {
        return collectionService.findAvailbeAttr(level, year, id);
    }

    /**
     * Method to find time-range for given location level and attr id
     * @param level either state, city or county
     * @param attributes
     * @return available attribute list
     */
    @ApiOperation(value = "find time-range for given location level and attr id")
    @GetMapping("/api/time_range_new")
    public ResponseMessage findAvailbeAttr(@RequestParam(value = "level") String level, @RequestParam("attributes") List<Integer> attributes) {

        return collectionService.attriTimeRange(level, attributes);
    }

    public static void main(String[] args) {
        System.out.println(1 << 5);
    }

}
