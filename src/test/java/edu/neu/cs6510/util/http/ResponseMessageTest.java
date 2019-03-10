package edu.neu.cs6510.util.http;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseMessageTest {
  private ResponseMessage<String> responseMessage;

  @Before
  public void setUp() throws Exception {
    responseMessage = new ResponseMessage<>();

    responseMessage.setRespCode("0");
    responseMessage.setMessage("Test");
    responseMessage.setData("Data");
  }

  @Test
  public void getRespCode() {
    Assert.assertEquals("0", responseMessage.getRespCode());
  }

  @Test
  public void getMessage() {
    Assert.assertEquals("Test", responseMessage.getMessage());
  }

  @Test
  public void getData() {
    Assert.assertEquals("Data", responseMessage.getData());
  }

  @Test
  public void isOk() {
    Assert.assertFalse(responseMessage.isOk());
  }
}