package com.startdt.modules.common;

import com.startdt.modules.common.config.Swagger2Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(value = {Swagger2Configuration.class})
public @interface EnableStarterSwagger {

}
