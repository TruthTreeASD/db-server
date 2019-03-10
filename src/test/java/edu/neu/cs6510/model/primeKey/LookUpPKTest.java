package edu.neu.cs6510.model.primeKey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LookUpPKTest {
  private LookUpPK lookUpPK;
  private LookUpPK lookUpPK2;
  private LookUpPK lookUpPK3;
  private LookUpPK lookUpPK4;

  private LookUpPK sameRefLookUpPK;
  private LookUpPK sameStateAsLookUpPK;
  private LookUpPK yetAnotherLookUpPK;
  private LookUpPK diffLookUpPK;
  private LookUpPK nullLookUpPK = null;

  @Before
  public void setUp() throws Exception {
    lookUpPK = new LookUpPK();
    lookUpPK.setAttribute_mapping_id(1);
    lookUpPK.setLocation_id(1);
    lookUpPK.setYear(2019);

    lookUpPK2 = new LookUpPK();
    lookUpPK2.setAttribute_mapping_id(1);
    lookUpPK2.setLocation_id(1);
    lookUpPK2.setYear(2019);

    lookUpPK3 = new LookUpPK();
    lookUpPK3.setAttribute_mapping_id(1);
    lookUpPK3.setLocation_id(1);
    lookUpPK3.setYear(2019);

    lookUpPK4 = new LookUpPK();
    lookUpPK4.setAttribute_mapping_id(2);
    lookUpPK4.setLocation_id(2);
    lookUpPK4.setYear(2018);

    sameRefLookUpPK = lookUpPK;
    sameStateAsLookUpPK = lookUpPK2;
    yetAnotherLookUpPK = lookUpPK3;
    diffLookUpPK = lookUpPK4;
  }

  @Test
  public void getAttribute_mapping_id() {
    Assert.assertEquals(new Integer(1), lookUpPK.getAttribute_mapping_id());
  }

  @Test
  public void getLocation_id() {
    Assert.assertEquals(new Integer(1), lookUpPK.getLocation_id());
  }

  @Test
  public void getYear() {
    Assert.assertEquals(new Integer(2019), lookUpPK.getYear());
  }

  @Test
  public void equals() {
    Assert.assertTrue(lookUpPK.equals(lookUpPK)); // reflexivity
    Assert.assertTrue(lookUpPK.equals(sameRefLookUpPK)); // trivial condition both reference the same object
    Assert.assertTrue(lookUpPK.equals(sameStateAsLookUpPK)); // both objects should have the same state
    Assert.assertTrue(sameStateAsLookUpPK.equals(lookUpPK)); //symmetry
    Assert.assertEquals(lookUpPK.equals(sameStateAsLookUpPK) && sameStateAsLookUpPK.equals(yetAnotherLookUpPK), yetAnotherLookUpPK.equals(lookUpPK)); //transitivity
    Assert.assertFalse(lookUpPK.equals(diffLookUpPK)); //objects have different state
    Assert.assertFalse(lookUpPK.equals(nullLookUpPK)); //is NOT null
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(lookUpPK.equals(sameRefLookUpPK), lookUpPK.hashCode() == sameRefLookUpPK.hashCode()); //same objects have the same hashCode
    Assert.assertEquals(lookUpPK.equals(sameStateAsLookUpPK), lookUpPK.hashCode() == sameStateAsLookUpPK.hashCode()); //equal objects have the same hashCode
  }
}