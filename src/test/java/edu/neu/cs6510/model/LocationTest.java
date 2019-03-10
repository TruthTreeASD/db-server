package edu.neu.cs6510.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
  Location location;

  @Before
  public void setUp() throws Exception {
    location = new Location();

    location.setLongitude(1.0);
    location.setLatitude(1.0);
    location.setFips_code_state(1);
    location.setFips_county(1);
    location.setFips_place(1);
    location.setFyenddate(1);
    location.setId(1);
    location.setName("Test");
    location.setParent_id("1");
    location.setType_code(1);
  }

  @Test
  public void getLongitude() {
    Assert.assertEquals(new Double(1.0), location.getLongitude());
  }

  @Test
  public void getLatitude() {
    Assert.assertEquals(new Double(1.0), location.getLatitude());
  }

  @Test
  public void getFips_code_state() {
    Assert.assertEquals(new Integer(1), location.getFips_code_state());
  }

  @Test
  public void getFips_county() {
    Assert.assertEquals(new Integer(1), location.getFips_county());
  }

  @Test
  public void getFips_place() {
    Assert.assertEquals(new Integer(1), location.getFips_place());
  }

  @Test
  public void getFyenddate() {
    Assert.assertEquals(new Integer(1), location.getFyenddate());
  }

  @Test
  public void getId() {
    Assert.assertEquals(1, location.getId());
  }

  @Test
  public void getName() {
    Assert.assertEquals("Test", location.getName());
  }

  @Test
  public void getParent_id() {
    Assert.assertEquals("1", location.getParent_id());
  }

  @Test
  public void getType_code() {
    Assert.assertEquals(1, location.getType_code());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Location{id=1, name='Test', parent_id='1', type_code=1, " +
        "fips_code_state=1, fips_county=1, fips_place=1, fyenddate=1}", location.toString());
  }
}