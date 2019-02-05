package com.example.demo.repositories;

import com.example.demo.model.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionRepository extends CrudRepository<Collection, Integer> {

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

    @Query(value = "SELECT max(a.year),min(a.year) FROM " +
            "( SELECT * FROM gov_fin_lookup WHERE attribute_mapping_id = ?1 ) A " +
            "LEFT JOIN ( SELECT * FROM gov_fin_location_info WHERE type_code = 0 ) b " +
            "ON A.location_id = b.ID ", nativeQuery = true)
    public String findStateTimeRangeByAttrId(@Param("id") Integer id);

    @Query(value = "SELECT max(a.year),min(a.year) FROM " +
            "( SELECT * FROM gov_fin_lookup WHERE attribute_mapping_id = ?1 ) A " +
            "LEFT JOIN ( SELECT * FROM gov_fin_location_info WHERE type_code = 1 ) b " +
            "ON A.location_id = b.ID ", nativeQuery = true)
    public String findCountyTimeRangeByAttrId(@Param("id") Integer id);

    @Query(value = "SELECT max(a.year),min(a.year) FROM " +
            "( SELECT * FROM gov_fin_lookup WHERE attribute_mapping_id = ?1 ) A " +
            "LEFT JOIN ( SELECT * FROM gov_fin_location_info WHERE type_code not in (0, 1)) b " +
            "ON A.location_id = b.ID ", nativeQuery = true)
    public String findCityTimeRangeByAttrId(@Param("id") Integer id);
}
