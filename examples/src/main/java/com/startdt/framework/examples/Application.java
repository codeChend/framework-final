package com.startdt.framework.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hourui 2017/10/10 16:48
 */
@SpringBootApplication
//@ComponentScan(basePackages = "com.nosuchfield")
//演示开启自定义注解的能力
//@EnableRoleStarter()
public class Application  {

//    @Resource
//    private RunIt runIt;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Override
//    public void run(String... strings) throws Exception {
//        runIt.hello();
//    }
}