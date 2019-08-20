package com.startdt.framework.examples;

import com.startdt.modules.user.EnableContentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author hourui 2017/10/10 16:48
 */
@SpringBootApplication
//@ComponentScan(basePackages = "com.nosuchfield")
//演示开启自定义注解的能力
//@EnableContentService()
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
