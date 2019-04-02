package edu.neu.cs6510.dto;
/**
 * AttributeValueDTO represents a date-time object.
 * This date-time object represents a part of AttributeMapping information and has:
 * 1. attribute_id  : id of the attribute
 * 2. value         : value of the attribute
 */
public class AttributeValueDTO {
	
	private int attribute_id;
	
	private int value;

	public int getAttribute_id() {
		return attribute_id;
	}

	public void setAttribute_id(int attribute_id) {
		this.attribute_id = attribute_id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}
