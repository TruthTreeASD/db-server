package edu.neu.cs6510.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttributeInfoDTOTest {
  AttributeInfoDTO attributeInfoDTO;

  @Before
  public void setUp() throws Exception {
    attributeInfoDTO = new AttributeInfoDTO();
    attributeInfoDTO.setName("Test");
    attributeInfoDTO.setAttribute_id(1);
  }

  @Test
  public void getName() {
    Assert.assertEquals("Test", attributeInfoDTO.getName());
  }

  @Test
  public void setName() {
    attributeInfoDTO.setName("Test Again");
    Assert.assertEquals("Test Again", attributeInfoDTO.getName());
  }

  @Test
  public void getAttribute_id() {
    Assert.assertEquals(new Integer(1), attributeInfoDTO.getAttribute_id());
  }

  @Test
  public void setAttribute_id() {
    attributeInfoDTO.setAttribute_id(2);
    Assert.assertEquals(new Integer(2), attributeInfoDTO.getAttribute_id());
  }
}