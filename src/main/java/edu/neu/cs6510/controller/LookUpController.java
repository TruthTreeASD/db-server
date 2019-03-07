package edu.neu.cs6510.controller;

import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.repositories.LookUpRepository;
import edu.neu.cs6510.services.LocationService;
import edu.neu.cs6510.services.LookUpService;
import edu.neu.cs6510.util.Page;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class LookUpController {
    @Autowired
    LookUpService lookUpService;
    
    @Autowired
    LocationService locationService;

    @GetMapping("/api/lookup/all")
    public ResponseMessage findAllRecords() {
        return lookUpService.findAllRecords();
    }
    
    @ApiOperation(value = "Find values for specified attributes and year for all locations at same level")
    @GetMapping("/api/lookup/location&year&attributeIds")
    public ResponseMessage findRecordsAtSameLevel(
    		@RequestParam(value = "attributes") List<Integer> attributeIds,
    		@RequestParam(value = "location") int locationId,
    		@RequestParam(value = "year") int year){
    	
    	return lookUpService.findRecordsAtSameLevel(locationId, year, attributeIds);
    }
    
    @ApiOperation(value = "Find values for specified attributes and year for all locations with same parent")
    @GetMapping("/api/lookup/location&year&attributeId")
    public ResponseMessage findRecordsForSameParent(
    		@RequestParam(value = "attributes") List<Integer> attributeIds,
    		@RequestParam(value = "location") int locationId,
    		@RequestParam(value = "year") int year){
    	
    	return lookUpService.findRecordsForSameParent(locationId, year, attributeIds);
    }

    @ApiOperation(value = "query values for given attribute and year/locationId/typeCode", notes = "")
    @GetMapping("/api/findValue")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBy", value = "order by key word: name for location name, year for current year, value for current value"),
            @ApiImplicitParam(name = "from", value = "range begin, default: 0"),
            @ApiImplicitParam(name = "to", value = "range end, default: Integer.MAX_VALUE")})
    public ResponseMessage<Page> findRecords(@RequestParam(value = "attributeId") List<Integer> attributeId,
                                             @RequestParam(value = "year", required = false) List<Integer> year,
                                             @RequestParam(value = "locationId", required = false) List<Integer> locationId,
                                             @RequestParam(value = "typeCode", required = false) Integer typeCode,
                                             @RequestParam(value = "parentId", required = false) Integer parentId,
                                             @RequestParam(value = "orderBy", defaultValue = "year") String orderBy,
                                             @RequestParam(value = "order", defaultValue = "DESC") String order,
                                             @RequestParam(value = "from", defaultValue = "0") Integer from,
                                             @RequestParam(value = "to", defaultValue = "2147483647") Integer to,
                                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                             @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage) {
        return lookUpService.findAvailbeAttr(attributeId, year, locationId, typeCode, parentId,orderBy, order, from, to, pageSize, currentPage);
    }



    public List<LookUpData> findAllRecordsWithAttributeIds() {
        return null;
    }
}
