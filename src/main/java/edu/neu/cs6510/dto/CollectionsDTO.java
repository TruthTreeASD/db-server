package edu.neu.cs6510.dto;

import java.util.List;
/**
 * CollectionsDTO represents a date-time object.
 * This date-time object represents a part of Collection class information and has:
 * 1. name              : name of the collection
 * 2. collection_id     : id of the collection
 * 3. properties        : list of property date-time objects
 * 4. attributes        : list of attribute information date-time objects
 */
public class CollectionsDTO {
    private String name;
    private Integer collection_id;
    private List<PropertyDTO> properties;
    private List<AttributeInfoDTO> attributes;

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

    public List<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDTO> properties) {
        this.properties = properties;
    }

    public List<AttributeInfoDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeInfoDTO> attributes) {
        this.attributes = attributes;
    }
}
