package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "gov_fin_attribute_mapping")
public class AttributeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int collection_id;
    int property_id;
}
