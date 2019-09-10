package com.startdt.modules.role;

import com.startdt.modules.role.service.RoleStarterConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/10 下午3:17
 * @Modified By:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(RoleStarterConfiguration.class)
public @interface EnableRoleStarter {

}
