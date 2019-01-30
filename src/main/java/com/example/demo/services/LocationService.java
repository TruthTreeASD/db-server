package com.example.demo.services;

import com.example.demo.model.Location;
import com.example.demo.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    @GetMapping("/api/location/all")
    public List<Location> findAllLocationBasedData() {
        return (List<Location>) locationRepository.findAll();
    }

    @GetMapping("/api/location/state/all")
    public List<Location> findAllStates() {
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        List<Location> stateList = new ArrayList<>();
        for(Location location: locationList){
            if (location.getType_code()  == 0){
                stateList.add(location);
            }
        }
        return stateList;
    }

    @GetMapping("/api/location/county/all")
    public List<Location> findAllCounties() {
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        List<Location> countyList = new ArrayList<>();
        for(Location location: locationList){
            if (location.getType_code()  == 1){
                countyList.add(location);
            }
        }
        return countyList;

    }

    @GetMapping("/api/location/city/all")
    public List<Location> findAllCities() {
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        List<Location> cityList = new ArrayList<>();
        for(Location location: locationList){
            if (location.getType_code()  == 2){
                cityList.add(location);
            }
        }
        return cityList;

    }

    @GetMapping("/api/location/town/all")
    public List<Location> findAllTowns() {
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        List<Location> townList = new ArrayList<>();
        for(Location location: locationList){
            if (location.getType_code()  == 3){
                townList.add(location);
            }
        }
        return townList;

    }

    @GetMapping("/api/location/district/all")
    public List<Location> findAllDistricts() {
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        List<Location> districtList = new ArrayList<>();
        for(Location location: locationList){
            if (location.getType_code()  == 4){
                districtList.add(location);
            }
        }
        return districtList;

    }

    @GetMapping("/api/location/other/all")
    public List<Location> findAllOtherLocations() {
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        List<Location> otherList = new ArrayList<>();
        for(Location location: locationList){
            if (location.getType_code()  == 5){
                otherList.add(location);
            }
        }
        return otherList;
    }

    @GetMapping("/api/location/city")
    public List<Location> findCities(@RequestParam("id") Integer id) {
        return (List<Location>) locationRepository.findAll();
    }
    @GetMapping("/api/location/state")
    public List<Location> findStates() {
        return (List<Location>) locationRepository.findAll();
    }
    @GetMapping("/api/location/county")
    public List<Location> findCounties(@RequestParam("id") Integer id) {
        return (List<Location>) locationRepository.findAll();
    }

}
