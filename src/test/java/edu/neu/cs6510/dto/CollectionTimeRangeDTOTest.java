package edu.neu.cs6510.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionTimeRangeDTOTest {
  CollectionTimeRangeDTO collectionTimeRangeDTO;
  List<AttrTimeRangeDTO> attrTimeRangeDTOs;
  AttrTimeRangeDTO attr1;
  AttrTimeRangeDTO attr2;

  @Before
  public void setUp() throws Exception {
    collectionTimeRangeDTO = new CollectionTimeRangeDTO();
    attrTimeRangeDTOs = new ArrayList<>();

    attr1 = new AttrTimeRangeDTO();
    attr1.setStartYear("2019");
    attr1.setEndYear("2019");
    attr1.setAttribute_id(1);
    attr1.setAttribute_name("Attr1");
    attr1.setProperty_id(1);
    attr1.setProperty_name("Test");

    attr2 = new AttrTimeRangeDTO();
    attr2.setStartYear("2018");
    attr2.setEndYear("2018");
    attr2.setAttribute_id(2);
    attr2.setAttribute_name("Attr2");
    attr2.setProperty_id(2);
    attr2.setProperty_name("Test 2");

    attrTimeRangeDTOs.add(attr1);
    attrTimeRangeDTOs.add(attr2);
  }

  @Test
  public void getName() {
    collectionTimeRangeDTO.setName("Test");
    Assert.assertEquals("Test", collectionTimeRangeDTO.getName());
  }

  @Test
  public void getCollection_id() {
    collectionTimeRangeDTO.setCollection_id(1);
    Assert.assertEquals(new Integer(1), collectionTimeRangeDTO.getCollection_id());
  }

  @Test
  public void getAttributes() {
    collectionTimeRangeDTO.setAttributes(attrTimeRangeDTOs);
    Assert.assertEquals(attrTimeRangeDTOs, collectionTimeRangeDTO.getAttributes());
  }
}