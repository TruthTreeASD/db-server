package edu.neu.cs6510.services;

import edu.neu.cs6510.repositories.PropertyRepository;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    public ResponseMessage findAllProperties() {
        return Result.success(propertyRepository.findAll());
    }

    public ResponseMessage findPropertyById(List<Integer> ids) {
        return Result.success(propertyRepository.findAllById(ids));
    }
}
