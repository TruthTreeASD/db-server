package edu.neu.cs6510.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseExceptionTest {
  private BaseException baseException;

  @Before
  public void setUp() throws Exception {
    baseException = new BaseException();
  }

  @Test
  public void getErrorCode() {
    baseException.setErrorCode("0");
    Assert.assertEquals("0", baseException.getErrorCode());
  }
}