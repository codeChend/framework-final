package com.startdt.modules.role.controller;

import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.enums.PrincipalTypeEnum;
import com.startdt.modules.common.utils.enums.ResourceTypeEnum;
import com.startdt.modules.common.utils.page.PageInfo;
import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import com.startdt.modules.role.dal.pojo.dto.PermissionAccessDTO;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.RoleInfoDTO;
import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;
import com.startdt.modules.role.dal.pojo.request.grant.RolePermissionReq;
import com.startdt.modules.role.service.IGrantPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/10 上午10:35
 * @Modified By:
 */
@RestController
@RequestMapping("/starter/grants")
@Api(value = "后台-授权管理", tags = "后台-授权管理")
public class GrantPermissionController {

    @Autowired
    private IGrantPermissionService grantPermissionService;

    @PostMapping("/v1")
    @ApiOperation(value = "用户赋予某个角色")
    public Result<Integer> grantUserRole(@RequestBody @Valid GrantUserRoleReq permissionNodeDTO) {

        return Result.ofSuccess(grantPermissionService.grantUserRole(permissionNodeDTO));
    }

    @DeleteMapping("/v1")
    @ApiOperation(value = "禁用用户的某个角色")
    public Result<Integer> deleteUserRole(@RequestBody @Valid GrantUserRoleReq permissionNodeDTO) {

        return Result.ofSuccess(grantPermissionService.deleteUserRole(permissionNodeDTO.getUserId(),permissionNodeDTO.getRoleCode()));
    }

    @GetMapping("/v1/getRoleByUserId")
    @ApiOperation(value = "获取用户所有的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name = "userId"),
            @ApiImplicitParam(value = "当前页",name = "currentPage"),
            @ApiImplicitParam(value = "每页大小",name = "pageSize"),
    })
    public Result<PageResult<RoleInfoDTO>> getRoleByUserId(@RequestParam("userId") Integer userId,
                                                           @RequestParam("currentPage") Integer currentPage,
                                                           @RequestParam("pageSize") Integer pageSize) {
        GrantPermissionExample example = new GrantPermissionExample();
        example.or().andPrincipalPartEqualTo(userId.toString()).andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue())
                .andResourcesTypeEqualTo(ResourceTypeEnum.ROLE.getCode().byteValue()).andStatusEqualTo((byte)1);

        return Result.ofSuccess(grantPermissionService.pageRoleByUserId(example,currentPage,pageSize));
    }

    @GetMapping("/v1/getMenuPermission")
    @ApiOperation(value = "通过userId分层获取所有菜单权限")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "用户id",name = "userId")
    )
    public Result<List<PermissionNodeDTO>> getMenuPermission(@RequestParam("userId") Integer userId) {

        return Result.ofSuccess(grantPermissionService.getMenuPermission(userId));
    }

    @GetMapping("/v1/getUrlPermission")
    @ApiOperation(value =  "通过userId获取所有的url权限集")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "用户id",name = "userId")
    )
    public Result<List<String>> getUrlPermission(@RequestParam("userId") Integer userId) {

        return Result.ofSuccess(grantPermissionService.getUrlPermission(userId));
    }

    @GetMapping("/v1/getBusinessPermission")
    @ApiOperation(value = "通过userId获取外部业务权限code集合")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "用户id",name = "userId")
    )
    public Result<List<String>> getBusinessPermission(@RequestParam("userId") Integer userId) {

        return Result.ofSuccess(grantPermissionService.getBusinessPermission(userId));
    }

    @GetMapping("/v1/getRolePermission")
    @ApiOperation(value = "通过角色id获取内部系统权限树集合")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "用户id",name = "userId")
    )
    public Result<List<PermissionAccessDTO>> getRolePermission(@RequestParam("roleId") Integer roleId) {

        return Result.ofSuccess(grantPermissionService.getRolePermission(roleId));
    }

    @PostMapping("/grantRolePermission")
    @ApiOperation(value = "给角色授权")
    public Result grantRolePermission(@RequestBody RolePermissionReq rolePermissionReq){

        return Result.ofSuccess(grantPermissionService.grantRolePermission(rolePermissionReq.getRoleId(),rolePermissionReq.getPermissionCodes()));
    }

    @PostMapping("/releaseRolePermission")
    @ApiOperation(value = "释放角色的某些权限")
    public Result releaseRolePermission(@RequestBody RolePermissionReq rolePermissionReq){

        return Result.ofSuccess(grantPermissionService.releaseRolePermission(rolePermissionReq.getRoleId(),rolePermissionReq.getPermissionCodes()));
    }
}
