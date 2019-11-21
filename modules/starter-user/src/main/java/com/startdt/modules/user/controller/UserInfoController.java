package com.startdt.modules.user.controller;

import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.DataInfo;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.dal.pojo.request.ModifyUserReq;
import com.startdt.modules.user.dal.pojo.request.UpdatePwdReq;
import com.startdt.modules.user.dal.pojo.request.UserInfoReq;
import com.startdt.modules.user.dal.pojo.vo.UserDetailVO;
import com.startdt.modules.user.service.ITbUserInfoService;
import com.startdt.modules.user.service.encode.PasswordEncode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/30 上午10:01
 * @Modified By:
 */
@RestController
@RequestMapping("/api/starter")
@Api(value = "后台-用户管理", tags = "后台-用户管理")
public class UserInfoController {

    @Autowired
    private ITbUserInfoService userInfoService;

    @Autowired
    private PasswordEncode passwordEncode;

    @PostMapping("/v1/users")
    @ApiOperation(value = "添加用户")
    @Transactional(rollbackFor = Exception.class)
    public Result<DataInfo<TbUserInfo>> registerUser(@RequestBody @Valid UserInfoReq userInfoReq) {
        TbUserInfo userInfo = BeanConverter.convert(userInfoReq, TbUserInfo.class);
        return Result.ofSuccess(DataInfo.resultToData(userInfoService.insertUser(userInfo)));
    }

    @PatchMapping("/v1/users")
    @ApiOperation(value = "编辑用户")
    @Transactional(rollbackFor = Exception.class)
    public Result<DataInfo<TbUserInfo>> updateUser(@RequestBody ModifyUserReq registerUserReq) {
        TbUserInfo userInfo = new TbUserInfo();
        userInfo.setId(registerUserReq.getId());
        userInfo.setPassword(passwordEncode.encode(registerUserReq.getPassword()));
        userInfo.setNickName(registerUserReq.getNickName());
        userInfo.setNote(registerUserReq.getNote());
        userInfo.setEmail(registerUserReq.getEmail());
        userInfo.setPhone(registerUserReq.getPhone());

        return Result.ofSuccess(DataInfo.resultToData(userInfoService.modifyUser(userInfo)));
    }

    @GetMapping("/v1/users/{id}")
    @ApiOperation(value = "获取用户详情")
    public Result<DataInfo<UserDetailVO>> getDetail(@PathVariable("id") Integer id) {
        TbUserInfo userInfoResult = userInfoService.getUserById(id);

        UserDetailVO result = BeanConverter.convert(userInfoResult, UserDetailVO.class);

        return Result.ofSuccess(DataInfo.resultToData(result));
    }

    @DeleteMapping("/v1/users/{id}")
    @ApiOperation(value = "删除用户")
    public Result deleteUser(@PathVariable("id") Integer userId) {
        int delete = userInfoService.disableUser(userId);
        if(delete<1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }
        return Result.ofSuccess();
    }

    @GetMapping("/v1/users")
    @ApiOperation(value = "分页获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int", paramType = "query", example = "20")
    })
    public Result<PageResult<UserDetailVO>> pageUser(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {

        return Result.ofSuccess(userInfoService.selectByExamplePaging(null,pageNum,pageSize));
    }

    
}
