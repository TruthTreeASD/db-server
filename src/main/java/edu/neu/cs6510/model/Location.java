package edu.neu.cs6510.model;

import javax.persistence.*;

@Entity
@Table(name = "gov_fin_location_info")
public class Location {
    @Id
    int id;
    String name;
    @Column(name = "parent_id")
    String parent_id;
    @Column(name = "type_code")
    int type_code;
    @Column(name = "fips_code_state")
    Integer fips_code_state;
    @Column(name = "fips_county")
    Integer fips_county;
    @Column(name = "fips_place")
    Integer fips_place;
    Integer fyenddate;

    public Integer getFips_code_state() {
        return fips_code_state;
    }

    public void setFips_code_state(Integer fips_code_state) {
        this.fips_code_state = fips_code_state;
    }

    public Integer getFips_county() {
        return fips_county;
    }

    public void setFips_county(Integer fips_county) {
        this.fips_county = fips_county;
    }

    public Integer getFips_place() {
        return fips_place;
    }

    public void setFips_place(Integer fips_place) {
        this.fips_place = fips_place;
    }

    public Integer getFyenddate() {
        return fyenddate;
    }

    public void setFyenddate(Integer fyenddate) {
        this.fyenddate = fyenddate;
    }

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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public int getType_code() {
        return type_code;
    }

    public void setType_code(int type_code) {
        this.type_code = type_code;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", type_code=" + type_code +
                ", fips_code_state=" + fips_code_state +
                ", fips_county=" + fips_county +
                ", fips_place=" + fips_place +
                ", fyenddate=" + fyenddate +
                '}';
    }
}
