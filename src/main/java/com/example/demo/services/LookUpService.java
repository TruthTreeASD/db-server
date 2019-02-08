package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LookUpData;
import com.example.demo.repositories.LookUpRepository;

@RestController
@CrossOrigin(origins="*")
public class LookUpService {
    @Autowired
    LookUpRepository lookUpRepository;

    @GetMapping("/api/lookup/all")
    public List<LookUpData> findAllRecords() {
        return (List<LookUpData>) lookUpRepository.findAll();
    }

    public List<LookUpData> findAllRecordsWithAttributeIds() {
        return null;
    }
}
