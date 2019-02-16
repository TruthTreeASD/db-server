package edu.neu.cs6510.controller;

import edu.neu.cs6510.repositories.PropertyRepository;
import edu.neu.cs6510.services.PropertyService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/api/property/all")
    public ResponseMessage findAllProperties() {
        return propertyService.findAllProperties();
    }

    @ApiOperation(value = "find property information by ids")
    @GetMapping("/api/queryPropertyById")
    public ResponseMessage findPropertyById(@RequestParam("id") List<Integer> ids) {
        return Result.success(propertyService.findPropertyById(ids));
    }
}
