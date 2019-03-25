package edu.neu.cs6510.model;

import javax.persistence.*;

/**
 * Class Collection represents the 'gov_fin_collection' table as Collection object
 * Its attributes are:
 * 1. id    : unique id of a collection
 * 2. name  : name of the collection
 */
@Entity
@Table(name = "gov_fin_collection")
public class Collection {
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
        return "Collection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
