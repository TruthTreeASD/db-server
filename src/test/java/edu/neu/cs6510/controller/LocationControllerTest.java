package edu.neu.cs6510.controller;

import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.repositories.LocationRepository;
import edu.neu.cs6510.services.LocationService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
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
    public void findAllLocationBasedDataTest(){
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
    public void findCitiesTest(){

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
        ResponseMessage<List<Location>> resMessage = new ResponseMessage<>();
        resMessage.setData(cities);
        Mockito.when(locationRepository.findCities(idx)).thenReturn(cities);
        Mockito.when(locationRepository.findAllCities()).thenReturn(cities);
        ResponseMessage res = locationService.findCities(idx);
        assertEquals(res.getData(),cities);
    }

    @Test
    public void findStatesTest(){

        int idx = 5;
        Location state = new Location();
        state.setFips_code_state(0);
        state.setFips_county(0);
        state.setFips_place(1);
        state.setId(5);
        state.setLatitude(0.00);
        state.setLongitude(0.00);
        state.setName("test-state");
        state.setType_code(0);

        List<Location> states = new ArrayList<>();
        states.add(state);
        ResponseMessage<List<Location>> resMessage = new ResponseMessage<List<Location>>();
        resMessage.setData(states);
        Mockito.when(locationRepository.findStates()).thenReturn(states);
        ResponseMessage res = locationService.findStates();
        assertEquals(res.getData(),states);
    }

    @Test
    public void findCountiesTest(){

        int idx = 5;
        Location county = new Location();
        county.setFips_code_state(1);
        county.setFips_county(0);
        county.setFips_place(1);
        county.setId(5);
        county.setLatitude(10.00);
        county.setLongitude(10.00);
        county.setName("testcounties");
        county.setType_code(5);

        List<Location> counties = new ArrayList<>();
        counties.add(county);
        Mockito.when(locationRepository.findCounties(idx)).thenReturn(counties);
        Mockito.when(locationRepository.findCounties(idx)).thenReturn(counties);
        ResponseMessage res = locationService.findCounties(idx);
        assertEquals(res.getData(),counties);
    }

    @Test
    public void findParentsTest(){

        int idx = 5;
        Location city = new Location();
        city.setFips_code_state(1);
        city.setFips_county(0);
        city.setFips_place(1);
        city.setId(5);
        city.setLatitude(10.00);
        city.setLongitude(10.00);
        city.setName("test-city");
        city.setType_code(1);
        city.setParent_id("10");

        Location state = new Location();
        state.setFips_code_state(0);
        state.setFips_county(0);
        state.setFips_place(1);
        state.setId(10);
        state.setLatitude(0.00);
        state.setLongitude(0.00);
        state.setName("test-state");
        state.setType_code(0);

        Mockito.when(locationRepository.findParentId(idx)).thenReturn(10);
        Mockito.when(locationRepository.findParentId(idx)).thenReturn(10);
        ResponseMessage res = locationService.findParents(idx);
        assertEquals(res.getData(), new ArrayList<>());
    }

    @Test
    public void findByIdTest(){

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

        Mockito.when(locationRepository.findById(idx)).thenReturn(java.util.Optional.ofNullable(city));
        ResponseMessage res = locationService.queryById(idx);
        assertEquals(res.getData(),city);
    }


}






