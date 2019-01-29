package com.example.demo.repositories;

import com.example.demo.model.Property;
import org.springframework.data.repository.CrudRepository;


public interface PropertyRepository extends CrudRepository<Property, Integer> {
}
