package edu.neu.cs6510.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyDTOTest {

  PropertyDTO prop1;

  @Before
  public void setUp() throws Exception {
    prop1 = new PropertyDTO();
  }

  @Test
  public void getName() {
    prop1.setName("Prop1");
    Assert.assertEquals("Prop1", prop1.getName());
  }

  @Test
  public void getProperty_id() {
    prop1.setProperty_id(1);
    Assert.assertEquals(new Integer(1), prop1.getProperty_id());
  }
}