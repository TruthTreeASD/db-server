package edu.neu.cs6510.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.neu.cs6510.model.primeKey.LookUpPK;

import static org.junit.Assert.*;

public class AttributeValueTest {
  AttributeValue attributeValue;
  LookUpPK lookUpPK;

  @Before
  public void setUp() throws Exception {
    lookUpPK = new LookUpPK();
    lookUpPK.setAttribute_mapping_id(1);
    lookUpPK.setLocation_id(1);
    lookUpPK.setYear(2019);

    attributeValue = new AttributeValue(1.0, lookUpPK, "Test", 1, 1, 1);
  }

  @Test
  public void getValue() {
    Assert.assertEquals(new Double(1.0), attributeValue.getValue());
  }

  @Test
  public void getLookUpPK() {
    Assert.assertEquals(lookUpPK, attributeValue.getLookUpPK());
  }

  @Test
  public void getAttributeName() {
    Assert.assertEquals("Test", attributeValue.getAttributeName());
  }

  @Test
  public void getAttributeId() {
    Assert.assertEquals(new Integer(1), attributeValue.getAttributeId());
  }

  @Test
  public void getPropertyId() {
    Assert.assertEquals(1, attributeValue.getPropertyId());
  }

  @Test
  public void getCollectionId() {
    Assert.assertEquals(1, attributeValue.getCollectionId());
  }

  @Test
  public void setValue() {
    attributeValue.setValue(2.0);
    Assert.assertEquals(new Double(2.0), attributeValue.getValue());
  }

  @Test
  public void setLookUpPK() {
    LookUpPK newLookupPK = new LookUpPK();
    lookUpPK = new LookUpPK();
    lookUpPK.setAttribute_mapping_id(2);
    lookUpPK.setLocation_id(2);
    lookUpPK.setYear(2018);

    attributeValue.setLookUpPK(newLookupPK);
    Assert.assertEquals(newLookupPK, attributeValue.getLookUpPK());
  }

  @Test
  public void setAttributeName() {
    attributeValue.setAttributeName("Test Again");
    Assert.assertEquals("Test Again", attributeValue.getAttributeName());
  }

  @Test
  public void setAttributeId() {
    attributeValue.setAttributeId(2);
    Assert.assertEquals(new Integer(2), attributeValue.getAttributeId());
  }

  @Test
  public void setPropertyId() {
    attributeValue.setPropertyId(2);
    Assert.assertEquals(2, attributeValue.getPropertyId());
  }

  @Test
  public void setCollectionId() {
    attributeValue.setCollectionId(2);
    Assert.assertEquals(2, attributeValue.getCollectionId());
  }
}