package edu.neu.cs6510.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.neu.cs6510.model.primeKey.LookUpPK;

import static org.junit.Assert.*;

public class LookUpDataTest {
  LookUpData lookUpData;
  LookUpPK lookUpPK;

  @Before
  public void setUp() throws Exception {
    lookUpData = new LookUpData();

    lookUpPK = new LookUpPK();
    lookUpPK.setAttribute_mapping_id(1);
    lookUpPK.setLocation_id(1);
    lookUpPK.setYear(2019);

    lookUpData.setLookUpPK(lookUpPK);
    lookUpData.setValue(1.0);
  }

  @Test
  public void getLookUpPK() {
    Assert.assertEquals(lookUpPK, lookUpData.getLookUpPK());
  }

  @Test
  public void getValue() {
    Assert.assertEquals(new Double(1.0), lookUpData.getValue());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("LookUpData{lookUpPK=LookUpPK{attribute_mapping_id=1, " +
        "location_id=1, year=2019}, value=1.0}", lookUpData.toString());
  }
}