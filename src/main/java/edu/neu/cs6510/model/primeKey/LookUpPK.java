package edu.neu.cs6510.model.primeKey;


import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LookUpPK implements Serializable {

    private Integer attribute_mapping_id;
    private Integer location_id;
    private Integer year;

    public Integer getAttribute_mapping_id() {
        return attribute_mapping_id;
    }

    public void setAttribute_mapping_id(Integer attribute_mapping_id) {
        this.attribute_mapping_id = attribute_mapping_id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LookUpPK lookUpPK = (LookUpPK) o;
        return attribute_mapping_id.equals(lookUpPK.attribute_mapping_id) &&
                location_id.equals(lookUpPK.location_id) &&
                year.equals(lookUpPK.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute_mapping_id, location_id, year);
    }

    @Override
    public String toString() {
        return "LookUpPK{" +
                "attribute_mapping_id=" + attribute_mapping_id +
                ", location_id=" + location_id +
                ", year=" + year +
                '}';
    }
}
