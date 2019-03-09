package edu.neu.cs6510.controller;

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
import edu.neu.cs6510.repositories.AttributeMappingRepository;
import edu.neu.cs6510.services.AttributeService;
import edu.neu.cs6510.util.http.ResponseMessage;

public class AttributeControllerTest<E> {

	@InjectMocks
	AttributeService attributeService;

	@Mock
	AttributeMappingRepository attributeMappingRepository;

	@Before
	public void setup() {
		attributeService = new AttributeService();

		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void findAllAttributesForSpecificStatesTest() {
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(2);
		l.add(3);

		List<AttributeMapping> att = new ArrayList<>();
		AttributeMapping temp = new AttributeMapping();
		temp.setName("Population");
		temp.setId(1);
		temp.setCollection_id(1);
		temp.setProperty_id(1);

		att.add(temp);

		Mockito.when(attributeMappingRepository.findStateAttrIds()).thenReturn(l);
		Mockito.when(attributeMappingRepository.findCountyAttrIds()).thenReturn(l);
		Mockito.when(attributeMappingRepository.findCityAttrIds()).thenReturn(l);
		

		Mockito.when(attributeMappingRepository.findAllById(l)).thenReturn((List<AttributeMapping>) att);
		ResponseMessage res =  attributeService.findAllAttributesForSpecificStates("state");
		assertEquals(res.getData(),att);		
		ResponseMessage res2= attributeService.findAllAttributesForSpecificStates("county");
		assertEquals(res2.getData(),att);
		ResponseMessage res3 = attributeService.findAllAttributesForSpecificStates("city");
		assertEquals(res3.getData(),att);
	}

	/*
	 * @Test public void findAllAttributesForACollectionTest() { ResponseMessage r =
	 * attributeService.findAllAttributesForACollection(null); }
	 * 
	 * @Test public ResponseMessage findAllAttributesForCollectionAndPropertyTest()
	 * { ResponseMessage r =
	 * attributeService.findAllAttributesForCollectionAndProperty(collection,
	 * property); }
	 * 
	 * @Test public ResponseMessage
	 * findAllAttributesForCollectionAndPropertyAndStatesTest() { ResponseMessage r
	 * = attributeService.findAllAttributesForCollectionAndPropertyAndStates(
	 * collection, property, states); }
	 * 
	 * @Test public ResponseMessage findAllAttributesByAttributeIdsTest() {
	 * ResponseMessage r =
	 * attributeService.findAllAttributesByAttributeIds(attributeIds); }
	 * 
	 * @Test public ResponseMessage findAllAttributesByAttributeIdsForStatesTest() {
	 * ResponseMessage r =
	 * attributeService.findAllAttributesByAttributeIdsForStates(attributeIds,
	 * stateIds); }
	 * 
	 * @Test public ResponseMessage
	 * findAllAttributesByAttributeIdsForStatesInYearsTest() { ResponseMessage r =
	 * attributeService.findAllAttributesByAttributeIdsForStatesInYears(
	 * attributeIds, stateIds, yearRange); }
	 * 
	 * @Test public ResponseMessage findAllAttributesByIdsForStatesAndYearsTest() {
	 * ResponseMessage r =
	 * attributeService.findAllAttributesByIdsForStatesAndYears(attributeIds,
	 * stateIds, yearList); }
	 * 
	 * @Test public ResponseMessage queryAttriById() {
	 * 
	 * ResponseMessage r = attributeService.findAttriByIds(ids); }
	 */

}
