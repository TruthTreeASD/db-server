package edu.neu.cs6510.controller;

import edu.neu.cs6510.dto.LocationInfoDTO;
import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.model.primeKey.LookUpPK;
import edu.neu.cs6510.repositories.LocationRepository;
import edu.neu.cs6510.repositories.LookUpRepository;
import edu.neu.cs6510.services.LookUpService;
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
import static org.junit.Assert.assertTrue;

public class LookUpControllerTests {

    @InjectMocks
    LookUpService lookUpService;

    @Mock
    LookUpRepository lookUpRepository;

    @Mock
    LocationRepository locationRepository;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllRecords(){
        LookUpData lookUpData1 = new LookUpData();
        LookUpPK pk1 = new LookUpPK();
        pk1.setAttribute_mapping_id(1);
        pk1.setLocation_id(1);
        pk1.setYear(2017);
        lookUpData1.setLookUpPK(pk1);
        lookUpData1.setValue(10.00);

        LookUpData lookUpData2 = new LookUpData();
        LookUpPK pk2 = new LookUpPK();
        pk2.setAttribute_mapping_id(2);
        pk2.setLocation_id(2);
        pk2.setYear(2007);
        lookUpData2.setLookUpPK(pk2);
        lookUpData2.setValue(20.00);

        List<LookUpData> lookUpDataList = new ArrayList<>();
        lookUpDataList.add(lookUpData1);
        lookUpDataList.add(lookUpData2);

        Mockito.when(lookUpRepository.findAll()).thenReturn(lookUpDataList);
        ResponseMessage res = lookUpService.findAllRecords();
        assertEquals(res.getData(), lookUpDataList);

    }

    @Test
    public void findRecordsAtSameLevelTest(){
        List<Integer> attributeIds = new ArrayList<>();
        attributeIds.add(1);
        attributeIds.add(2);
        attributeIds.add(3);

        int locationId = 1;
        int year = 2017;

        Location state1 = new Location();
        state1.setFips_code_state(0);
        state1.setFips_county(0);
        state1.setFips_place(1);
        state1.setId(5);
        state1.setLatitude(10.00);
        state1.setLongitude(10.00);
        state1.setName("test-state-1");
        state1.setType_code(0);
        Location state2 = new Location();
        state2.setFips_code_state(0);
        state2.setFips_county(0);
        state2.setFips_place(1);
        state2.setId(10);
        state2.setLatitude(20.00);
        state2.setLongitude(20.00);
        state2.setName("test-state-2");
        state2.setType_code(0);
        List<Location> states = new ArrayList<>();
        states.add(state1);
        states.add(state2);
        List<Integer> locationsIds = new ArrayList<>();
        locationsIds.add(5);
        locationsIds.add(10);

        LookUpData lookUpData1 = new LookUpData();
        LookUpPK pk1 = new LookUpPK();
        pk1.setAttribute_mapping_id(1);
        pk1.setLocation_id(1);
        pk1.setYear(2017);
        lookUpData1.setLookUpPK(pk1);
        lookUpData1.setValue(10.00);

        LookUpData lookUpData2 = new LookUpData();
        LookUpPK pk2 = new LookUpPK();
        pk2.setAttribute_mapping_id(2);
        pk2.setLocation_id(1);
        pk2.setYear(2017);
        lookUpData2.setLookUpPK(pk2);
        lookUpData2.setValue(20.00);

        List<LookUpData> lookUpDataList = new ArrayList<>();
        lookUpDataList.add(lookUpData1);
        lookUpDataList.add(lookUpData2);

        Mockito.when(locationRepository.findTypeCode(locationId)).thenReturn(0);
        Mockito.when(locationRepository.findAllLocationsAtGivenLevel(0)).thenReturn(states);
        Mockito.when(lookUpRepository.findByAttributeIdAndLocAndTime(attributeIds, locationsIds, year)).thenReturn(lookUpDataList);

        LocationInfoDTO l1 = new LocationInfoDTO(state1);
        LocationInfoDTO l2 = new LocationInfoDTO(state2);

        List<LocationInfoDTO> locationInfoDTOS = new ArrayList<>();
        locationInfoDTOS.add(l1);
        locationInfoDTOS.add(l2);

        ResponseMessage res = lookUpService.findRecordsAtSameLevel(locationId, year, attributeIds);
    }

}
