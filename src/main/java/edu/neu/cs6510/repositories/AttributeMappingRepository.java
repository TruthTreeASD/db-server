package edu.neu.cs6510.repositories;

import edu.neu.cs6510.model.AttributeMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * AttributeMappingRepository is implemented as an interface that allows to do CRUD operations
 * for AttributeMapping class which is mapped to 'gov_fin_attribute_mapping' table.
 * Some CRUD operations are:
 * 1. find attribute mapping id for states only
 * 2. find attribute mapping id for counties only
 * 3. find attribute mapping id for cities only
 * 4. find attribute mapping id for a specific collection
 * 5. find attribute mappings for a specific collection and property
 */
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

    @Query(value = "select id,name,collection_id,property_id " +
            "from gov_fin_attribute_mapping where collection_id = ?1", nativeQuery = true)
    public List<AttributeMapping> findByCollectionId(@Param("id") Integer id);

    @Query(value = "select id,name,collection_id,property_id " +
            "from gov_fin_attribute_mapping where collection_id = ?1 and property_id in (?2)", nativeQuery = true)
    public List<AttributeMapping> findByCollectionIdAndProperties(@Param("id") Integer id, @Param("pids") List<Integer> pids);

    @Query(value = "select id,name,collection_id,property_id " +
            "from gov_fin_attribute_mapping where collection_id = ?1 and property_id in (?2)", nativeQuery = true)
    public List<AttributeMapping> findByAttriAndLocation(@Param("id") Integer id, @Param("pids") List<Integer> pids);
}

