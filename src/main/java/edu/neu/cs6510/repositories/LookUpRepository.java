package edu.neu.cs6510.repositories;

import edu.neu.cs6510.model.LookUpData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LookUpRepository extends CrudRepository<LookUpData, Integer> {
    @Query(value = "select * from gov_fin_lookup where attribute_mapping_id = ?1", nativeQuery = true)
    List<LookUpData> findLookUpDataForAttributeId(@Param("id") Integer id);

    @Query(value = "select * from gov_fin_lookup where attribute_mapping_id in ?1 and location_id in ?2 order by year desc", nativeQuery = true)
    List<LookUpData> findLookUpDataForAttributeIdAndLoc(@Param("id") List<Integer> attrId, @Param("id") List<Integer> locId);

    @Query(value = "select * from gov_fin_lookup where attribute_mapping_id in ?1 and location_id in ?2 and year >= ?3 and year <= ?4 order by year desc", nativeQuery = true)
    List<LookUpData> findByAttributeIdAndLocAndTimeRange(@Param("id") List<Integer> attrId, @Param("id") List<Integer> locId, Integer from, Integer to);

    @Query(value = "select * from gov_fin_lookup where year in ?3 and attribute_mapping_id in ?1 and location_id in ?2 order by year desc", nativeQuery = true)
    List<LookUpData> findByAttributeIdAndLocAndTimeList(@Param("id") List<Integer> attrId, @Param("id") List<Integer> locId, List<Integer> years);
    
    @Query(value = "select * from gov_fin_lookup where year = ?3 and attribute_mapping_id in ?1 and location_id in ?2 order by year desc", nativeQuery = true)
    List<LookUpData> findByAttributeIdAndLocAndTime(@Param("id") List<Integer> attrId, @Param("id") List<Integer> locId, int year);

    @Query(value = "select * from gov_fin_lookup " +
            "where attribute_mapping_id in ?1 and (COALESCE( null, ?2) is null or year in ?2) " +
            "and (COALESCE( null, ?3) is null or location_id in ?3) and value between ?4 and ?5 " +
            "order by ?6 ", nativeQuery = true)
    List<LookUpData> queryLookUpData(List<Integer> attributeId, List<Integer> year, List<Integer> locationId, Integer from, Integer to, String sort);

    @Query(value = "select * from gov_fin_lookup " +
            "where attribute_mapping_id in ?1 and (COALESCE( null, ?2) is null or year in ?2) " +
            " and value between ?4 and ?5 and (COALESCE( null, ?3) is null or location_id in (select id from gov_fin_location_info where type_code = ?3)) " +
            "order by ?6 ", nativeQuery = true)
    List<LookUpData> queryLookUpData(List<Integer> attributeId, List<Integer> year, Integer typeCode, Integer from, Integer to, String sort);

}