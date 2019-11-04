package com.startdt.modules.login.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/3 下午8:11
 * @Modified By:
 */
@ConditionalOnProperty(prefix = "login.un.filter",matchIfMissing = true)
public class LoginUnFilter {
    @Value("${login.un.filter:}")
    private String unFilter;

    private static final String KEY = "login_un_filter";

    private static final Map<String,List<LoginUrlDTO>> LOGIN_MAP = new ConcurrentHashMap<>();

    public List<LoginUrlDTO> unFilterList(){
        if(!CollectionUtils.isEmpty(LOGIN_MAP.get(KEY))){
            return LOGIN_MAP.get(KEY);
        }
        String[] filterArray = unFilter.split(",");
        List<LoginUrlDTO> loginUrlDTOS = new ArrayList<>();
        if(filterArray.length>0){
            for(int i = 0;i <= filterArray.length-1;i++){
                String filter = filterArray[i];
                LoginUrlDTO loginUrlDTO = new LoginUrlDTO();
                if(filter.contains("(")){
                    loginUrlDTO.setMethod(filter.substring(filter.indexOf("(")+1,filter.indexOf(")")));
                    loginUrlDTO.setUrl(filter.substring(0,filter.indexOf("(")));
                }else{
                    loginUrlDTO.setUrl(filter);
                }
                loginUrlDTOS.add(loginUrlDTO);
            }
        }
        LOGIN_MAP.put(KEY,loginUrlDTOS);

        return loginUrlDTOS;
    }
}
