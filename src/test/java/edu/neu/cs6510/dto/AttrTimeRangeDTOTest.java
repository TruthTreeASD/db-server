package edu.neu.cs6510.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttrTimeRangeDTOTest {
  AttrTimeRangeDTO attrTimeRangeDTO;

  @Before
  public void setUp() throws Exception {
    attrTimeRangeDTO = new AttrTimeRangeDTO();
  }

  @Test
  public void getAttribute_name() {
    attrTimeRangeDTO.setAttribute_name("Test");
    Assert.assertEquals("Test", attrTimeRangeDTO.getAttribute_name());
  }

  @Test
  public void setAttribute_name() {
    attrTimeRangeDTO.setAttribute_name("Test Again");
    Assert.assertEquals("Test Again", attrTimeRangeDTO.getAttribute_name());
  }

  @Test
  public void getAttribute_id() {
    attrTimeRangeDTO.setAttribute_id(1);
    Assert.assertEquals(new Integer(1), attrTimeRangeDTO.getAttribute_id());
  }

  @Test
  public void setAttribute_id() {
    attrTimeRangeDTO.setAttribute_id(2);
    Assert.assertEquals(new Integer(2), attrTimeRangeDTO.getAttribute_id());
  }

  @Test
  public void getStartYear() {
    attrTimeRangeDTO.setStartYear("2019");
    Assert.assertEquals("2019", attrTimeRangeDTO.getStartYear());
  }

  @Test
  public void setStartYear() {
    attrTimeRangeDTO.setStartYear("2018");
    Assert.assertEquals("2018", attrTimeRangeDTO.getStartYear());
  }

  @Test
  public void getEndYear() {
    attrTimeRangeDTO.setEndYear("2019");
    Assert.assertEquals("2019", attrTimeRangeDTO.getEndYear());
  }

  @Test
  public void setEndYear() {
    attrTimeRangeDTO.setEndYear("2018");
    Assert.assertEquals("2018", attrTimeRangeDTO.getEndYear());
  }

  @Test
  public void getProperty_name() {
    attrTimeRangeDTO.setProperty_name("Property");
    Assert.assertEquals("Property", attrTimeRangeDTO.getProperty_name());
  }

  @Test
  public void setProperty_name() {
    attrTimeRangeDTO.setProperty_name("Property Again");
    Assert.assertEquals("Property Again", attrTimeRangeDTO.getProperty_name());
  }

  @Test
  public void getProperty_id() {
    attrTimeRangeDTO.setProperty_id(1);
    Assert.assertEquals(1, attrTimeRangeDTO.getProperty_id());
  }

  @Test
  public void setProperty_id() {
    attrTimeRangeDTO.setProperty_id(2);
    Assert.assertEquals(2, attrTimeRangeDTO.getProperty_id());
  }
}