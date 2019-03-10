package edu.neu.cs6510.util.cache;

import java.util.List;

public class ValuePagePojo {
    private List<Integer> attributeId;
    private List<Integer> year;
    private List<Integer> locationId;
    private Integer typeCode;
    private Integer parentId;
    private String orderBy;
    private String order;
    private Integer from;
    private Integer to;
    private Integer pageSize;
    private Integer currentPage;

    public ValuePagePojo(List<Integer> attributeId, List<Integer> year, List<Integer> locationId, Integer typeCode, Integer parentId, String orderBy, String order, Integer from, Integer to, Integer pageSize, Integer currentPage) {
        this.attributeId = attributeId;
        this.year = year;
        this.locationId = locationId;
        this.typeCode = typeCode;
        this.parentId = parentId;
        this.orderBy = orderBy;
        this.order = order;
        this.from = from;
        this.to = to;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public List<Integer> getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(List<Integer> attributeId) {
        this.attributeId = attributeId;
    }

    public List<Integer> getYear() {
        return year;
    }

    public void setYear(List<Integer> year) {
        this.year = year;
    }

    public List<Integer> getLocationId() {
        return locationId;
    }

    public void setLocationId(List<Integer> locationId) {
        this.locationId = locationId;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "ValuePagePojo{" +
                "attributeId=" + attributeId +
                ", year=" + year +
                ", locationId=" + locationId +
                ", typeCode=" + typeCode +
                ", parentId=" + parentId +
                ", orderBy='" + orderBy + '\'' +
                ", order='" + order + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}';
    }
}
