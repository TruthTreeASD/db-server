package com.example.demo;

import com.example.demo.model.Collection;
import com.example.demo.model.Location;
import com.example.demo.repositories.CollectionRepository;
import com.example.demo.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Test implements CommandLineRunner {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CollectionRepository collectionRepository;
    @Override
    public void run(String... args) throws Exception {

        // to display all locations
//        List<Location> locationList = (List<Location>) locationRepository.findAll();
//        for (int i =0; i< locationList.size(); i++){
//            System.out.println(locationList.get(i).getId() + " " + locationList.get(i).getName());
//        }

        // to display all collections
        List<Collection> collectionList = (List<Collection>) collectionRepository.findAll();
        for (Collection collection : collectionList){
            System.out.println("Collection name :" + collection.getId() + "->" + collection.getName());
        }

    }
}
