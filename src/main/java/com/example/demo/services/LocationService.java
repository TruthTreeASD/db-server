package com.example.demo.services;

import com.example.demo.model.Location;
import com.example.demo.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
