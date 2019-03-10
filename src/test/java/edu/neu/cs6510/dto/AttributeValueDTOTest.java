package edu.neu.cs6510.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttributeValueDTOTest {
  AttributeValueDTO attributeValueDTO;

  @Before
  public void setUp() throws Exception {
    attributeValueDTO = new AttributeValueDTO();

  }

  @Test
  public void setAttribute_id() {
    attributeValueDTO.setAttribute_id(1);
    Assert.assertEquals(1, attributeValueDTO.getAttribute_id());
  }

  @Test
  public void getAttribute_id() {
    attributeValueDTO.setAttribute_id(2);
    Assert.assertEquals(2, attributeValueDTO.getAttribute_id());
  }

  @Test
  public void setValue() {
    attributeValueDTO.setValue(4);
    Assert.assertEquals(4, attributeValueDTO.getValue());
  }

  @Test
  public void getValue() {
    attributeValueDTO.setValue(6);
    Assert.assertEquals(6, attributeValueDTO.getValue());
  }
}