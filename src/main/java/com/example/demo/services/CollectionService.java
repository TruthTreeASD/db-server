package com.example.demo.services;

import com.example.demo.dto.*;
import com.example.demo.model.AttributeMapping;
import com.example.demo.model.Collection;
import com.example.demo.model.Property;
import com.example.demo.repositories.AttributeMappingRepository;
import com.example.demo.repositories.CollectionRepository;
import com.example.demo.repositories.LocationRepository;
import com.example.demo.repositories.PropertyRepository;
import com.example.demo.util.BeanMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*")
public class CollectionService {

    @Autowired
    AttributeMappingRepository attributeMappingRepository;
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    BeanMapper beanMapper;

    @GetMapping("/api/collection/all")
    public List<Collection> findAllCollections() {
        return (List<Collection>) collectionRepository.findAll();
    }


    @ApiOperation(value = "find Availbe Attr by location level")
    @GetMapping("/api/collections")
    public List<CollectionsDTO> findAvailbeAttr(@RequestParam(value = "level") String level) {
        List<Integer> ids = null;
        switch (level.toLowerCase()) {
            case "state":
                ids = collectionRepository.findStateAttrIds();
                break;
            case "county":
                ids = collectionRepository.findCountyAttrIds();
                break;
            case "city":
                ids = collectionRepository.findCityAttrIds();
                break;
            default:
                return null;
        }

        List<AttributeMapping> attributeMappings = (List)attributeMappingRepository.findAllById(ids);
        Map<Integer, List<AttributeMapping>> map = attributeMappings.stream()
                .collect(Collectors.groupingBy(AttributeMapping::getCollection_id));
        List<CollectionsDTO> collectionsDTOS = new ArrayList<>();
        for (Map.Entry<Integer, List<AttributeMapping>> entry : map.entrySet()) {
            Optional<Collection> optional = collectionRepository.findById(entry.getKey());
            if (!optional.isPresent()) {
                continue;
            }
            CollectionsDTO collectionsDTO = beanMapper.map(optional.get(), CollectionsDTO.class);
            List<AttributeInfoDTO> attributeInfoDTOS = beanMapper.mapList(entry.getValue(), AttributeInfoDTO.class);
            List<Integer> properyIds = entry.getValue().stream()
                    .map(AttributeMapping::getProperty_id)
                    .collect(Collectors.toList());
            List<Property> properties = (List)propertyRepository.findAllById(properyIds);
            collectionsDTO.setProperties(beanMapper.mapList(properties, PropertyDTO.class));
            collectionsDTO.setAttributes(attributeInfoDTOS);
            collectionsDTOS.add(collectionsDTO);
        }
        return collectionsDTOS;

    }
    @ApiOperation(value = "find time-range for given location level and attr id")
    @GetMapping("/api/time_range")
    public List<CollectionTimeRangeDTO> findAvailbeAttr(@RequestParam(value = "level") String level, @RequestParam("attributes") List<Integer> attributes) {
        String maxMinVal = null;
        List<CollectionTimeRangeDTO> res = new ArrayList<>();
        level = level.toLowerCase();
        int type = level.equals("state") ? 0 : (level.equals("county") ? 1 : 2);
        List<AttributeMapping> attributeMappings = (List)attributeMappingRepository.findAllById(attributes);
        Map<Integer, List<AttributeMapping>> map = attributeMappings.stream().collect(Collectors.groupingBy(AttributeMapping::getCollection_id));
        for (Map.Entry<Integer, List<AttributeMapping>> entry : map.entrySet()) {
            int id = entry.getKey();
            Optional<Collection> collection = collectionRepository.findById(id);
            if (!collection.isPresent()) {
                continue;
            }
            CollectionTimeRangeDTO collectionTimeRangeDTO = beanMapper.map(collection.get(), CollectionTimeRangeDTO.class);
            List<AttributeMapping> mappings = entry.getValue();
            List<AttrTimeRangeDTO> attrDtos = new ArrayList<>();
            for (AttributeMapping attributeMapping : mappings) {
                AttrTimeRangeDTO attrTimeRangeDTO = beanMapper.map(attributeMapping, AttrTimeRangeDTO.class);
                switch (type) {
                    case 0:
                        maxMinVal = collectionRepository.findStateTimeRangeByAttrId(attributeMapping.getId());
                        break;
                    case 1:
                        maxMinVal = collectionRepository.findCountyTimeRangeByAttrId(attributeMapping.getId());
                        break;
                    case 2:
                        maxMinVal = collectionRepository.findCityTimeRangeByAttrId(attributeMapping.getId());
                        break;
                    default:
                        return null;
                }
                System.out.println(maxMinVal);
                Optional<Property> property = propertyRepository.findById(id);
                if (!property.isPresent()) {
                    continue;
                }
                String[] strings = maxMinVal.split(",");
                attrTimeRangeDTO.setStartYear(strings[1]);
                attrTimeRangeDTO.setEndYear(strings[0]);
                attrTimeRangeDTO.setPropertyName(property.get().getName());
                attrTimeRangeDTO.setPropertyId(property.get().getId());
                attrDtos.add(attrTimeRangeDTO);
            }
            collectionTimeRangeDTO.setAttributes(attrDtos);
            res.add(collectionTimeRangeDTO);
        }
        return res;
    }
}
