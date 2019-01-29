package com.example.demo.repositories;

import com.example.demo.model.Collection;
import org.springframework.data.repository.CrudRepository;

public interface CollectionRepository extends CrudRepository<Collection, Integer> {
}
