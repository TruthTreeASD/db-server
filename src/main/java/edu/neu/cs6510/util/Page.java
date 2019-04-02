package edu.neu.cs6510.util;

import java.util.List;

public class Page<T> {
    private List<T> data;
    private Long total;
    private Long totalPage;
    private Integer currentPage;
    private Integer pageSize;

    public Page(List<T> data, Long total, Integer currentPage, Integer pageSize) {
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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
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
