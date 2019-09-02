package com.startdt.modules.role.controller;

import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;
import com.startdt.modules.role.dal.pojo.dto.RolePermissionDTO;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/30 上午10:01
 * @Modified By:
 */
@RestController
@RequestMapping("/starter/role")
@Api(value = "后台-角色权限管理", tags = "后台-角色权限管理")
public class RolePermissionController {

    @Autowired
    private IRolePermissionInfoService rolePermissionInfoService;


    @PostMapping("/addRolePermission")
    @ApiOperation(value = "添加用户")
    @Transactional(rollbackFor = Exception.class)
    public Result<RolePermissionInfo> registerUser(@RequestBody @Valid RolePermissionDTO rolePermissionDTO) {

        return rolePermissionInfoService.insertRolePermission(rolePermissionDTO);
    }



}
