package edu.neu.cs6510.repositories;

import edu.neu.cs6510.model.Property;
import org.springframework.data.repository.CrudRepository;
/**
 * PropertyRepository is implemented as an interface that allows to do CRUD operations
 * for Property class which is mapped to 'gov_fin_property' table.
 */
public interface PropertyRepository extends CrudRepository<Property, Integer> {
}
