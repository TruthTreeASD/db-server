package edu.neu.cs6510.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.model.primeKey.LookUpPK;

import static org.junit.Assert.*;

public class LocationInfoDTOTest {
  LocationInfoDTO locationInfoDTO;
  List<LookUpData> lookUpDataList;
  LookUpData lookUpData;
  Location location;
  LookUpPK lookUpPK;

  @Before
  public void setUp() throws Exception {
    locationInfoDTO = new LocationInfoDTO(new Location());
    lookUpDataList = new ArrayList<>();

    lookUpPK = new LookUpPK();
    lookUpPK.setAttribute_mapping_id(1);
    lookUpPK.setLocation_id(1);
    lookUpPK.setYear(2019);

    lookUpData = new LookUpData();
    lookUpData.setLookUpPK(lookUpPK);

    lookUpDataList.add(lookUpData);

    location = new Location();
    location.setFips_code_state(1);
    location.setName("Test");
    location.setFips_county(1);
    location.setFips_place(1);
    location.setFyenddate(1);
    location.setId(1);
    location.setLatitude(1.0);
    location.setLongitude(1.0);
    location.setParent_id("1");
    location.setType_code(1);
  }

  @Test
  public void getLocation() {
    locationInfoDTO.setLocation(location);
    Assert.assertEquals(location, locationInfoDTO.getLocation());
  }

  @Test
  public void getAttributeValues() {
    locationInfoDTO.setAttributeValues(lookUpDataList);
    Assert.assertEquals(lookUpDataList, locationInfoDTO.getAttributeValues());
  }
}