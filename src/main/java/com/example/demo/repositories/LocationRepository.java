package com.example.demo.repositories;

import com.example.demo.model.Location;
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

  @Query(value = "select * from gov_fin_location_info where type_code not in (0 ,1)", nativeQuery = true)
  public List<Location> findAllCities();

  @Query(value = "select * from gov_fin_location_info where type_code = 1", nativeQuery = true)
  public List<Location> findAllCounties();


}
