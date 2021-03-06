package edu.neu.cs6510.dto;

/**
 * CollectionInfoDTO represents a date-time object.
 * This date-time object represents a part of Collection class information and has:
 * 1. attribute_name    : name of the attribute
 * 2. attribute_id      : id of the attribute
 * 3. property_name     : name of property
 * 4. property_id       : id of property
 */
public class CollectionInfoDTO {

    String attribute_name;
    String attribute_id;
    Integer property_id;
    String property_name;

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public String getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(String attribute_id) {
        this.attribute_id = attribute_id;
    }

    public Integer getProperty_id() {
        return property_id;
    }

    public void setProperty_id(Integer property_id) {
        this.property_id = property_id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }
}
