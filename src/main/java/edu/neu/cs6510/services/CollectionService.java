package edu.neu.cs6510.services;

import edu.neu.cs6510.dto.*;
import edu.neu.cs6510.model.AttributeMapping;
import edu.neu.cs6510.model.Property;
import edu.neu.cs6510.repositories.AttributeMappingRepository;
import edu.neu.cs6510.repositories.CollectionRepository;
import edu.neu.cs6510.repositories.PropertyRepository;
import edu.neu.cs6510.util.BeanMapper;
import edu.neu.cs6510.util.cache.CacheService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CollectionService {

    @Autowired
    AttributeMappingRepository attributeMappingRepository;
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    BeanMapper beanMapper;


    public ResponseMessage findAllCollections() {
        return Result.success(collectionRepository.findAll());
    }

    public ResponseMessage findAvailbeAttr(String level, Integer year, Integer id) {
        List<Integer> ids = null;
        long start = System.currentTimeMillis();
        if ((level == null || level.isEmpty()) && id == null && year == null) {
            ids = ((List<AttributeMapping>)attributeMappingRepository.findAll()).stream().map(AttributeMapping::getId).collect(Collectors.toList());
        } else if (id == null){
            switch (level.toLowerCase()) {
                case "state":
                    ids = (year == null) ? collectionRepository.findStateAttrIds() : collectionRepository.findStateAttrIds(year);
                    break;
                case "county":
                    ids = (year == null) ?collectionRepository.findCountyAttrIds(): collectionRepository.findCountyAttrIds(year);
                    break;
                case "city":
                    ids = (year == null) ?collectionRepository.findCityAttrIds(): collectionRepository.findCityAttrIds(year);
                    break;
                default:
                    ids = (year == null) ? collectionRepository.findAllAttrids(): collectionRepository.findAllAttrids(year);
            }
        } else {
            ids =(year == null) ? collectionRepository.findAttriIDsByLocId(id) : collectionRepository.findAttriIDsByLocId(id, year);
        }
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        List<AttributeMapping> attributeMappings = new ArrayList<>();
        for (Integer attributeId : ids) {
            attributeMappings.add(CacheService.attributeMappingMap.get(attributeId));
        }
        Map<Integer, List<AttributeMapping>> map = attributeMappings.stream()
                .collect(Collectors.groupingBy(AttributeMapping::getCollection_id));
        List<CollectionsDTO> collectionsDTOS = new ArrayList<>();
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        for (Map.Entry<Integer, List<AttributeMapping>> entry : map.entrySet()) {
            CollectionsDTO collectionsDTO = beanMapper.map(CacheService.collectionMap.get(entry.getKey()), CollectionsDTO.class);
            List<AttributeInfoDTO> attributeInfoDTOS = beanMapper.mapList(entry.getValue(), AttributeInfoDTO.class);
            List<Integer> propertyIds = entry.getValue().stream()
                    .map(AttributeMapping::getProperty_id)
                    .collect(Collectors.toList());
            List<Property> properties = new ArrayList<>();
            for (Integer propertyId : propertyIds) {
                properties.add(CacheService.propertyMap.get(propertyId));
            }
            collectionsDTO.setProperties(beanMapper.mapList(properties, PropertyDTO.class));
            collectionsDTO.setAttributes(attributeInfoDTOS);
            collectionsDTOS.add(collectionsDTO);
        }
        System.out.println(System.currentTimeMillis() - start);
        return Result.success(collectionsDTOS);

    }
    public ResponseMessage<List<CollectionTimeRangeDTO>> findAvailbeAttr(String level, List<Integer> attributes) {
        String maxMinVal = null;
        List<CollectionTimeRangeDTO> res = new ArrayList<>();
        level = level.toLowerCase();
        int type = level.equals("state") ? 0 : (level.equals("county") ? 1 : 2);
        List<AttributeMapping> attributeMappings = (List)attributeMappingRepository.findAllById(attributes);
        Map<Integer, List<AttributeMapping>> map = attributeMappings.stream().collect(Collectors.groupingBy(AttributeMapping::getCollection_id));
        for (Map.Entry<Integer, List<AttributeMapping>> entry : map.entrySet()) {
            int id = entry.getKey();
            CollectionTimeRangeDTO collectionTimeRangeDTO = beanMapper.map(CacheService.attributeMappingMap.get(id), CollectionTimeRangeDTO.class);
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
                }
                System.out.println(maxMinVal);
                Property property = CacheService.propertyMap.get(attributeMapping.getProperty_id());
                String[] strings = maxMinVal.split(",");
                attrTimeRangeDTO.setStartYear(strings[1]);
                attrTimeRangeDTO.setEndYear(strings[0]);
                attrTimeRangeDTO.setProperty_name(property.getName());
                attrTimeRangeDTO.setProperty_id(property.getId());
                attrDtos.add(attrTimeRangeDTO);
            }
            collectionTimeRangeDTO.setAttributes(attrDtos);
            res.add(collectionTimeRangeDTO);
        }
        return Result.success(res);
    }

    public ResponseMessage findAvailbeAttr(Integer id) {
        List<AttributeMapping> attributeMappings = attributeMappingRepository.findByCollectionId(id);
        List<CollectionInfoDTO> collectionsDTOS = new ArrayList<>();
        for (AttributeMapping mapping : attributeMappings) {
            CollectionInfoDTO collectionInfoDTO = beanMapper.map(mapping, CollectionInfoDTO.class);
            collectionInfoDTO.setProperty_name(propertyRepository.findById(mapping.getProperty_id()).get().getName());
            collectionsDTOS.add(collectionInfoDTO);
        }
        return Result.success(collectionsDTOS);
    }

    public ResponseMessage findAvailbeAttr(List<Integer> collection_ids, List<Integer> property_ids) {
        List<AttributeMapping> mappings  = new ArrayList<>();
        for (Integer cId : collection_ids) {
            mappings.addAll(attributeMappingRepository.findByCollectionIdAndProperties(cId, property_ids));
        }
        return Result.success(mappings);
    }



    public ResponseMessage findCollectionByIds(List<Integer> ids) {
        return Result.success(collectionRepository.findAllById(ids));
    }

    public ResponseMessage findAvailbeAttr(String level, Integer year, Integer id, String orderBy, String order) {
        String sort = orderBy + " " + order;
        if (id != null) {
            return Result.success(collectionRepository.findAvailableAttriById(id, year, sort));
        } else if (StringUtils.isNotEmpty(level)){
            List<Integer> code = level.equalsIgnoreCase("state") ? Arrays.asList(0)
                    : level.equalsIgnoreCase("county") ? Arrays.asList(1) : Arrays.asList(2,3,4,5,6);
            return Result.success(collectionRepository.findAvailableAttriByLevel(code, year, sort));
        } else {
            return Result.success(collectionRepository.findAllAvailableAttri(year, "year desc"));
        }
    }
}
