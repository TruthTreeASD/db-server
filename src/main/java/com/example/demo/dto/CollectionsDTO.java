package com.example.demo.dto;

import java.util.List;

public class CollectionsDTO {
    private String name;
    private Integer collectionId;
    private List<PropertyDTO> properties;
    private List<AttributeInfoDTO> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
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
