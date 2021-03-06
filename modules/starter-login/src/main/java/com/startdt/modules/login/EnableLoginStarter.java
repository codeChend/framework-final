package com.startdt.modules.login;

import com.startdt.modules.login.service.LoginStarterConfiguration;
import com.startdt.modules.user.config.UserStarterConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(value = {UserStarterConfiguration.class,LoginStarterConfiguration.class})
public @interface EnableLoginStarter {

}
