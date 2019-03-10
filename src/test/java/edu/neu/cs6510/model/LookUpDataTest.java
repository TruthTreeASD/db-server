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
  }

  @Test
  public void getLookUpPK() {
    lookUpData.setLookUpPK(lookUpPK);
    Assert.assertEquals(lookUpPK, lookUpData.getLookUpPK());
  }

  @Test
  public void getValue() {
    lookUpData.setValue(1.0);
    Assert.assertEquals(new Double(1.0), lookUpData.getValue());
  }
}