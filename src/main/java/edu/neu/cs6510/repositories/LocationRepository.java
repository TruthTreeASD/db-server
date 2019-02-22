package edu.neu.cs6510.repositories;

import edu.neu.cs6510.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {


  @Query(value = "select * from find_cities(?1)", nativeQuery = true)
  public List<Location> findCities(@Param("id") Integer id);

  @Query(value = "select * from find_counties(?1)", nativeQuery = true)
  public List<Location> findCounties(@Param("id") Integer id);

  @Query(value = "select * from gov_fin_location_info where type_code = 0", nativeQuery = true)
  public List<Location> findStates();

  @Query(value = "select * from gov_fin_location_info where type_code = 2", nativeQuery = true)
  public List<Location> findAllCities();

  @Query(value = "select * from gov_fin_location_info where type_code = 1", nativeQuery = true)
  public List<Location> findAllCounties();
  
  @Query(value = "select type_code from gov_fin_location_info where id = ?1", nativeQuery = true)
  public int findTypeCode(@Param("id") Integer id);
  
  @Query(value = "select parent_id from gov_fin_location_info where id = ?1", nativeQuery = true)
  public int findParentId(@Param("id") Integer id);

  @Query(value = "select * from gov_fin_location_info where type_code = ?1 order by name", nativeQuery = true)
  public List<Location> findAllLocationsAtGivenLevel(@Param("id") Integer id);

  @Query(value = "select * from gov_fin_location_info where type_code = ?2 and parent_id = ?1  order by name", nativeQuery = true)
  public List<Location> findAllLocationsWithGivenParent(@Param("parentCode") Integer parentCode, @Param("typeCode") Integer typeCode);  

}
