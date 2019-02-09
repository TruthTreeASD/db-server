package com.example.demo.services;

import com.example.demo.model.AttributeMapping;
import com.example.demo.model.AttributeValue;
import com.example.demo.model.LookUpData;
import com.example.demo.repositories.AttributeMappingRepository;
import com.example.demo.repositories.LookUpRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AttributeService {

    @Autowired
    AttributeMappingRepository attributeMappingRepository;
    @Autowired
    LookUpService lookUpService;
    @Autowired
    LookUpRepository lookUpRepository;


    @ApiOperation(value = "find Availbe Attr by location level")
    @GetMapping("/api/attributes")
    public List<AttributeMapping> findAllAttributesForSpecificStates(@RequestParam(value = "level") String level) {
        return findAllAttributesAtLevels(level);
    }


    private List<AttributeMapping> findAllAttributesAtLevels(String level) {
        List<Integer> ids = null;
        switch (level.toLowerCase()) {
            case "state":
                ids = attributeMappingRepository.findStateAttrIds();
                break;
            case "county":
                ids = attributeMappingRepository.findCountyAttrIds();
                break;
            case "city":
                ids = attributeMappingRepository.findCityAttrIds();
                break;
            default:
                return new ArrayList<>();
        }
        List<AttributeMapping> attributeMappings = (List<AttributeMapping>) attributeMappingRepository.findAllById(ids);
        return attributeMappings;
    }

    @ApiOperation(value = "find attributes for a collection")
    @GetMapping("/api/attributes/collection")
    public List<AttributeMapping> findAllAttributesForACollection(@RequestParam(value = "collection") List<Integer> collection) {
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
        return attributeForCollections;
    }


    @ApiOperation(value = "find attributes for a collection and property")
    @GetMapping("/api/attributes/collection&property")
    public List<AttributeValue> findAllAttributesForCollectionAndProperty(
            @RequestParam(value = "collection") List<Integer> collection,
            @RequestParam(value = "property") List<Integer> property) {

        return findAllAttributesWithValuesHelper(collection, property);

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
                AttributeValue attributeValue = new AttributeValue(look.getLookUpPK(), attributeMapping.getName(),
                        attributeMapping.getId(), attributeMapping.getProperty_id(), attributeMapping.getCollection_id());
                attributeValueList.add(attributeValue);
            }
        }
        return attributeValueList;
    }


    @ApiOperation(value = "find attributes for a collection and property for specific states")
    @GetMapping("/api/attributes/collection&property&states")
    public List<AttributeValue> findAllAttributesForCollectionAndPropertyAndStates(@RequestParam(value = "collection") List<Integer> collection,
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
        return attributeValues;
    }

    @ApiOperation(value = "find attributes by specified id")
    @GetMapping("/api/attributes/attributeIds")
    public List<AttributeValue> findAllAttributesByAttributeIds(
            @RequestParam(value = "attributes") List<Integer> attributeIds) {
        List<AttributeValue> attributeValueList = new ArrayList<>();
        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAllById(attributeIds);
        for (AttributeMapping attributeMapping : allAttributes) {
            for (Integer id : attributeIds) {
                if (attributeMapping.getId() == id) {
                    List<LookUpData> lookUpDataList = lookUpRepository.findLookUpDataForAttributeId(attributeMapping.getId());
                    for (LookUpData look : lookUpDataList) {
                        AttributeValue attributeValue = new AttributeValue(look.getLookUpPK(), attributeMapping.getName(),
                                attributeMapping.getId(), attributeMapping.getProperty_id(), attributeMapping.getCollection_id());
                        attributeValueList.add(attributeValue);
                    }
                    break;
                }

            }
        }
        return attributeValueList;
    }

    @ApiOperation(value = "find attributes by specified id for specific states")
    @GetMapping("/api/attributes/attributeIds&states")
    public List<AttributeValue> findAllAttributesByAttributeIdsForStates(
            @RequestParam(value = "attributes") List<Integer> attributeIds,
            @RequestParam(value = "state") List<Integer> stateIds) {

        return findAttributeByIdsForStateHelper(attributeIds, stateIds);

    }

    private List<AttributeValue> findAttributeByIdsForStateHelper(List<Integer> attributeIds, List<Integer> stateIds) {
        List<AttributeValue> attributeValueList = new ArrayList<>();
        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAllById(attributeIds);
        for (AttributeMapping attributeMapping : allAttributes) {
            List<LookUpData> lookUpDataList = lookUpRepository.findLookUpDataForAttributeId(attributeMapping.getId());
            for (LookUpData look : lookUpDataList) {
                for (Integer state_id : stateIds) {
                    if (look.getLookUpPK().getLocation_id().equals(state_id)) {
                        AttributeValue attributeValue = new AttributeValue(look.getLookUpPK(), attributeMapping.getName(),
                                attributeMapping.getId(), attributeMapping.getProperty_id(), attributeMapping.getCollection_id());
                        attributeValueList.add(attributeValue);
                    }
                }
            }
        }
        return attributeValueList;
    }

    @ApiOperation(value = "find attributes by specified id for specific states within year range")
    @GetMapping("/api/attributes/attributeIds&states&yearRange")
    public List<AttributeValue> findAllAttributesByAttributeIdsForStatesInYears(
            @RequestParam(value = "attributes") List<Integer> attributeIds,
            @RequestParam(value = "state") List<Integer> stateIds,
            @RequestParam(value = "yearRange") List<Integer> yearRange) {
        int startYear = yearRange.get(0);
        int endYear = yearRange.get(1);
        List<AttributeValue> attributeValueList = new ArrayList<>();
        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAllById(attributeIds);
        for (AttributeMapping attributeMapping : allAttributes) {
            List<LookUpData> lookUpDataList = lookUpRepository.findLookUpDataForAttributeId(attributeMapping.getId());
            for (LookUpData look : lookUpDataList) {
                for (Integer state_id : stateIds) {
                    if (look.getLookUpPK().getLocation_id().equals(state_id) && look.getLookUpPK().getYear() >= startYear
                            && look.getLookUpPK().getYear() <= endYear) {
                        AttributeValue attributeValue = new AttributeValue(look.getLookUpPK(), attributeMapping.getName(),
                                attributeMapping.getId(), attributeMapping.getProperty_id(), attributeMapping.getCollection_id());
                        attributeValueList.add(attributeValue);
                    }
                }
            }
        }
        return attributeValueList;
    }

    @ApiOperation(value = "find attributes by specified id for specific states within year range")
    @GetMapping("/api/attributes/attributeIds&states&yearRange")
    public List<AttributeValue> findAllAttributesByIdsForStatesAndYears(
            @RequestParam(value = "attributes") List<Integer> attributeIds,
            @RequestParam(value = "state") List<Integer> stateIds,
            @RequestParam(value = "yearList") List<Integer> yearList) {
        List<AttributeValue> attributeValueList = findAttributeByIdsForStateHelper(attributeIds, stateIds);
        List<AttributeValue> attributeValues = new ArrayList<>();
        for(AttributeValue attributeValue: attributeValueList){
            for(Integer year: yearList){
                if(attributeValue.getLookUpPK().getYear().equals(year)){
                    attributeValues.add(attributeValue);
                }
            }
        }
        return attributeValues;
    }

}

