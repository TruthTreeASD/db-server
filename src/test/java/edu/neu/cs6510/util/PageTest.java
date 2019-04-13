package edu.neu.cs6510.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PageTest {
  private Page<String> page;
  private List<String> testList;

  @Before
  public void setUp() throws Exception {
    testList = new ArrayList<>();
    testList.add("1");
    testList.add("2");
    testList.add("3");
    testList.add("4");
    testList.add("5");
    testList.add("6");

    page = new Page<String>(testList, 1l, 1, 1);
  }

  @Test
  public void getData() {
    Assert.assertEquals(testList, page.getData());
  }

  @Test
  public void setData() {
    List<String> testList2 = new ArrayList<>();
    testList2.add("2");
    testList2.add("3");
    testList2.add("4");
    testList2.add("5");
    testList2.add("6");
    testList2.add("7");

    page.setData(testList2);
    Assert.assertEquals(testList2, page.getData());
  }

  @Test
  public void getTotal() {
    Assert.assertEquals(new Long(1), page.getTotal());
  }

  @Test
  public void setTotal() {
    page.setTotal(2l);
    Assert.assertEquals(new Long(2), page.getTotal());
  }

  @Test
  public void getTotalPage() {
    Assert.assertEquals(new Long(1), page.getTotalPage());
  }

  @Test
  public void setTotalPage() {
    page.setTotalPage(2l);
    Assert.assertEquals(new Long(2), page.getTotalPage());
  }

  @Test
  public void getCurrentPage() {
    Assert.assertEquals(new Integer(1), page.getCurrentPage());
  }

  @Test
  public void setCurrentPage() {
    page.setCurrentPage(2);
    Assert.assertEquals(new Integer(2), page.getCurrentPage());
  }

  @Test
  public void getPageSize() {
    Assert.assertEquals(new Integer(1), page.getPageSize());
  }

  @Test
  public void setPageSize() {
    page.setPageSize(2);
    Assert.assertEquals(new Integer(2), page.getPageSize());
  }
}