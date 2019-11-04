package com.startdt.modules.login.intercept;

import java.lang.annotation.*;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/8/28 下午3:41
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginStarter {
}
