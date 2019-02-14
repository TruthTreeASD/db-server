package edu.neu.cs6510.dto;

import java.util.List;

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
