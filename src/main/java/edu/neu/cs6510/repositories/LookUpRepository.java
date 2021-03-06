package edu.neu.cs6510.repositories;

import edu.neu.cs6510.model.LookUpData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
/**
 * LookUpRepository is implemented as an interface that allows to do CRUD operations
 * for LookUpData class which is mapped to 'gov_fin_lookup' table.
 * Some of the CRUD operations are:
 * 1. To find lookup data for a specific attribute id
 * 2. To find lookup data for a specific attribute id and given location
 * 3. To find lookup data for a specific attribute id and given location and a time range
 */
public interface LookUpRepository extends CrudRepository<LookUpData, Integer> {
    @Query(value = "select * from gov_fin_lookup where attribute_mapping_id = ?1", nativeQuery = true)
    List<LookUpData> findLookUpDataForAttributeId(@Param("id") Integer id);

    @Query(value = "select * from gov_fin_lookup where attribute_mapping_id in ?1 and location_id in ?2 order by year desc", nativeQuery = true)
    List<LookUpData> findLookUpDataForAttributeIdAndLoc(@Param("id") List<Integer> attrId, @Param("id") List<Integer> locId);

    @Query(value = "select * from gov_fin_lookup where attribute_mapping_id in ?1 and location_id in ?2 and year >= ?3 and year <= ?4 order by year desc", nativeQuery = true)
    List<LookUpData> findByAttributeIdAndLocAndTimeRange(@Param("id") List<Integer> attrId, @Param("id") List<Integer> locId, @Param("from") Integer from, @Param("to") Integer to);

    @Query(value = "select * from gov_fin_lookup where year in ?3 and attribute_mapping_id in ?1 and location_id in ?2 order by year desc", nativeQuery = true)
    List<LookUpData> findByAttributeIdAndLocAndTimeList(@Param("id") List<Integer> attrId, @Param("id") List<Integer> locId,@Param("years") List<Integer> years);
    
    @Query(value = "select * from gov_fin_lookup where year = ?3 and attribute_mapping_id in ?1 and location_id in ?2 order by year desc", nativeQuery = true)
    List<LookUpData> findByAttributeIdAndLocAndTime(@Param("id") List<Integer> attrId, @Param("id") List<Integer> locId,@Param("year") int year);

    /*******************************************************************************************************************************************************************************/

    @Query(value = "select  a.attribute_id, a.location_id, a.year, a.value from (select attribute_mapping_id as attribute_id, location_id, year, value  from gov_fin_lookup " +
            "where attribute_mapping_id in ?1 and (-1 = ?7 or year in ?2) " +
            "and (COALESCE( null, ?3) is null or location_id in ?3) and value between ?4 and ?5) as a "  +
            "join gov_fin_location_info on a.location_id = gov_fin_location_info.id ORDER by ?6 limit ?8 offset ?9", nativeQuery = true)
    List<Map<String,String>> queryLookUpData(@Param("attributeId")List<Integer> attributeId,@Param("year") List<Integer> year,
                                             @Param("locationId")List<Integer> locationId,
                                             @Param("from") Integer from,@Param("to") Integer to, @Param("sort")String sort,
                                             @Param("yearSize")Integer yearSize,@Param("pageSize") Integer pageSize,@Param("offset") Integer offset);

    @Query(value = "select count(1) from (select location_id from gov_fin_lookup " +
            "where attribute_mapping_id in ?1 and (-1 = ?6 or year in ?2) " +
            "and (COALESCE( null, ?3) is null or location_id in ?3) and value between ?4 and ?5) as a "  +
            "join gov_fin_location_info on a.location_id = gov_fin_location_info.id", nativeQuery = true)
    Long queryLookUpDataTotal(@Param("attributeId")List<Integer> attributeId, @Param("year") List<Integer> year,
                                 @Param("locationId") List<Integer> locationId,
                                 @Param("from")Integer from, @Param("to")Integer to, @Param("yearSize") Integer yearSize);

    @Query(value = "select   a.attribute_id, a.location_id, a.year, a.value from (select attribute_mapping_id as attribute_id, location_id, year, value from gov_fin_lookup " +
            "where attribute_mapping_id in ?1 and (-1 = ?7 or year in ?2) " +
            "and value between ?4 and ?5 and (COALESCE( null, ?3) is null or location_id in (select id from gov_fin_location_info where type_code = ?3))) as a " +
            "join gov_fin_location_info on a.location_id = gov_fin_location_info.id ORDER by ?6 limit ?8 offset ?9", nativeQuery = true)
    List<Map<String,String>> queryLookUpData(@Param("attributeId")List<Integer> attributeId,@Param("year") List<Integer> year,@Param("typeCode") Integer typeCode,
                                             @Param("from")Integer from,@Param("to") Integer to,@Param("sort") String sort,
                                             @Param("yearSize")Integer yearSize, @Param("pageSize")Integer pageSize, @Param("offset")Integer offset);

    @Query(value = "select count(1) from (select location_id from gov_fin_lookup " +
            "where attribute_mapping_id in ?1 and (-1 = ?6 or year in ?2) " +
            "and value between ?4 and ?5 and (COALESCE( null, ?3) is null or location_id in (select id from gov_fin_location_info where type_code = ?3))) as a " +
            "join gov_fin_location_info on a.location_id = gov_fin_location_info.id", nativeQuery = true)
    Long queryLookUpDataTotal(@Param("attributeId") List<Integer> attributeId, @Param("year") List<Integer> year,
                                 @Param("typeCode") Integer typeCode, @Param("from") Integer from,
                                 @Param("to")Integer to,@Param("yearSize") Integer yearSize);

    @Query(value = "select  a.attribute_id, a.location_id, a.year, a.value from (select attribute_mapping_id as attribute_id, location_id, year, value from gov_fin_lookup " +
            "where attribute_mapping_id in ?1 and (-1 = ?7 or year in ?2) " +
            "and value between ?4 and ?5 and (COALESCE( null, ?3) is null or location_id in (select id from gov_fin_location_info where parent_id = ?3))) as a " +
            "join gov_fin_location_info on a.location_id = gov_fin_location_info.id ORDER by ?6 limit ?8 offset ?9", nativeQuery = true)
    List<Map<String,String>> queryLookUpDataParentId(@Param("attributeId") List<Integer> attributeId, @Param("year") List<Integer> year,
                                                     @Param("parentId")Integer parentId,
                                                     @Param("from")Integer from, @Param("to") Integer to,
                                                     @Param("sort")String sort, @Param("yearSize")Integer yearSize,
                                                     @Param("pageSize")Integer pageSize, @Param("offset")Integer offset);

    @Query(value = "select count(1) from (select location_id from gov_fin_lookup " +
            "where attribute_mapping_id in ?1 and (-1 = ?6 or year in ?2) " +
            "and value between ?4 and ?5 and (COALESCE( null, ?3) is null or location_id in (select id from gov_fin_location_info where parent_id = ?3))) as a " +
            "join gov_fin_location_info on a.location_id = gov_fin_location_info.id", nativeQuery = true)
    Long queryLookUpDataParentIdTotal(@Param("attributeId") List<Integer> attributeId, @Param("year") List<Integer> year,
                                         @Param("parentId") Integer parentId,@Param("from")  Integer from,@Param("to")  Integer to,
                                         @Param("yearSize") Integer yearSize);

}