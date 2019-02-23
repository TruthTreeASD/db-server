package edu.neu.cs6510.util;

import java.util.List;

public class Page<T> {
    private List<T> data;
    private Integer total;
    private Integer totalPage;
    private Integer currentPage;
    private Integer pageSize;

    public Page(List<T> data, Integer total, Integer currentPage, Integer pageSize) {
        this.data = data;
        this.total = total;
        this.pageSize = pageSize;
        this.totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
