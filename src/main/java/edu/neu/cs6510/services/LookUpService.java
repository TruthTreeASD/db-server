package edu.neu.cs6510.services;

import java.util.List;

import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.repositories.LookUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class LookUpService {
    @Autowired
    LookUpRepository lookUpRepository;


    public ResponseMessage findAllRecords() {
        return Result.success(lookUpRepository.findAll());
    }

    public List<LookUpData> findAllRecordsWithAttributeIds() {
        return null;
    }
}
