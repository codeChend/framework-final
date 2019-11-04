package com.startdt.modules.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/4/10.
 * @author  weilong
 */

public class BeanConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanConverter.class);

    /**
     * Bean转换
     *
     * @param source 源对象
     * @param clz    目标对象类型
     * @param <T>    T
     * @return 转换后的对象
     */
    public static <T> T convert(Object source, Class<T> clz) {
        if (source != null) {
            BeanCopier copier = BeanCopier.create(source.getClass(), clz, false);

            T target = null;
            try {
                target = clz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            copier.copy(source, target, null);
                return target;

        }
        return null;
    }

    /**
     * LIST转换
     * @param sources
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<T> mapList(List<?> sources, Class<T> clz) {
//        List<T> targets = Lists.newArrayListWithCapacity(sources.size());
        List<T> targets=new ArrayList<>(sources.size());
        for (Object source : sources) {

            T target = convert(source, clz);
            targets.add(target);
        }
        return targets;
    }
}
