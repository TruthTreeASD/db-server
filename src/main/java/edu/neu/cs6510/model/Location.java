package edu.neu.cs6510.model;

import javax.persistence.*;

/**
 * Location class represents 'gov_fin_location_info' table as a Location object
 * Location object attributes are:
 * 1. id                : id of location
 * 2. name              : name of location
 * 3. parent_id         : parent id of location (ex: state Mass will be parent of city Boston)
 * 4. type_code         : type_code defines if it is a state/city/counties etc
 * 5. fips_code_state   : represents fips state code
 * 6. fips_county       : represents fips county code
 * 7. fips_place        : represents fips place code
 * 8. fyenddate         :
 * 9. longitude         : represents the longitude of the location
 * 10. latitude         : represents the latitude of the location
 */
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
    Double longitude;
    Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

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
