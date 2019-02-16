package edu.neu.cs6510.repositories;

import edu.neu.cs6510.model.Collection;
import org.springframework.data.domain.Sort;
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
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_location_info.type_code not in (0,1) " +
            "group by attribute_mapping_id ", nativeQuery = true)
    public List<Integer> findCityAttrIds();

    @Query(value = "select attribute_mapping_id from gov_fin_lookup join gov_fin_location_info on  " +
            "gov_fin_lookup.location_id = gov_fin_location_info.id where gov_fin_lookup.year = ?1 and gov_fin_location_info.type_code not in (0,1) " +
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
            "LEFT JOIN ( SELECT * FROM gov_fin_location_info WHERE type_code not in (0, 1)) b " +
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



    @Query(value = "select a.id from ( select attribute_mapping_id as id from gov_fin_lookup where" +
            "(COALESCE( null, ?1) is null or year = ?2) and location_id = ?1 ORDER BY ?3) a " +
            "group by a.id", nativeQuery = true)
    public List<Integer> findAvailableAttriById(@Param("id") Integer id, @Param("year") Integer year, @Param("sort") String sort);

    @Query(value = "select a.id from( select attribute_mapping_id as id from gov_fin_lookup join gov_fin_location_info on " +
                            "gov_fin_lookup.location_id = gov_fin_location_info.id where " +
                       "(COALESCE( null, ?2) is null or year = ?2) and type_code in ?1 ORDER BY ?3) a " +
                       "group by a.id", nativeQuery = true)
    public List<Integer> findAvailableAttriByLevel(@Param("level") List<Integer> level, @Param("year") Integer year, @Param("sort") String sort);

    @Query(value = "select a.id from ( select attribute_mapping_id as id from gov_fin_lookup where" +
            " (COALESCE( null, ?1) is null or year = ?1) ORDER BY ?2) a group by a.id", nativeQuery = true)
    public List<Integer> findAllAvailableAttri(@Param("year") Integer year,@Param("sort") String sort);
}
