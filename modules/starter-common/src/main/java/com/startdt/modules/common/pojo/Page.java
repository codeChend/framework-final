package com.startdt.modules.common.pojo;

import java.util.List;

/**
 * 由Mybatis Generator增强版生成，不要手动修改
 * @see <a href="https://github.com/pocketdigi/mybatis-generator">https://github.com/pocketdigi/mybatis-generator</a>
 * @author Exception
 */
public final class Page<T> {
    private Integer currentPage;

    private Integer totalPage;

    private Integer pageSize;

    private Long totalCount;

    private List<T> dataList;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}