package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.LookUpData;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LookUpRepository extends CrudRepository<LookUpData, Integer> {
    @Query(value = "select * from gov_fin_lookup where attribute_mapping_id = ?1", nativeQuery = true)
    List<LookUpData> findLookUpDataForAttributeId(@Param("id") Integer id);
}