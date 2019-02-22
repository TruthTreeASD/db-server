package edu.neu.cs6510.repositories;

import edu.neu.cs6510.model.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CollectionRepository extends CrudRepository<Collection, Integer> {

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_location_info.type_code = 0 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findStateAttrIds();

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_lookup.year = ?1 and gov_fin_location_info.type_code = 0 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findStateAttrIds(Integer year);

    @Query(value = "select attribute_mapping_id from gov_fin_lookup group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findAllAttrids();

    @Query(value = "select attribute_mapping_id from gov_fin_lookup where year = ?1 group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findAllAttrids(Integer year);

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_location_info.type_code = 1 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findCountyAttrIds();

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_lookup.year = ?1 and gov_fin_location_info.type_code = 1 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findCountyAttrIds(Integer year);

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_location_info.type_code = 2 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findCityAttrIds();

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_lookup.year = ?1 and gov_fin_location_info.type_code = 2 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findCityAttrIds(Integer year);

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
            "LEFT JOIN ( SELECT * FROM gov_fin_location_info WHERE type_code = 2) b " +
            "ON A.location_id = b.ID ", nativeQuery = true)
    public String findCityTimeRangeByAttrId(@Param("id") Integer id);

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_location_info.id = ?1 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findAttriIDsByLocId(@Param("id") Integer id);

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_lookup.year = ?2 and gov_fin_location_info.id = ?1 " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findAttriIDsByLocId(@Param("id") Integer id, @Param("year") Integer year);

    /***************************************************************************************************************************************/

    @Query(value = "select a.id from ( select attribute_mapping_id as id from gov_fin_lookup where" +
            "(-1 = ?2 is null or year = ?2) and location_id = ?1) a " +
            "group by a.id", nativeQuery = true)
    public List<Integer> findAvailableAttriById(@Param("id") Integer id, @Param("year") Integer year);

    @Query(value = "select a.id from( select attribute_mapping_id as id from gov_fin_lookup join gov_fin_location_info on " +
                            "gov_fin_lookup.location_id = gov_fin_location_info.id where " +
                       "(-1 = ?2 or year = ?2) and type_code = ?1) a " +
                       "group by a.id", nativeQuery = true)
    public List<Integer> findAvailableAttriByLevel(@Param("level") Integer level, @Param("year") Integer year);

    @Query(value = "select a.id from ( select attribute_mapping_id as id from gov_fin_lookup where" +
            " ( -1 = ?1 or year = ?1)) a group by a.id", nativeQuery = true)
    public List<Integer> findAllAvailableAttri(@Param("year") Integer year);

    @Query(value = "select start_year, end_year, attribute_mapping_id as attribute_id from time_range where type_code = ?1 and attribute_mapping_id in ?2", nativeQuery = true)
    List<Map<String, String>> attriTimeRange(int code, List<Integer> attributes);
}
