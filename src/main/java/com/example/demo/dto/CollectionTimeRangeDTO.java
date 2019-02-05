package com.example.demo.dto;

import java.util.List;

public class CollectionTimeRangeDTO {
    private String name;
    private Integer collectionId;
    private List<AttrTimeRangeDTO> attributes;

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

    public List<AttrTimeRangeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttrTimeRangeDTO> attributes) {
        this.attributes = attributes;
    }
}
