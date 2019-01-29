package com.example.demo.services;

import com.example.demo.model.Property;
import com.example.demo.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @GetMapping("/api/property/all")
    public List<Property> findAllProperties() {
        return (List<Property>) propertyRepository.findAll();
    }
}
