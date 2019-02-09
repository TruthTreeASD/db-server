package com.example.demo.services;

import com.example.demo.model.AttributeMapping;
import com.example.demo.model.AttributeValue;
import com.example.demo.model.LookUpData;
import com.example.demo.repositories.AttributeMappingRepository;
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
    public List<AttributeMapping> findAllAttributesForACollection(@RequestParam(value = "collection") List<Integer> collection){
        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAll();
        List<AttributeMapping> attributeForCollections = new ArrayList<>();
        for(AttributeMapping attributeMapping: allAttributes){
            for(Integer collection_id: collection){
                if(attributeMapping.getCollection_id() == collection_id){
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
            @RequestParam(value = "property") List<Integer> property){
        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAll();
        List<LookUpData> lookUpData = lookUpService.findAllRecords();
        List<AttributeMapping> attributeForCollections = new ArrayList<>();
        List<AttributeMapping> attributeForCollectionsAndProperties = new ArrayList<>();
        List<AttributeValue> attributeValueList = new ArrayList<>();
        for(AttributeMapping attributeMapping: allAttributes){
            for(Integer collection_id: collection){
                if(attributeMapping.getCollection_id() == collection_id){
                    attributeForCollections.add(attributeMapping);
                    break;
                }
            }
        }
        for(AttributeMapping attributeMapping: attributeForCollections){
            for(Integer property_id: property){
                if(attributeMapping.getProperty_id() == property_id){
                    attributeForCollectionsAndProperties.add(attributeMapping);
                    break;
                }
            }
        }

        for(AttributeMapping attributeMapping: attributeForCollectionsAndProperties){
            for(LookUpData look: lookUpData){
                if(attributeMapping.getId() == look.getLookUpPK().getAttribute_mapping_id()){
                    AttributeValue attributeValue = new AttributeValue(look.getValue(), attributeMapping.getName(),
                            attributeMapping.getId(),attributeMapping.getProperty_id(), attributeMapping.getCollection_id());
                    attributeValueList.add(attributeValue);
                    break;
                }
            }
        }

        return attributeValueList;
    }


//    @ApiOperation(value = "find attributes for a collection and property for specific states")
//    @GetMapping("/api/attributes/collection&property")
//    public List<AttributeMapping> findAllAttributesForCollectionAndPropertyAndStates(
//            @RequestParam(value = "collection") List<Integer> collection,
//            @RequestParam(value = "property") List<Integer> property,
//            @RequestParam(value = "state") List<Integer> stateIds){
//        List<AttributeMapping> attributeMappingsForCollectionAndProperty = findAllAttributesForCollectionAndProperty(collection, property);
//        List<LookUpData> specifiedStateData = new ArrayList<>();
//        List<LookUpData> lookUpData = lookUpService.findAllRecords();
//        List<AttributeMapping> attributeMappings = new ArrayList<>();
//        for(Integer state_id: stateIds){
//            for(LookUpData look: lookUpData){
//                if(look.getLocation_id() == state_id){
//                    specifiedStateData.add(look);
//                }
//            }
//        }
//
//        for(AttributeMapping attributeMapping: attributeMappingsForCollectionAndProperty){
//            for(LookUpData lookUpData1: specifiedStateData){
//                if(attributeMapping.getId() == lookUpData1.getAttribute_mapping_id()){
//                    attributeMappings.add(attributeMapping);
//                }
//            }
//        }
//        return attributeMappings;
//    }

    @ApiOperation(value = "find attributes by specified id")
    @GetMapping("/api/attributes/level=state&attributeIds")
    public List<AttributeMapping> findAllAttributesByAttributeIds(
            @RequestParam(value = "attributes") List<Integer> attributeIds){
        List<AttributeMapping> allAttributes = (List<AttributeMapping>) attributeMappingRepository.findAllById(attributeIds);
        List<AttributeMapping> attributesByIds = new ArrayList<>();
        for(AttributeMapping attributeMapping: allAttributes){
            for(Integer id : attributeIds){
                if(attributeMapping.getId() == id){
                    attributesByIds.add(attributeMapping);
                }
            }

        }
        return attributesByIds;
    }
//
//    @ApiOperation(value = "find attributes by specified id for specific states")
//    @GetMapping("/api/attributes/level=state")
//    public List<AttributeMapping> findAllAttributesByAttributeIdsForStates(
//            @RequestParam(value = "attributes") List<Integer> attributeIds,
//            @RequestParam(value = "state") List<Integer> stateIds){
//        List<AttributeMapping> attributeMappingList = findAllAttributesByAttributeIds(attributeIds);
//        List<AttributeMapping> attributeMappings = new ArrayList<>();
//        List<LookUpData> specifiedStateData = new ArrayList<>();
//        List<LookUpData> lookUpData = lookUpService.findAllRecords();
//        for(Integer state_id: stateIds){
//            for(LookUpData look: lookUpData){
//                if(look.getLocation_id() == state_id){
//                    specifiedStateData.add(look);
//                }
//            }
//        }
//
//        for(AttributeMapping attributeMapping: attributeMappingList){
//            for(LookUpData lookUpData1: specifiedStateData){
//                if(attributeMapping.getId() == lookUpData1.getAttribute_mapping_id()){
//                    attributeMappings.add(attributeMapping);
//                }
//            }
//        }
//        return attributeMappings;
//
//    }

//    public List<AttributeMapping> findAttributesWithYearRange(
//            @RequestParam(value = "attributes") List<Integer> attributeIds,
//            @RequestParam(value = "state") List<Integer> stateIds,
//            @RequestParam(value = "yearRange") List<Integer> yearRange){
//
//    }


}

