package com.startdt.modules.common.utils.page;


import java.io.Serializable;
import java.util.List;

/**
 * @author : weilong
 * @Description:
 * @DataUtil: Create in 2019/8/30 下午3:10
 * @Modified By:
 */
public class PageResult<T> implements Serializable{

    private List<T> data;
    private PageInfo pageInfo;

    public PageResult(List<T> data, PageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }

    public PageResult(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return this.pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public String toString() {
        return "PageResult{data=" + this.data + ", pageInfo=" + this.pageInfo + '}';
    }
}
