package edu.neu.cs6510.model;

import edu.neu.cs6510.model.primeKey.LookUpPK;

public class AttributeValue {
    private Double value;
    private LookUpPK lookUpPK;
    private String attributeName;
    private Integer attributeId;
    private int propertyId;
    private int collectionId;

    public AttributeValue(Double value,LookUpPK lookUpPK, String attributeName, Integer attributeId, int propertyId, int collectionId) {
        this.value = value;
        this.lookUpPK = lookUpPK;
        this.attributeName = attributeName;
        this.attributeId = attributeId;
        this.propertyId = propertyId;
        this.collectionId = collectionId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LookUpPK getLookUpPK() {
        return lookUpPK;
    }

    public void setLookUpPK(LookUpPK lookUpPK) {
        this.lookUpPK = lookUpPK;
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
