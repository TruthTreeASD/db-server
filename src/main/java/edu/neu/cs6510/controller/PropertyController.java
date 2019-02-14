package edu.neu.cs6510.controller;

import edu.neu.cs6510.repositories.PropertyRepository;
import edu.neu.cs6510.services.PropertyService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/api/property/all")
    public ResponseMessage findAllProperties() {
        return propertyService.findAllProperties();
    }
}
