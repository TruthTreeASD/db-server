package edu.neu.cs6510.controller;

import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.repositories.LocationRepository;
import edu.neu.cs6510.services.LocationService;
import edu.neu.cs6510.util.Page;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@Api(value = "location apis")
public class LocationController {
  @Autowired
  LocationService locationService;


  @ApiOperation(value = "find all locations")
  @GetMapping("/api/location/all")
  public ResponseMessage<List<Location>> findAllLocationBasedData() {
    return locationService.findAllLocationBasedData();
  }

  @ApiOperation(value = "find cities")
  @GetMapping("/api/location/city")
  public ResponseMessage<List<Location>> findCities(@RequestParam(value = "id", required = false) Integer id) {
    return locationService.findCities(id);
  }

  @ApiOperation(value = "find all states")
  @GetMapping("/api/location/state")
  public ResponseMessage<List<Location>> findStates() {
    return locationService.findStates();
  }

  @ApiOperation(value = "find all counties")
  @GetMapping("/api/location/county")
  public ResponseMessage<List<Location>> findCounties(@RequestParam(value = "id", required = false) Integer id) {
    return locationService.findCounties(id);
  }

  @ApiOperation(value = "find all parents")
  @GetMapping("/api/location/queryParents")
  public ResponseMessage<List<Location>> findParents(@RequestParam(value = "id") Integer id) {
    return locationService.findParents(id);
  }

  @ApiOperation(value = "find by id")
  @GetMapping("/api/location/queryById")
  public ResponseMessage<Location> findById(@RequestParam(value = "id") Integer id) {
    return locationService.queryById(id);
  }

  @ApiOperation(value = "find all counties")
  @GetMapping("/api/location")
  public ResponseMessage<Page<Location>> findLocPagination(@RequestParam(value = "id", required = false) Integer id,
                                                           @RequestParam(value = "typeCode", defaultValue = "-1") Integer typeCode,
                                                           @RequestParam(value = "parentId", required = false) Integer parentId,
                                                           @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                           @RequestParam(value = "order", defaultValue = "ASC") String order,
                                                           @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                           @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage) {
    return locationService.findLocPagination(id, typeCode, parentId,orderBy, order, pageSize, currentPage);
  }


}
