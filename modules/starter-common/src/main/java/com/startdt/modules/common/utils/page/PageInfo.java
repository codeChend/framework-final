package com.startdt.modules.common.utils.page;

import java.io.Serializable;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/30 下午3:11
 * @Modified By:
 */
public class PageInfo implements Serializable{
    private Integer currentPage;
    private Integer totalPage;
    private Integer pageSize;
    private Long totalCount;

    public PageInfo() {
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageInfo{currentPage=" + this.currentPage + ", totalPage=" + this.totalPage + ", pageSize=" + this.pageSize + ", totalCount=" + this.totalCount + '}';
    }
}
