package com.startdt.modules.login.controller;

import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.login.pojo.LoginReq;
import com.startdt.modules.login.pojo.LoginUnFilter;
import com.startdt.modules.login.pojo.UserLoginVO;
import com.startdt.modules.login.service.StarterLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/3 下午7:59
 * @Modified By:
 */
@RestController
@RequestMapping("/starter/login")
@Api(value = "后台-登录", tags = "后台-登录")
public class LoginStarterController {


    @Autowired
    private StarterLoginService loginService;

    @Autowired
    private LoginUnFilter loginUnFilter;


    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    @Transactional(rollbackFor = Exception.class)
    public Result<UserLoginVO> login(@RequestBody @Valid LoginReq loginReq) {

        return Result.ofSuccess(loginService.login(loginReq.getUserName(),loginReq.getPassWord()));
    }

    @GetMapping("/get")
    public Result get(){
        return Result.ofSuccess(loginUnFilter.unFilterList());
    }
}
