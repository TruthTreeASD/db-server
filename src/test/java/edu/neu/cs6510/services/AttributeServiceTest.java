package edu.neu.cs6510.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.neu.cs6510.model.AttributeMapping;
import edu.neu.cs6510.model.AttributeValue;
import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.model.primeKey.LookUpPK;
import edu.neu.cs6510.repositories.AttributeMappingRepository;
import edu.neu.cs6510.repositories.LookUpRepository;
import edu.neu.cs6510.services.AttributeService;
import edu.neu.cs6510.util.cache.CacheService;
import edu.neu.cs6510.util.http.ResponseMessage;

public class AttributeServiceTest{

	@InjectMocks
	AttributeService attributeService;

	@Mock
	AttributeMappingRepository attributeMappingRepository;

	@Mock
	LookUpRepository lookUpRepository;

	List<Integer> l;

	List<AttributeMapping> att;

	List<LookUpData> lookUpDataList;

	List<AttributeValue> attributeValueList;

	@Before
	public void setup() {
		attributeService = new AttributeService();

		MockitoAnnotations.initMocks(this);

		l = new ArrayList<>();
		l.add(1);
		l.add(2);
		l.add(3);

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

	}

	@Test
	public void findAllAttributesForSpecificStatesTest() {

		Mockito.when(attributeMappingRepository.findStateAttrIds()).thenReturn(l);
		Mockito.when(attributeMappingRepository.findCountyAttrIds()).thenReturn(l);
		Mockito.when(attributeMappingRepository.findCityAttrIds()).thenReturn(l);

		Mockito.when(attributeMappingRepository.findAllById(l)).thenReturn((List<AttributeMapping>) att);
		ResponseMessage res = attributeService.findAllAttributesForSpecificStates("state");
		assertEquals(res.getData(), att);
		ResponseMessage res2 = attributeService.findAllAttributesForSpecificStates("county");
		assertEquals(res2.getData(), att);
		ResponseMessage res3 = attributeService.findAllAttributesForSpecificStates("city");
		assertEquals(res3.getData(), att);
		ResponseMessage res4 = attributeService.findAllAttributesForSpecificStates("test");
		assertEquals(res4.getData(), new ArrayList<AttributeMapping>());
	}

	@Test
	public void findAllAttributesForACollectionTest() {

		List<AttributeMapping> att = new ArrayList<>();
		Mockito.when(attributeMappingRepository.findAll()).thenReturn((List<AttributeMapping>) att);

		AttributeMapping temp = new AttributeMapping();
		temp.setName("Population");
		temp.setId(1);
		temp.setCollection_id(1);
		temp.setProperty_id(1);

		att.add(temp);
		ResponseMessage r = attributeService.findAllAttributesForACollection(l);
		assertEquals(r.getData(), att);
	}

	@Test
	public void findAllAttributesForCollectionAndPropertyTest() {

		Mockito.when(attributeMappingRepository.findAll()).thenReturn((List<AttributeMapping>) att);
		Mockito.when(lookUpRepository.findLookUpDataForAttributeId(Matchers.any(Integer.class)))
				.thenReturn((List<LookUpData>) lookUpDataList);

		ResponseMessage r = attributeService.findAllAttributesForCollectionAndProperty(l, l);
		List<AttributeValue> l = (List<AttributeValue>) r.getData();
		assertEquals(l.size(), attributeValueList.size());
		assertEquals(l.get(0).getValue(), attributeValueList.get(0).getValue());

	}

	@SuppressWarnings("deprecation")
	@Test
	public void findAllAttributesForCollectionAndPropertyAndStatesTest() {

		Mockito.when(attributeMappingRepository.findAll()).thenReturn((List<AttributeMapping>) att);
		Mockito.when(lookUpRepository.findLookUpDataForAttributeId(Matchers.any(Integer.class)))
				.thenReturn((List<LookUpData>) lookUpDataList);
		ResponseMessage r = attributeService.findAllAttributesForCollectionAndPropertyAndStates(l, l, l);

		@SuppressWarnings("unchecked")
		List<AttributeValue> l = (List<AttributeValue>) r.getData();
		assertEquals(l.size(), attributeValueList.size());
		assertEquals(l.get(0).getValue(), attributeValueList.get(0).getValue());
	}

	@Test
	public void findAllAttributesByAttributeIdsTest() {
		Mockito.when(attributeMappingRepository.findAllById(l)).thenReturn((List<AttributeMapping>) att);
		Mockito.when(lookUpRepository.findLookUpDataForAttributeId(Matchers.any(Integer.class)))
				.thenReturn((List<LookUpData>) lookUpDataList);
		ResponseMessage r = attributeService.findAllAttributesByAttributeIds(l);

		List<AttributeValue> l = (List<AttributeValue>) r.getData();
		assertEquals(l.size(), attributeValueList.size());
		assertEquals(l.get(0).getValue(), attributeValueList.get(0).getValue());
	}

	@Test
	public void findAllAttributesByAttributeIdsForStatesTest() {
		Mockito.when(attributeMappingRepository.findAllById(l)).thenReturn((List<AttributeMapping>) att);
		Mockito.when(lookUpRepository.findLookUpDataForAttributeId(Matchers.any(Integer.class)))
				.thenReturn((List<LookUpData>) lookUpDataList);

		Mockito.when(lookUpRepository.findByAttributeIdAndLocAndTimeList(l, l, l))
				.thenReturn((List<LookUpData>) lookUpDataList);
		ResponseMessage r = attributeService.findAllAttributesByAttributeIdsForStates(l, l);

		List<AttributeValue> l = (List<AttributeValue>) r.getData();
		assertEquals(l.size(), 0);

	}

	@Test
	public void findAllAttributesByAttributeIdsForStatesInYearsTest() {
		Mockito.when(lookUpRepository.findByAttributeIdAndLocAndTimeRange(l, l, 1990, 2000))
				.thenReturn((List<LookUpData>) lookUpDataList);
		
	/*	Mockito.when(CacheService.attributeMappingMap.get(Matchers.any()).getName())
		 CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getName(),
         look.getLookUpPK().getAttribute_mapping_id(),
         CacheService.propertyMap.get( CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getProperty_id()).getId(),
         CacheService.collectionMap.get( CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getCollection_id()).getId());}).collect(Collectors.toList());
*/
		List<Integer> i = new ArrayList<>();
		i.add(1990);
		i.add(2000);
		ResponseMessage r = attributeService.findAllAttributesByAttributeIdsForStatesInYears(l, l, i);
		List<AttributeValue> l = (List<AttributeValue>) r.getData();

	}

	@Test
	public void findAllAttributesByIdsForStatesAndYearsTest() {
		ResponseMessage r = attributeService.findAllAttributesByIdsForStatesAndYears(l, l, l);
		
		
	}

	@Test
	public void queryAttriById() {
		Mockito.when(attributeMappingRepository.findAllById(l)).thenReturn((List<AttributeMapping>) att);
		ResponseMessage r = attributeService.findAttriByIds(l);

		List<AttributeValue> l = (List<AttributeValue>) r.getData();
		assertEquals(l.size(), attributeValueList.size());
		assertEquals(l.get(0).getValue(), attributeValueList.get(0).getValue());
	}

}
