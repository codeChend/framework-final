package com.startdt.modules.user;

import com.startdt.modules.user.config.UserStarterConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(UserStarterConfiguration.class)
public @interface EnableUserStarter {


}
