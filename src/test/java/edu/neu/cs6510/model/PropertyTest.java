package edu.neu.cs6510.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyTest {
  Property property;

  @Before
  public void setUp() throws Exception {
    property = new Property();

    property.setId(1);
    property.setName("Test");
  }

  @Test
  public void getId() {
    Assert.assertEquals(1, property.getId());
  }

  @Test
  public void getName() {
    Assert.assertEquals("Test", property.getName());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Property{id=1, name='Test'}", property.toString());
  }
}