package com.startdt.modules.common.utils;

import com.startdt.modules.common.pojo.ModulesDO;
import com.startdt.modules.common.pojo.ModulesTree;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Author: weilong
 * @Description:    加载菜单到缓存
 * @Date: Create in 2019/8/29 下午8:01
 * @Modified By:
 */
public class ModulesCache {

    private static final Map<String,ModulesDO> map = new ConcurrentHashMap<>();

    private static final Set<ModulesTree> treeSet = new ConcurrentSkipListSet<>();

    public static ModulesDO getModulesCacheByCode(String code){
        return map.get(code);
    }

    public static void saveModulesCache(ModulesDO modulesDO){
        map.put(modulesDO.getCode(),modulesDO);
    }

    public static void addModulesTree(ModulesTree modulesTree){
        treeSet.add(modulesTree);
    }

    public static Set<ModulesTree> getModulesTree(){
        return treeSet;
    }
}
