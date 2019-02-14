package edu.neu.cs6510.controller;

import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.repositories.LookUpRepository;
import edu.neu.cs6510.services.LookUpService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class LookUpController {
    @Autowired
    LookUpService lookUpService;

    @GetMapping("/api/lookup/all")
    public ResponseMessage findAllRecords() {
        return lookUpService.findAllRecords();
    }

    public List<LookUpData> findAllRecordsWithAttributeIds() {
        return null;
    }
}
