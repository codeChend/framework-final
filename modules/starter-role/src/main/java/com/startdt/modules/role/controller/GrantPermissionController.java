package com.startdt.modules.role.controller;

import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.enums.PrincipalTypeEnum;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.RoleInfoDTO;
import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;
import com.startdt.modules.role.service.IGrantPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/10 上午10:35
 * @Modified By:
 */
@RestController
@RequestMapping("/starter/grant")
@Api(value = "后台-授权管理", tags = "后台-授权管理")
public class GrantPermissionController {

    @Autowired
    private IGrantPermissionService grantPermissionService;

    @PostMapping("/grantUserRole")
    @ApiOperation(value = "用户赋予某个角色")
    public Result<Integer> grantUserRole(@RequestBody @Valid GrantUserRoleReq permissionNodeDTO) {

        return Result.ofSuccess(grantPermissionService.grantUserRole(permissionNodeDTO));
    }

    @PostMapping("/deleteUserRole")
    @ApiOperation(value = "禁用用户的某个角色")
    public Result<Integer> deleteUserRole(@RequestBody @Valid GrantUserRoleReq permissionNodeDTO) {

        return Result.ofSuccess(grantPermissionService.deletUserRole(permissionNodeDTO.getUserId(),permissionNodeDTO.getRoleCode()));
    }

    @GetMapping("/getRoleByUserId")
    @ApiOperation(value = "获取用户所有的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name = "userId"),
            @ApiImplicitParam(value = "当前页",name = "currentPage"),
            @ApiImplicitParam(value = "每页大小",name = "pageSize"),
    })
    public Result<Page<RoleInfoDTO>> getRoleByUserId(@RequestParam("userId") String userId,
                                                     @RequestParam("currentPage") Integer currentPage,
                                                     @RequestParam("pageSize") Integer pageSize) {
        GrantPermissionExample example = new GrantPermissionExample();
        example.or().andPrincipalPartEqualTo(userId).andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue());
        return Result.ofSuccess(grantPermissionService.getRoleByUserId(example,currentPage,pageSize));
    }
}
