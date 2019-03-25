package edu.neu.cs6510.controller;

import edu.neu.cs6510.model.Property;
import edu.neu.cs6510.repositories.PropertyRepository;
import edu.neu.cs6510.services.PropertyService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PropertyControllerTest {

    @InjectMocks
    PropertyService propertyService;

    @Mock
    PropertyRepository propertyRepository;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllPropertiesTest() {
        Property property1 = new Property();
        property1.setId(1);
        property1.setName("prop1");

        Property property2 = new Property();
        property2.setId(2);
        property2.setName("prop2");

        Property property3 = new Property();
        property3.setId(3);
        property3.setName("prop3");

        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property1);
        propertyList.add(property2);
        propertyList.add(property3);

        Mockito.when(propertyRepository.findAll()).thenReturn(propertyList);
        ResponseMessage res = propertyService.findAllProperties();
        assertEquals(res.getData(), propertyList);

    }


    @Test
    public void findPropertyByIdTest() {
        Property property1 = new Property();
        property1.setId(1);
        property1.setName("property-1");

        Property property2 = new Property();
        property2.setId(2);
        property2.setName("property-2");

        Property property3 = new Property();
        property3.setId(3);
        property3.setName("property-3");

        List<Integer> ids= new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);

        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property1);
        propertyList.add(property2);
        propertyList.add(property3);

        Mockito.when(propertyRepository.findAllById(ids)).thenReturn(propertyList);
        ResponseMessage res = propertyService.findPropertyById(ids);
        assertEquals(res.getData(), propertyList);

    }

}
