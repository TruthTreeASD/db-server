package com.example.demo.services;

import com.example.demo.model.Collection;
import com.example.demo.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class CollectionService {
    @Autowired
    CollectionRepository collectionRepository;

    @GetMapping("/api/collection/all")
    public List<Collection> findAllCollections() {
        return (List<Collection>) collectionRepository.findAll();
    }
}
