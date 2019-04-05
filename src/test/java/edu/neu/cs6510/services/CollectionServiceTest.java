package edu.neu.cs6510.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.neu.cs6510.model.AttributeMapping;
import edu.neu.cs6510.model.AttributeValue;
import edu.neu.cs6510.model.Collection;
import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.model.primeKey.LookUpPK;
import edu.neu.cs6510.repositories.AttributeMappingRepository;
import edu.neu.cs6510.repositories.CollectionRepository;
import edu.neu.cs6510.repositories.LookUpRepository;
import edu.neu.cs6510.util.http.ResponseMessage;

public class CollectionServiceTest {

	@InjectMocks
	CollectionService collectionService;

	@Mock
	AttributeMappingRepository attributeMappingRepository;

	@Mock
	LookUpRepository lookUpRepository;

	@Mock
	CollectionRepository collectionRepository;

	List<Integer> l;

	List<AttributeMapping> att;

	List<Collection> c;

	List<LookUpData> lookUpDataList;

	List<AttributeValue> attributeValueList;

	@Before
	public void setup() {
		collectionService = new CollectionService();

		MockitoAnnotations.initMocks(this);

		l = new ArrayList<>();
		l.add(1);
		l.add(4);
		l.add(5);

		att = new ArrayList<>();
		AttributeMapping temp = new AttributeMapping();
		temp.setName("Population");
		temp.setId(1);
		temp.setCollection_id(1);
		temp.setProperty_id(1);

		att.add(temp);

		lookUpDataList = new ArrayList<>();
		LookUpData e = new LookUpData();
		LookUpPK pk = new LookUpPK();
		pk.setAttribute_mapping_id(1);
		pk.setLocation_id(1);
		pk.setYear(2001);

		e.setLookUpPK(pk);
		e.setValue(1000.00);
		lookUpDataList.add(e);

		AttributeValue attributeValue = new AttributeValue(e.getValue(), e.getLookUpPK(), temp.getName(), temp.getId(),
				temp.getProperty_id(), temp.getCollection_id());
		attributeValueList = new ArrayList<>();
		attributeValueList.add(attributeValue);

		c = new ArrayList<>();
		Collection c1 = new Collection();
		c1.setId(1);
		c1.setName("General");
		c.add(c1);
	}

	@Test
	public void findAllCollectionsTest() {
		Mockito.when(collectionRepository.findAll()).thenReturn((List<Collection>) c);
		ResponseMessage r = collectionService.findAllCollections();
		assertEquals(r.getData(), c);

	}

	@Test
	public void findAvailbeAttr1Test() {
		Mockito.when(collectionRepository.findAll()).thenReturn((List<Collection>) c);
		ResponseMessage r = collectionService.findAvailbeAttr1("state", 2016, 10000000);
		assertEquals(r.getData(), new ArrayList<>());

	}

	@Test
	public void findAvailbeAttrTest() {
		ResponseMessage r = collectionService.findAvailbeAttr("state", l);
		assertEquals(r.getData(), new ArrayList<>());
	}
	
	@Test
	public void findAvailbeAttr2Test() {
		ResponseMessage r = collectionService.findAvailbeAttr(1);
		assertEquals(r.getData(), new ArrayList<>());
	}

	
	@Test
	public void findAvailbeAttr3Test() {
		ResponseMessage r = collectionService.findAvailbeAttr(l,l);
		assertEquals(r.getData(), new ArrayList<>());
	}
	
	@Test
	public void findAvailbeAttr4Test() {
		ResponseMessage r = collectionService.findAvailbeAttr("state",2016,10000000);
		assertEquals(r.getData(), new ArrayList<>());
	}
	
	

	@Test
	public void attriTimeRangeTest() {
		ResponseMessage r = collectionService.attriTimeRange("state",l);
		assertEquals(r.getData(), new ArrayList<>());
	}
}
