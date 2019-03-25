package edu.neu.cs6510.services;

import edu.neu.cs6510.dto.*;
import edu.neu.cs6510.model.AttributeMapping;
import edu.neu.cs6510.model.Property;
import edu.neu.cs6510.repositories.AttributeMappingRepository;
import edu.neu.cs6510.repositories.CollectionRepository;
import edu.neu.cs6510.repositories.PropertyRepository;
import edu.neu.cs6510.util.BeanMapper;
import edu.neu.cs6510.util.cache.AvailableAttriPojo;
import edu.neu.cs6510.util.cache.CacheEnum;
import edu.neu.cs6510.util.cache.CacheService;
import edu.neu.cs6510.util.cache.EHCacheUtils;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    CacheManager cacheManager;

    /**
     * Method that gives all the collectionns.
     * @return collection list in the response
     */
    public ResponseMessage findAllCollections() {
        return Result.success(collectionRepository.findAll());
    }

    /**
     * Method to return attributes by either level, year or id
     * @param level state, city or county
     * @param year 
     * @param id attribute id
     * @return
     */
    @Deprecated
    public ResponseMessage findAvailbeAttr1(String level, Integer year, Integer id) {
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
        List<AttributeMapping> attributeMappings = new ArrayList<>();
        for (Integer attributeId : ids) {
            attributeMappings.add(CacheService.attributeMappingMap.get(attributeId));
        }
        Map<Integer, List<AttributeMapping>> map = attributeMappings.stream()
                .collect(Collectors.groupingBy(AttributeMapping::getCollection_id));
        List<CollectionsDTO> collectionsDTOS = new ArrayList<>();
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
        return Result.success(collectionsDTOS);

    }
    
    /**
     * Method to return time-range for given location and attribute id
     * @param level state, city or county
     * @param attributes list of attributes
     * @return Collections for the location and attribute id
     */
    @Deprecated
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

    /**
     * Method to return time-range for given location and attribute id
     * @param level state, city or county
     * @param attributes list of attributes
     * @return Collections for the location and attribute id
     */
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

    /**
     * Method to find Availbe Attr by collection and property pairs
     * @param collection_ids 
     * @param property_ids
     * @return Matching attributes
     */
    public ResponseMessage findAvailbeAttr(List<Integer> collection_ids, List<Integer> property_ids) {
        List<AttributeMapping> mappings  = new ArrayList<>();
        for (Integer cId : collection_ids) {
            mappings.addAll(attributeMappingRepository.findByCollectionIdAndProperties(cId, property_ids));
        }
        return Result.success(mappings);
    }

    /**
     * Information for collections for the given ids
     * @param ids collection ids
     * @return Collection Information
     */
    public ResponseMessage findCollectionByIds(List<Integer> ids) {
        return Result.success(collectionRepository.findAllById(ids));
    }

    /**
     * Method to find Available Attr by location/ location level/ given year
     * @param level either state, city or county
     * @param year 
     * @param id is the Location id
     * @return available attribute list
     */
    public ResponseMessage findAvailbeAttr(String level, Integer year, Integer id) {
        AvailableAttriPojo availableAttriPojo = new AvailableAttriPojo(level, year, id);
        if (id != null) {
            return Result.success(collectionRepository.findAvailableAttriById(id, year));
        } else if (StringUtils.isNotEmpty(level)){
            int code = level.equalsIgnoreCase("state") ? 0
                    : level.equalsIgnoreCase("county") ? 1 :2;
            if (!EHCacheUtils.isExsit(cacheManager, CacheEnum.AVAILABLE_ATTRIBUTE, availableAttriPojo.toString())) {
                List<Integer> res = collectionRepository.findAvailableAttriByLevel(code, year);
                EHCacheUtils.setCache(cacheManager, CacheEnum.AVAILABLE_ATTRIBUTE, availableAttriPojo.toString(), res);
            }
            return Result.success(EHCacheUtils.getCache(cacheManager, CacheEnum.AVAILABLE_ATTRIBUTE, availableAttriPojo.toString()));
        } else {
            return Result.success(collectionRepository.findAllAvailableAttri(year));
        }
    }

    /**
     * Method to find time-range for given location level and attr id
     * @param level either state, city or county
     * @param attributes
     * @return available attribute list
     */
    public ResponseMessage attriTimeRange(String level, List<Integer> attributes) {
        int code = level.equalsIgnoreCase("state") ? 0
                : level.equalsIgnoreCase("county") ? 1 :2;
        return Result.success(collectionRepository.attriTimeRange(code, attributes));
    }
}
