package edu.neu.cs6510.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttributeMappingTest {
  AttributeMapping attributeMapping;

  @Before
  public void setUp() throws Exception {
    attributeMapping = new AttributeMapping();

    attributeMapping.setId(1);
    attributeMapping.setName("Test");
    attributeMapping.setCollection_id(1);
    attributeMapping.setProperty_id(1);
  }

  @Test
  public void getId() {

    Assert.assertEquals(1, attributeMapping.getId());
  }

  @Test
  public void getName() {

    Assert.assertEquals("Test", attributeMapping.getName());
  }

  @Test
  public void getCollection_id() {

    Assert.assertEquals(1, attributeMapping.getCollection_id());
  }

  @Test
  public void getProperty_id() {

    Assert.assertEquals(1, attributeMapping.getProperty_id());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("AttributeMapping{id=1, name='Test', collection_id=1, property_id=1}", attributeMapping.toString());
  }
}