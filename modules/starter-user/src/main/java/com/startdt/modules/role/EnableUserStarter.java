package com.startdt.modules.role;

import com.startdt.modules.role.service.UserStarterConfiguration;
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
//@Import(UserStarterConfiguration.class)
@Import(UserStarterConfiguration.class)
public @interface EnableUserStarter {

//    String policy() default "core";

}
