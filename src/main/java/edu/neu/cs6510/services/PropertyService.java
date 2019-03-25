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

    /**
     * Method to get all the properties.
     * @return List of all properties in the response.
     */
    public ResponseMessage findAllProperties() {
        return Result.success(propertyRepository.findAll());
    }

    /**
     * Method to give information about a property by property id.
     * @param ids list of properties
     * @return List of Property DTO in the response which has property information
     */
    public ResponseMessage findPropertyById(List<Integer> ids) {
        return Result.success(propertyRepository.findAllById(ids));
    }
}
