package com.startdt.modules.user.controller;

import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfoExample;
import com.startdt.modules.user.dal.pojo.request.ModifyUserReq;
import com.startdt.modules.user.dal.pojo.request.UpdatePwdReq;
import com.startdt.modules.user.dal.pojo.vo.UserDetailVO;
import com.startdt.modules.user.service.ITbUserInfoService;
import com.startdt.modules.user.service.encode.PasswordEncode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/30 上午10:01
 * @Modified By:
 */
@RestController
@RequestMapping("/starter/user")
@Api(value = "后台-用户管理", tags = "后台-用户管理")
public class UserInfoController {

    @Autowired
    private ITbUserInfoService userInfoService;

    @Autowired
    private PasswordEncode passwordEncode;

    @PostMapping("/register")
    @ApiOperation(value = "添加用户")
    @Transactional(rollbackFor = Exception.class)
    public Result<TbUserInfo> registerUser(@RequestBody @Valid ModifyUserReq modifyUserReq) {
        TbUserInfo userInfo = BeanConverter.convert(modifyUserReq, TbUserInfo.class);

        return userInfoService.insertUser(userInfo);
    }

    @PostMapping("/update")
    @ApiOperation(value = "编辑用户")
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> updateUser(@RequestBody ModifyUserReq registerUserReq) {
        TbUserInfo userInfo = new TbUserInfo();
        userInfo.setId(registerUserReq.getId());
        userInfo.setPassword(passwordEncode.encode(registerUserReq.getPassword()));
        userInfo.setNickName(registerUserReq.getNickName());
        userInfo.setNote(registerUserReq.getNote());
        userInfo.setEmail(registerUserReq.getEmail());
        userInfo.setPhone(registerUserReq.getPhone());
        return userInfoService.modifyUser(userInfo);
    }

    @GetMapping("/get")
    @ApiOperation(value = "获取用户详情")
    public Result<UserDetailVO> getDetail(@RequestParam("id") Integer id) {
        Result<TbUserInfo> userInfoResult = userInfoService.getUserById(id);
        if(!userInfoResult.isSuccess()) {
            return Result.ofErrorT(userInfoResult.getCode(),userInfoResult.getMessage());
        }
        UserDetailVO result = BeanConverter.convert(userInfoResult.getValue(), UserDetailVO.class);

        return Result.ofSuccess(result);
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除用户")
    public Result<Integer> deleteUser(@RequestParam("id") Integer userId) {
        return userInfoService.disableUser(userId);
    }

    @PostMapping("/updatePwd")
    @ApiOperation(value = "修改用户密码")
    public Result<Integer> updatePwd(@Valid @RequestBody UpdatePwdReq updatePwdReq) {
        return userInfoService.editPwd(updatePwdReq.getId(), updatePwdReq.getOldPwd(), updatePwdReq.getNewPwd(), updatePwdReq.getConfirmNewPwd());
    }

    @GetMapping("/list/page")
    @ApiOperation(value = "分页获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", dataType = "int", paramType = "query", example = "20")
    })
    public Result<Page<UserDetailVO>> pageUser(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        TbUserInfoExample example = new TbUserInfoExample();
        example.or().andStatusEqualTo((byte)1);
        return Result.ofSuccess(userInfoService.selectByExamplePaging(example,pageNum,pageSize));
    }
}
