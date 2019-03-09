package edu.neu.cs6510.controller;

import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.repositories.LocationRepository;
import edu.neu.cs6510.services.LocationService;
import edu.neu.cs6510.util.http.ResponseMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LocationControllerTest {

    @InjectMocks
    LocationService locationService;

    @Mock
    LocationRepository locationRepository;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllLocationBasedData(){
        int idx = 5;
        Location city1 = new Location();

        city1.setFips_code_state(1);
        city1.setFips_county(0);
        city1.setFips_place(1);
        city1.setId(5);
        city1.setLatitude(10.00);
        city1.setLongitude(10.00);
        city1.setName("testcity1");
        city1.setType_code(1);

        Location city2 = new Location();
        city2.setFips_code_state(1);
        city2.setFips_county(100);
        city2.setFips_place(20);
        city2.setId(10);
        city2.setLatitude(20.00);
        city2.setLongitude(20.00);
        city2.setName("testcity2");
        city2.setType_code(1);

        List<Location> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);

        Mockito.when(locationRepository.findAll()).thenReturn(cities);
        ResponseMessage res = locationService.findAllLocationBasedData();
        assertEquals(res.getData(),cities);

    }

    @Test
    public void findCityById(){

        int idx = 5;
        Location city = new Location();
        city.setFips_code_state(1);
        city.setFips_county(0);
        city.setFips_place(1);
        city.setId(5);
        city.setLatitude(10.00);
        city.setLongitude(10.00);
        city.setName("testcity");
        city.setType_code(1);

        List<Location> cities = new ArrayList<>();
        cities.add(city);
        Mockito.when(locationRepository.findCities(idx)).thenReturn(cities);
        Mockito.when(locationRepository.findAllCities()).thenReturn(cities);
        ResponseMessage res = locationService.findCities(idx);
        assertEquals(res.getData(),cities);
    }
}
