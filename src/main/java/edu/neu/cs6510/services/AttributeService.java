package edu.neu.cs6510.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import edu.neu.cs6510.model.AttributeMapping;
import edu.neu.cs6510.model.AttributeValue;
import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.repositories.AttributeMappingRepository;
import edu.neu.cs6510.repositories.LookUpRepository;
import edu.neu.cs6510.util.cache.CacheService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;

@Service
public class AttributeService {

    @Autowired
    AttributeMappingRepository attributeMappingRepository;
    
    @Autowired
    LookUpService lookUpService;
    
    @Autowired
	LookUpRepository lookUpRepository;


    public ResponseMessage findAllAttributesForSpecificStates(@RequestParam(value = "level") String level) {
        return Result.success(findAllAttributesAtLevels(level));
    }


    private List<AttributeMapping> findAllAttributesAtLevels(String level) {
        List<Integer> ids = null;
        if (level == null || level.isEmpty()) return (List) attributeMappingRepository.findAll();
        switch (level.toLowerCase()) {
            case "state":
                ids = attributeMappingRepository.findStateAttrIds();
                break;
            case "county":
                ids = attributeMappingRepository.findCountyAttrIds();
                break;
            default:
                ids = attributeMappingRepository.findCityAttrIds();
        }
        List<AttributeMapping> attributeMappings = (List<AttributeMapping>) attributeMappingRepository.findAllById(ids);
        return attributeMappings;
    }


    public ResponseMessage findAllAttributesForACollection(@RequestParam(value = "collection") List<Integer> collection) {
        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAll();
        List<AttributeMapping> attributeForCollections = new ArrayList<>();
        for (AttributeMapping attributeMapping : allAttributes) {
            for (Integer collection_id : collection) {
                if (attributeMapping.getCollection_id() == collection_id) {
                    attributeForCollections.add(attributeMapping);
                    break;
                }
            }
        }

        return Result.success(attributeForCollections);
    }



    public ResponseMessage findAllAttributesForCollectionAndProperty(
            @RequestParam(value = "collection") List<Integer> collection,
            @RequestParam(value = "property") List<Integer> property) {

        return Result.success(findAllAttributesWithValuesHelper(collection, property));

    }

    private List<AttributeValue> findAllAttributesWithValuesHelper(List<Integer> collection, List<Integer> property) {
        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAll();
        List<AttributeMapping> attributeForCollections = new ArrayList<>();
        List<AttributeMapping> attributeForCollectionsAndProperties = new ArrayList<>();
        List<AttributeValue> attributeValueList = new ArrayList<>();
        for (AttributeMapping attributeMapping : allAttributes) {
            for (Integer collection_id : collection) {
                if (attributeMapping.getCollection_id() == collection_id) {
                    attributeForCollections.add(attributeMapping);
                    break;
                }
            }
        }
        for (AttributeMapping attributeMapping : attributeForCollections) {
            for (Integer property_id : property) {
                if (attributeMapping.getProperty_id() == property_id) {
                    attributeForCollectionsAndProperties.add(attributeMapping);
                    break;
                }
            }
        }
        for (AttributeMapping attributeMapping : attributeForCollectionsAndProperties) {
            List<LookUpData> lookUpDataList = lookUpRepository.findLookUpDataForAttributeId(attributeMapping.getId());
            for (LookUpData look : lookUpDataList) {
                AttributeValue attributeValue = new AttributeValue(look.getValue(), look.getLookUpPK(), attributeMapping.getName(),
                        attributeMapping.getId(), attributeMapping.getProperty_id(), attributeMapping.getCollection_id());
                attributeValueList.add(attributeValue);
            }
        }
        attributeValueList.sort((a,b) -> b.getLookUpPK().getYear() - a.getLookUpPK().getYear());
        return attributeValueList;
    }



    public ResponseMessage findAllAttributesForCollectionAndPropertyAndStates(@RequestParam(value = "collection") List<Integer> collection,
                                                                                   @RequestParam(value = "property") List<Integer> property,
                                                                                   @RequestParam(value = "location") List<Integer> states) {
        List<AttributeValue> attributeValues = new ArrayList<>();
        List<AttributeValue> attributeValueList = findAllAttributesWithValuesHelper(collection, property);
        for (AttributeValue attributeValue : attributeValueList) {
            for (Integer state_id : states) {
                if (attributeValue.getLookUpPK().getLocation_id().equals(state_id)) {
                    attributeValues.add(attributeValue);
                }
            }
        }
        attributeValues.sort((a,b) -> b.getLookUpPK().getYear() - a.getLookUpPK().getYear());
        return Result.success(attributeValues);
    }


    public ResponseMessage findAllAttributesByAttributeIds(
            @RequestParam(value = "attributes") List<Integer> attributeIds) {
        List<AttributeValue> attributeValueList = new ArrayList<>();
        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAllById(attributeIds);
        for (AttributeMapping attributeMapping : allAttributes) {
            for (Integer id : attributeIds) {
                if (attributeMapping.getId() == id) {
                    List<LookUpData> lookUpDataList = lookUpRepository.findLookUpDataForAttributeId(attributeMapping.getId());
                    for (LookUpData look : lookUpDataList) {
                        AttributeValue attributeValue = new AttributeValue(look.getValue(), look.getLookUpPK(), attributeMapping.getName(),
                                attributeMapping.getId(), attributeMapping.getProperty_id(), attributeMapping.getCollection_id());
                        attributeValueList.add(attributeValue);
                    }
                    break;
                }

            }
        }
        attributeValueList.sort((a,b) -> b.getLookUpPK().getYear() - a.getLookUpPK().getYear());
        return Result.success(attributeValueList);
    }


