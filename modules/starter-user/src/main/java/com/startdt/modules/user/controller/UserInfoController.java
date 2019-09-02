package com.startdt.modules.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.exception.UserException;
import com.startdt.modules.common.utils.page.PageInfo;
import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
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
import javax.websocket.server.PathParam;

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
        Result<TbUserInfo> bizUserInfo = null;
        try{
            bizUserInfo = userInfoService.insertUser(userInfo);
        }catch (DuplicateKeyException exception) {
            throw new UserException(BizResultConstant.USER_NAME_EXIST);
        }

        return Result.ofSuccess(bizUserInfo.getValue());
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
        Result<TbUserInfo> result = userInfoService.modifyUser(userInfo);

        return Result.ofSuccess(result.isSuccess()?1:0);
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

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除用户")
    public Result<Integer> deleteUser(@PathParam("id") Integer userId) {
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
    public Result<PageResult<TbUserInfo>> pageUser(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        QueryWrapper<TbUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        Page<TbUserInfo> page = new Page<>(pageNum,pageSize);
        Page<TbUserInfo> pageResult = (Page<TbUserInfo>) userInfoService.page(page,queryWrapper);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotalCount(pageResult.getTotal());
        pageInfo.setTotalPage((int) pageResult.getPages());
        PageResult<TbUserInfo> pageResult1 = new PageResult<>(pageResult.getRecords(),pageInfo);
        return Result.ofSuccess(pageResult1);
    }


}
