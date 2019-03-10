package edu.neu.cs6510.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollectionTest {
  Collection collection;

  @Before
  public void setUp() throws Exception {
    collection = new Collection();

    collection.setId(1);
    collection.setName("Test");
  }

  @Test
  public void getId() {
    Assert.assertEquals(1, collection.getId());
  }

  @Test
  public void getName() {
    Assert.assertEquals("Test", collection.getName());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Collection{id=1, name='Test'}", collection.toString());
  }
}