    public ResponseMessage findAllAttributesByAttributeIdsForStates(
            @RequestParam(value = "attributes") List<Integer> attributeIds,
            @RequestParam(value = "state") List<Integer> stateIds) {

        return Result.success(findAttributeByIdsForStateHelper(attributeIds, stateIds));

    }

    private List<AttributeValue> findAttributeByIdsForStateHelper(List<Integer> attributeIds, List<Integer> stateIds) {
        List<AttributeValue> attributeValueList = new ArrayList<>();
//        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAllById(attributeIds);
//        for (AttributeMapping attributeMapping : allAttributes) {
//            List<LookUpData> lookUpDataList = lookUpRepository.findLookUpDataForAttributeId(attributeMapping.getId());
//            for (LookUpData look : lookUpDataList) {
//                for (Integer state_id : stateIds) {
//                    if (look.getLookUpPK().getLocation_id().equals(state_id)) {
//                        AttributeValue attributeValue = new AttributeValue(look.getValue(), look.getLookUpPK(), attributeMapping.getName(),
//                                attributeMapping.getId(), attributeMapping.getProperty_id(), attributeMapping.getCollection_id());
//                        attributeValueList.add(attributeValue);
//                    }
//                }
//            }
//        }
//        attributeValueList.sort((a,b) -> b.getLookUpPK().getYear() - a.getLookUpPK().getYear());

        List<LookUpData> lookUpData = lookUpRepository.findLookUpDataForAttributeIdAndLoc(attributeIds, stateIds);
        return lookUpData.stream().map(look -> { return new AttributeValue(look.getValue(), look.getLookUpPK(),
                CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getName(),
                look.getLookUpPK().getAttribute_mapping_id(),
                CacheService.propertyMap.get( CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getProperty_id()).getId(),
                CacheService.collectionMap.get( CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getCollection_id()).getId());}).collect(Collectors.toList());
    }


    public ResponseMessage findAllAttributesByAttributeIdsForStatesInYears(
            @RequestParam(value = "attributes") List<Integer> attributeIds,
            @RequestParam(value = "state") List<Integer> stateIds,
            @RequestParam(value = "yearRange") List<Integer> yearRange) {
        int startYear = yearRange.get(0);
        int endYear = yearRange.get(1);
//        List<AttributeValue> attributeValueList = new ArrayList<>();
//        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAllById(attributeIds);
//        for (AttributeMapping attributeMapping : allAttributes) {
//            List<LookUpData> lookUpDataList = lookUpRepository.findLookUpDataForAttributeId(attributeMapping.getId());
//            for (LookUpData look : lookUpDataList) {
//                for (Integer state_id : stateIds) {
//                    if (look.getLookUpPK().getLocation_id().equals(state_id) && look.getLookUpPK().getYear() >= startYear
//                            && look.getLookUpPK().getYear() <= endYear) {
//                        AttributeValue attributeValue = new AttributeValue(look.getValue(),look.getLookUpPK(), attributeMapping.getName(),
//                                attributeMapping.getId(), attributeMapping.getProperty_id(), attributeMapping.getCollection_id());
//                        attributeValueList.add(attributeValue);
//                    }
//                }
//
//            }
//        }
        List<LookUpData> lookUpData = lookUpRepository.findByAttributeIdAndLocAndTimeRange(attributeIds, stateIds, startYear, endYear);
        List<AttributeValue> attributeValueList = lookUpData.stream().map(look -> { return new AttributeValue(look.getValue(), look.getLookUpPK(),
                CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getName(),
                look.getLookUpPK().getAttribute_mapping_id(),
                CacheService.propertyMap.get( CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getProperty_id()).getId(),
                CacheService.collectionMap.get( CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getCollection_id()).getId());}).collect(Collectors.toList());
        return Result.success(attributeValueList);
    }


    public ResponseMessage findAllAttributesByIdsForStatesAndYears(
            @RequestParam(value = "attributes") List<Integer> attributeIds,
            @RequestParam(value = "state") List<Integer> stateIds,
            @RequestParam(value = "yearList") List<Integer> yearList) {
//        List<AttributeValue> attributeValueList = findAttributeByIdsForStateHelper(attributeIds, stateIds);
//        List<AttributeValue> attributeValues = new ArrayList<>();
//        for(AttributeValue attributeValue: attributeValueList){
//            for(Integer year: yearList){
//                if(attributeValue.getLookUpPK().getYear().equals(year)){
//                    attributeValues.add(attributeValue);
//                }
//            }
//        }
        List<LookUpData> lookUpData = lookUpRepository.findByAttributeIdAndLocAndTimeList(attributeIds, stateIds, yearList);
        List<AttributeValue> attributeValueList = lookUpData.stream().map(look -> { return new AttributeValue(look.getValue(), look.getLookUpPK(),
                CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getName(),
                look.getLookUpPK().getAttribute_mapping_id(),
                CacheService.propertyMap.get( CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getProperty_id()).getId(),
                CacheService.collectionMap.get( CacheService.attributeMappingMap.get(look.getLookUpPK().getAttribute_mapping_id()).getCollection_id()).getId());}).collect(Collectors.toList());
        return Result.success(attributeValueList);

    }


    public ResponseMessage findAttriByIds(List<Integer> ids) {
        return Result.success(attributeMappingRepository.findAllById(ids));
    }
}

