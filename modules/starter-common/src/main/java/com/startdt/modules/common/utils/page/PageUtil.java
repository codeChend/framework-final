package com.startdt.modules.common.utils.page;

import com.github.pagehelper.PageInfo;
import com.startdt.modules.common.utils.BeanConverter;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/18 下午3:42
 * @Modified By:
 */
public class PageUtil{

    public static <T> PageResult<T> convertPage(PageInfo pageInfo,Class<T> clz){
        com.startdt.modules.common.utils.page.PageInfo page = new com.startdt.modules.common.utils.page.PageInfo();
        page.setCurrentPage(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotalCount(pageInfo.getTotal());
        page.setTotalPage(pageInfo.getPages());

        List<T> dataList = BeanConverter.mapList(pageInfo.getList(),clz);

        return new PageResult<>(dataList,page);
    }

    public static <T> PageResult<T> convertPage(PageInfo pageInfo){
        com.startdt.modules.common.utils.page.PageInfo page = new com.startdt.modules.common.utils.page.PageInfo();
        page.setCurrentPage(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotalCount(pageInfo.getTotal());
        page.setTotalPage(pageInfo.getPages());

        return new PageResult<>(page);
    }


    public static <T> PageResult<T> convertPage(PageInfo pageInfo,List<T> dataList){
        com.startdt.modules.common.utils.page.PageInfo page = new com.startdt.modules.common.utils.page.PageInfo();
        page.setCurrentPage(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotalCount(pageInfo.getTotal());
        page.setTotalPage(pageInfo.getPages());

        return new PageResult<>(dataList,page);
    }
}
