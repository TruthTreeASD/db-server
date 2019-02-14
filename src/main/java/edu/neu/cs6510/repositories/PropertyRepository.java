package edu.neu.cs6510.repositories;

import edu.neu.cs6510.model.Property;
import org.springframework.data.repository.CrudRepository;


public interface PropertyRepository extends CrudRepository<Property, Integer> {
}
