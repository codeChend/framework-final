package com.startdt.modules.user;

import com.startdt.modules.user.service.UserServiceImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

//@Import(ContentImportSelector.class)
//@Import(SimpleContentConfiguration.class)
//@Import(CoreContentConfiguration.class)
@Import(UserServiceImpl.class)
public @interface EnableContentService {

//    String policy() default "core";

}
