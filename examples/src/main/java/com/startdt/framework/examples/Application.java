package com.startdt.framework.examples;

import com.startdt.modules.common.EnableStarterSwagger;
import com.startdt.modules.login.EnableLoginStarter;
import com.startdt.modules.role.EnableRoleStarter;
import com.startdt.modules.user.EnableUserStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  hourui 2017/10/10 16:48
 */
@SpringBootApplication
//演示开启自定义注解的能力
//@EnableUserStarter
@EnableLoginStarter
@EnableStarterSwagger
@EnableRoleStarter
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
