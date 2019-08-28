package com.startdt.modules.login.intercept;

import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/28 下午3:42
 * @Modified By:
 */
public class CurrentUser {
    private static ThreadLocal<TbUserInfo> threadLocal = new ThreadLocal<>();

    public static void set(TbUserInfo info){threadLocal.set(info);}

    public static Integer getUserId(){
        TbUserInfo info = threadLocal.get();
        if(null == info){
            return null;
        }
        return info.getId();
    }

    public static String getUserName(){
        TbUserInfo info = threadLocal.get();
        if(null == info){
            return null;
        }
        return info.getUserName();
    }

    public static TbUserInfo getUserInfo(){ return threadLocal.get(); }

    public static void remove() { threadLocal.remove();}
}
