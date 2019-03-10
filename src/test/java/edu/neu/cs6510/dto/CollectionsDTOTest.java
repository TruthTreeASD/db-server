package edu.neu.cs6510.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.neu.cs6510.model.Property;

import static org.junit.Assert.*;

public class CollectionsDTOTest {
  CollectionsDTO collectionsDTO;
  List<PropertyDTO> propertyDTOs;
  PropertyDTO prop1;
  PropertyDTO prop2;
  List<AttributeInfoDTO> attributeInfoDTOs;
  AttributeInfoDTO attr1;
  AttributeInfoDTO attr2;

  @Before
  public void setUp() throws Exception {
    collectionsDTO = new CollectionsDTO();
    propertyDTOs = new ArrayList<PropertyDTO>();
    attributeInfoDTOs = new ArrayList<AttributeInfoDTO>();

    prop1 = new PropertyDTO();
    prop1.setName("Prop1");
    prop1.setProperty_id(1);

    prop2 = new PropertyDTO();
    prop2.setName("Prop2");
    prop2.setProperty_id(2);
    propertyDTOs.add(prop1);
    propertyDTOs.add(prop2);

    attr1 = new AttributeInfoDTO();
    attr1.setName("Attr1");
    attr1.setAttribute_id(1);

    attr2 = new AttributeInfoDTO();
    attr2.setName("Attr2");
    attr2.setAttribute_id(2);

    attributeInfoDTOs.add(attr1);
    attributeInfoDTOs.add(attr2);
  }

  @Test
  public void getName() {
    collectionsDTO.setName("Test");
    Assert.assertEquals("Test", collectionsDTO.getName());
  }

  @Test
  public void getCollection_id() {
    collectionsDTO.setCollection_id(1);
    Assert.assertEquals(new Integer(1), collectionsDTO.getCollection_id());
  }

  @Test
  public void getProperties() {
    collectionsDTO.setProperties(propertyDTOs);
    Assert.assertEquals(propertyDTOs, collectionsDTO.getProperties());
  }

  @Test
  public void getAttributes() {
    collectionsDTO.setAttributes(attributeInfoDTOs);
    Assert.assertEquals(attributeInfoDTOs, collectionsDTO.getAttributes());
  }
}