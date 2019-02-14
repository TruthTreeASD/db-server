package com.example.demo.util.cache;

import com.example.demo.model.AttributeMapping;
import com.example.demo.model.Collection;
import com.example.demo.model.Property;
import com.example.demo.repositories.AttributeMappingRepository;
import com.example.demo.repositories.CollectionRepository;
import com.example.demo.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CacheService implements ApplicationRunner {
    

    public static Map<Integer, Collection> collectionMap = new HashMap<>();
    public static Map<Integer, Property> propertyMap = new HashMap<>();
    public static Map<Integer, AttributeMapping> attributeMappingMap = new HashMap<>();

    @Autowired
    AttributeMappingRepository attributeMappingRepository;
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    PropertyRepository propertyRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        collectionRepository.findAll().forEach(collection -> collectionMap.put(collection.getId(), collection));
        propertyRepository.findAll().forEach(property -> propertyMap.put(property.getId(), property));
        attributeMappingRepository.findAll().forEach(attributeMapping -> attributeMappingMap.put(attributeMapping.getId(), attributeMapping));
    }
}
