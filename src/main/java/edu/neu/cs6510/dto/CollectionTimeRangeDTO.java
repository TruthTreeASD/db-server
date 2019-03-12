package edu.neu.cs6510.dto;

import java.util.List;
/**
 * CollectionTimeRangeDTO represents a date-time object.
 * This date-time object represents a part of Collection class information and has:
 * 1. name              : name of the collection
 * 2. collection_id     : id of the collection
 * 3. attributes        : list of attribute time-range date-time objects
 */
public class CollectionTimeRangeDTO {
    private String name;
    private Integer collection_id;
    private List<AttrTimeRangeDTO> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(Integer collection_id) {
        this.collection_id = collection_id;
    }

    public List<AttrTimeRangeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttrTimeRangeDTO> attributes) {
        this.attributes = attributes;
    }
}
