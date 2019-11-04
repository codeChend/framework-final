package com.startdt.modules.login.intercept;

import com.startdt.modules.login.pojo.UserInfoCache;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/8/28 下午3:42
 * @Modified By:
 */
public class CurrentUser {
    private static ThreadLocal<UserInfoCache> threadLocal = new ThreadLocal<>();

    public static void set(UserInfoCache info){threadLocal.set(info);}

    public static Integer getUserId(){
        UserInfoCache info = threadLocal.get();
        if(null == info){
            return null;
        }
        return info.getId();
    }

    public static String getUserName(){
        UserInfoCache info = threadLocal.get();
        if(null == info){
            return null;
        }
        return info.getUserName();
    }

    public static String getUserToken(){
        UserInfoCache infoCache = threadLocal.get();
        if(null == infoCache){
            return null;
        }
        return infoCache.getToken();
    }

    public static UserInfoCache getUserInfo(){ return threadLocal.get(); }

    public static void remove() { threadLocal.remove();}
}
