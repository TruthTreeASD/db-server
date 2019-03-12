package edu.neu.cs6510.dto;
/**
 * AttrTimeRangeDTO represents a date-time object.
 * This date-time object represents a part of AttributeMapping information and has:
 * 1. attribute_name    : name of the attribute
 * 2. attribute_id      : id of the attribute
 * 3. startYear         : start year of the range for this attribute
 * 4. endYear           : end year of the range for this attribute
 * 5. property_name     : name of property for this attribute
 * 6. property_id       : id of property for this attribute
 */
public class AttrTimeRangeDTO {
    private String attribute_name;
    private Integer attribute_id;
    private String startYear;
    private String endYear;
    private String property_name;
    private int property_id;

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public Integer getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(Integer attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }
}
