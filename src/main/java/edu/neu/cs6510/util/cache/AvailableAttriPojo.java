package edu.neu.cs6510.util.cache;

import java.util.Objects;

public class AvailableAttriPojo {
    private String level;
    private Integer year;
    private Integer id;

    public AvailableAttriPojo(String level, Integer year, Integer id) {
        this.level = level;
        this.year = year;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableAttriPojo that = (AvailableAttriPojo) o;
        return Objects.equals(level, that.level) &&
                Objects.equals(year, that.year) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, year, id);
    }

    @Override
    public String toString() {
        return "AvailableAttriPojo{" +
                "level='" + level + '\'' +
                ", year=" + year +
                ", id=" + id +
                '}';
    }
}
