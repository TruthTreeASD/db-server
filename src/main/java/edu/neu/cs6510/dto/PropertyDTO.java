package edu.neu.cs6510.dto;

/**
 * PropertyDTO represents a date-time object.
 * This date-time object represents a part of Property class information and has:
 * 1. name          : name of property
 * 2. property_id   : id of property
 */
public class PropertyDTO {

    private String name;
    private Integer property_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProperty_id() {
        return property_id;
    }

    public void setProperty_id(Integer property_id) {
        this.property_id = property_id;
    }
}
