package edu.neu.cs6510.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollectionInfoDTOTest {
  CollectionInfoDTO collectionInfoDTO;

  @Before
  public void setUp() throws Exception {
    collectionInfoDTO = new CollectionInfoDTO();
  }

  @Test
  public void getAttribute_name() {
    collectionInfoDTO.setAttribute_name("Test");
    Assert.assertEquals("Test", collectionInfoDTO.getAttribute_name());
  }

  @Test
  public void getAttribute_id() {
    collectionInfoDTO.setAttribute_id("1");
    Assert.assertEquals("1", collectionInfoDTO.getAttribute_id());
  }

  @Test
  public void getProperty_id() {
    collectionInfoDTO.setProperty_id(1);
    Assert.assertEquals(new Integer(1), collectionInfoDTO.getProperty_id());
  }

  @Test
  public void getProperty_name() {
    collectionInfoDTO.setProperty_name("Test");
    Assert.assertEquals("Test", collectionInfoDTO.getProperty_name());
  }
}