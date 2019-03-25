package edu.neu.cs6510.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AttributeMapping class maps to 'gov_fin_attribute_mapping' table as AttributeMapping object
 * Its attributes are:
 * 1. id 			: unique id of attribute
 * 2. name			: name of attribute
 * 3. collection_id	: collection id of a collection to which attribute belongs
 * 4. property_id	: property id of a property to which attribute belongs
 */
@Entity
@Table(name = "gov_fin_attribute_mapping")
public class AttributeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int collection_id;
	int property_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCollection_id() {
		return collection_id;
	}
	public void setCollection_id(int collection_id) {
		this.collection_id = collection_id;
	}
	public int getProperty_id() {
		return property_id;
	}
	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}

	@Override
	public String toString() {
		return "AttributeMapping{" +
				"id=" + id +
				", name='" + name + '\'' +
				", collection_id=" + collection_id +
				", property_id=" + property_id +
				'}';
	}
}
