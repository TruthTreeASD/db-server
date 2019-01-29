package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "gov_fin_property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
}
