package edu.neu.cs6510.model;

import javax.persistence.*;

/**
 * Property represents 'gov_fin_property' table as Property object
 * Its attributes are:
 * 1. id    : unique id of a property
 * 2. name  : name of property
 */
@Entity
@Table(name = "gov_fin_property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

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

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
