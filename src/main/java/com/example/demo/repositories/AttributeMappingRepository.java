package com.example.demo.repositories;

import com.example.demo.model.AttributeMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttributeMappingRepository extends CrudRepository<AttributeMapping, Integer> {

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_location_info.type_code = 0 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findStateAttrIds();


    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_location_info.type_code = 1 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findCountyAttrIds();

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_location_info.type_code not in (0,1) " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findCityAttrIds();

    @Query(value = "select location_id, attribute_mapping_id " +
            "from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_location_info.type_code = 0 " +
            "group by attribute_mapping_id, location_id", nativeQuery = true)
    public List<Object> findStateAttrIdsWithLocationIds();



}

