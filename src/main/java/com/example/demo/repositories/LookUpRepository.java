package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.LookUpData;

public interface LookUpRepository extends CrudRepository<LookUpData, Integer> {

}