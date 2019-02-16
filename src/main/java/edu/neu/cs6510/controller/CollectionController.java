package edu.neu.cs6510.controller;

import edu.neu.cs6510.dto.*;
import edu.neu.cs6510.model.AttributeMapping;
import edu.neu.cs6510.model.Property;
import edu.neu.cs6510.repositories.AttributeMappingRepository;
import edu.neu.cs6510.repositories.CollectionRepository;
import edu.neu.cs6510.repositories.PropertyRepository;
import edu.neu.cs6510.services.CollectionService;
import edu.neu.cs6510.util.BeanMapper;
import edu.neu.cs6510.util.cache.CacheService;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @GetMapping("/api/collection/all")
    public ResponseMessage findAllCollections() {
        return collectionService.findAllCollections();
    }

    @ApiOperation(value = "find Availbe Attr by location/ location level/ given year")
    @GetMapping("/api/collections")
    public ResponseMessage findAvailbeAttr(@RequestParam(value = "level", required = false) String level,
                                           @RequestParam(value = "year", required = false) Integer year,
                                           @RequestParam(value = "id", required = false) Integer id) {
        return collectionService.findAvailbeAttr(level, year, id);
    }
    @ApiOperation(value = "find time-range for given location level and attr id")
    @GetMapping("/api/time_range")
    public ResponseMessage<List<CollectionTimeRangeDTO>> findAvailbeAttr(@RequestParam(value = "level") String level, @RequestParam("attributes") List<Integer> attributes) {

        return collectionService.findAvailbeAttr(level, attributes);
    }

    @ApiOperation(value = "find Availbe Attr by location level")
    @GetMapping("/api/collectionSet")
    public ResponseMessage findAvailbeAttr(@RequestParam(value = "id") Integer id) {
        return collectionService.findAvailbeAttr(id);
    }

    @ApiOperation(value = "find Availbe Attr by collection and property pairs")
    @GetMapping("/api/queryAttriIdByCombineation")
    public ResponseMessage findAvailbeAttr(@RequestParam(value = "collection_ids") List<Integer> collection_ids,
                                           @RequestParam(value = "property_ids") List<Integer> property_ids) {

        return collectionService.findAvailbeAttr(collection_ids, property_ids);
    }

    @ApiOperation(value = "find collection information by ids")
    @GetMapping("/api/queryCollectionById")
    public ResponseMessage findCollectionByIds(@RequestParam("id") List<Integer> ids) {
        return Result.success(collectionService.findCollectionByIds(ids));
    }

    @ApiOperation(value = "find Availbe Attr by location/ location level/ given year")
    @GetMapping("/api/collections2")
    public ResponseMessage findAvailbeAttr(@RequestParam(value = "level", required = false) String level,
                                           @RequestParam(value = "year", required = false) Integer year,
                                           @RequestParam(value = "id", required = false) Integer id,
                                           @RequestParam(value = "orderBy", defaultValue = "year") String orderBy,
                                           @RequestParam(value = "order", defaultValue = "DESC") String order) {
        return collectionService.findAvailbeAttr(level, year, id, orderBy, order);
    }


}
