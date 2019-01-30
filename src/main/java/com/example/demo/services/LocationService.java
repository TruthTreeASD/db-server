package com.example.demo.services;

import com.example.demo.model.Location;
import com.example.demo.repositories.LocationRepository;
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
public class LocationService {
  @Autowired
  LocationRepository locationRepository;

  @ApiOperation(value = "find all locations")
  @GetMapping("/api/location/all")
  public List<Location> findAllLocationBasedData() {
    return (List<Location>) locationRepository.findAll();
  }

  @ApiOperation(value = "find cities")
  @GetMapping("/api/location/city")
  public List<Location> findCities(@RequestParam("id") Integer id) {
    return id == null ? locationRepository.findAllCities() : locationRepository.findCities(id);
  }

  @ApiOperation(value = "find all states")
  @GetMapping("/api/location/state")
  public List<Location> findStates() {
    return (List<Location>) locationRepository.findStates();
  }
  @ApiOperation(value = "find all counties")
  @GetMapping("/api/location/county")
  public List<Location> findCounties(@RequestParam("id") Integer id) {
    return id == null ? locationRepository.findAllCounties() : locationRepository.findCounties(id);
  }

}
