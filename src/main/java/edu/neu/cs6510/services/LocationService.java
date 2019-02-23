package edu.neu.cs6510.services;

import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.repositories.LocationRepository;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Service
public class LocationService {
  @Autowired
  LocationRepository locationRepository;



  public ResponseMessage<List<Location>> findAllLocationBasedData() {
    return Result.success((List < Location >) locationRepository.findAll());
  }

  public ResponseMessage<List<Location>> findParents(Integer id) {
    return Result.success(locationRepository.findParents(id));
  }


  public ResponseMessage<List<Location>> findCities(Integer id) {
    return id == null ? Result.success(locationRepository.findAllCities()) : Result.success(locationRepository.findCities(id));
  }


  public ResponseMessage<List<Location>> findStates() {
    return  Result.success(locationRepository.findStates());
  }


  public ResponseMessage<List<Location>> findCounties(@RequestParam(value = "id", required = false) Integer id) {
    return id == null ? Result.success(locationRepository.findAllCounties()) :Result.success(locationRepository.findCounties(id));
  }



}
