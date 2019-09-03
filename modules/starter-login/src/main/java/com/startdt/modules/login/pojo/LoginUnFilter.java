package com.startdt.modules.login.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/3 下午8:11
 * @Modified By:
 */
@ConditionalOnProperty(prefix = "login.un.filter",matchIfMissing = true)
public class LoginUnFilter {
    @Value("${login.un.filter:}")
    private String unFilter;

    public List<String> unFilterList(){
        String[] filterArray = unFilter.split(",");
        System.out.println("---------------------------"+Arrays.toString(filterArray));
        return Arrays.asList(filterArray);
    }
}
