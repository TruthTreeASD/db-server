package edu.neu.cs6510.dto;

/**
 * AttributeInfoDTO represents a date-time object.
 * This date-time object represents a part of AttributeMapping information and has:
 * 1. name          : name of the attribute
 * 2. attribute_id  : id of the attribute
 */
public class AttributeInfoDTO {

    private String name;
    private Integer attribute_id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(Integer attribute_id) {
        this.attribute_id = attribute_id;
    }
}
