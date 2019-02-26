package edu.neu.cs6510.repositories;

import edu.neu.cs6510.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Integer> {


  @Query(value = "select * from find_cities(?1)", nativeQuery = true)
  public List<Location> findCities(@Param("id") Integer id);

  @Query(value = "select * from find_counties(?1)", nativeQuery = true)
  public List<Location> findCounties(@Param("id") Integer id);

  @Query(value = "select * from trace_up(?1)", nativeQuery = true)
  public List<Location> findParents(@Param("id") Integer id);

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

  /**********************************************************************************************************************/

  @Query(value = "select count(1) from gov_fin_location_info where PARENT_ID = ?1 AND (?2 = -1 or Type_code = ?2)", nativeQuery = true)
  public int countLocationByuParentId(@Param("id") Integer id, @Param("type") Integer type);

  @Query(value = "select a.*  FROM (select * from gov_fin_location_info where PARENT_ID = ?1 AND (?2 = -1 or Type_code = ?2)) a ORDER by ?3 limit ?4 offset ?5", nativeQuery = true)
  public List<Location> findParentIdPage(@Param("id")  Integer id, @Param("type") Integer type,@Param("sort")  String sort,
                                         @Param("pageSize") Integer pageSize,@Param("offset") Integer offset);

  @Query(value = "select * from gov_fin_location_info where type_code = ?1 order by name", nativeQuery = true)
  public List<Location> findAllLocationsAtGivenLevel(@Param("id") Integer id);

  @Query(value = "select * from gov_fin_location_info where type_code = ?2 and parent_id = ?1  order by name", nativeQuery = true)
  public List<Location> findAllLocationsWithGivenParent(@Param("parentCode") Integer parentCode, @Param("typeCode") Integer typeCode);

  @Query(value = "select count(1) from find_locations(?1, ?2)", nativeQuery = true)
  public Integer countLocationsById(@Param("id") Integer id,@Param("myType")  Integer myType);

  @Query(value = "select a.* FROM (select * from find_locations(?1, ?2)) a ORDER by ?3 limit ?4 offset ?5", nativeQuery = true)
  List<Location> queryLocationsByIdPage(@Param("id")Integer id,@Param("myType") Integer myType, @Param("sort")String sort
          ,@Param("pageSize") Integer pageSize, @Param("offset") Integer offset);

  @Query(value = "select count(1) from gov_fin_location_info where  (?1 = -1 or Type_code = ?1))", nativeQuery = true)
  public int countByType(@Param("type")Integer type);

  @Query(value = "select a.* FROM (select * from gov_fin_location_info where  (?1 = -1 or Type_code = ?1))) a ORDER by ?2 limit ?3 offset ?4", nativeQuery = true)
  public List<Location> queryByTypePage(@Param("type")Integer type, @Param("sort")String sort,@Param("pageSize") Integer pageSize, @Param("offset")Integer offset);

}
