package com.example.demo.services;

import com.example.demo.dto.AttributeInfoDTO;
import com.example.demo.dto.CollectionsDTO;
import com.example.demo.dto.PropertyDTO;
import com.example.demo.model.AttributeMapping;
import com.example.demo.model.Location;
import com.example.demo.model.Collection;
import com.example.demo.model.Property;
import com.example.demo.repositories.AttributeMappingRepository;
import com.example.demo.repositories.CollectionRepository;
import com.example.demo.repositories.LocationRepository;
import com.example.demo.repositories.PropertyRepository;
import com.example.demo.util.BeanMapper;
import com.example.demo.util.http.ResponseMessage;
import com.example.demo.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*")
@Api(value = "location apis")
public class LocationService {
  @Autowired
  LocationRepository locationRepository;


  @ApiOperation(value = "find all locations")
  @GetMapping("/api/location/all")
  public ResponseMessage<List<Location>> findAllLocationBasedData() {
    return Result.success((List < Location >) locationRepository.findAll());
  }

  @ApiOperation(value = "find cities")
  @GetMapping("/api/location/city")
  public ResponseMessage<List<Location>> findCities(@RequestParam(value = "id", required = false) Integer id) {
    return id == null ? Result.success(locationRepository.findAllCities()) : Result.success(locationRepository.findCities(id));
  }

  @ApiOperation(value = "find all states")
  @GetMapping("/api/location/state")
  public ResponseMessage<List<Location>> findStates() {
    return  Result.success(locationRepository.findStates());
  }

  @ApiOperation(value = "find all counties")
  @GetMapping("/api/location/county")
  public ResponseMessage<List<Location>> findCounties(@RequestParam(value = "id", required = false) Integer id) {
    return id == null ? Result.success(locationRepository.findAllCounties()) :Result.success(locationRepository.findCounties(id));
  }



}
