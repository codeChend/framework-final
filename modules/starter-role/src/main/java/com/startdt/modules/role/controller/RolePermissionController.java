package com.startdt.modules.role.controller;

import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.DataInfo;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.dto.PermissionAccessDTO;
import com.startdt.modules.role.dal.pojo.request.grant.RolePermissionReq;
import com.startdt.modules.role.service.IGrantPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/24 上午10:35
 * @Modified By:
 */
@RestController
@RequestMapping("/starter")
@Api(value = "后台-角色绑定权限", tags = "后台-角色绑定权限")
public class RolePermissionController {

    @Autowired
    private IGrantPermissionService grantPermissionService;

    @GetMapping("/v1/rolePermissions/{roleId}")
    @ApiOperation(value = "通过角色id获取内部系统权限树集合")
    public Result<List<PermissionAccessDTO>> getRolePermission(@PathVariable("roleId") Integer roleId) {

        return Result.ofSuccess(grantPermissionService.getRolePermission(roleId));
    }

    @PostMapping("/v1/rolePermissions")
    @ApiOperation(value = "给角色授权,授予权限如果是子节点权限会附带上父节点的权限")
    public Result<DataInfo<RolePermissionReq>> grantRolePermission(@RequestBody RolePermissionReq rolePermissionReq){
        int grant = grantPermissionService.grantRolePermission(rolePermissionReq.getRoleId(),rolePermissionReq.getPermissionCodes());
        if(grant < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }

        return Result.ofSuccess(DataInfo.resultToData(rolePermissionReq));
    }

    @DeleteMapping("/v1/rolePermissions")
    @ApiOperation(value = "释放角色的某些权限")
    public Result releaseRolePermission(@RequestBody RolePermissionReq rolePermissionReq){
        int release = grantPermissionService.releaseRolePermission(rolePermissionReq.getRoleId(),rolePermissionReq.getPermissionCodes());
        if(release < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }

        return Result.ofSuccess();
    }
}
