package com.startdt.modules.user.config;

import com.alibaba.fastjson.JSONObject;
import com.startdt.modules.common.pojo.ModulesTree;
import com.startdt.modules.common.utils.ModulesCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/3 下午4:48
 * @Modified By:
 */
@Configuration
public class ReadModulesJsonConfig implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        //加载菜单静态文件
        InputStream inputStream = this.getClass().getResourceAsStream("/META-INF/starter-user-modules.json");
        ModulesTree modulesTree = JSONObject.parseObject(inputStream,ModulesTree.class);
        if(modulesTree!=null){
            ModulesCache.addModulesTree("user",modulesTree);
        }
    }
}
