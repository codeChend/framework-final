package com.startdt.modules.common.utils.page;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.BeanConverter;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/18 下午3:42
 * @Modified By:
 */
public class PageUtil{

    public static <T> Page<T> convertPage(PageInfo pageInfo,Class<T> clz){
        Page<T> page = new Page<>();
        page.setCurrentPage(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotalCount(pageInfo.getTotal());
        page.setTotalPage(pageInfo.getPages());

        page.setDataList(BeanConverter.mapList(pageInfo.getList(),clz));

        return page;
    }


}
