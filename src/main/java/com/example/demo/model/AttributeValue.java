package com.example.demo.model;

public class AttributeValue {
    private double val;
    private String attributeName;
    private Integer attributeId;
    private int propertyId;
    private int collectionId;

    public AttributeValue(double val, String attributeName, Integer attributeId, int propertyId, int collectionId) {
        this.val = val;
        this.attributeName = attributeName;
        this.attributeId = attributeId;
        this.propertyId = propertyId;
        this.collectionId = collectionId;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }



    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }
}
