package com.startdt.modules.role.controller;

import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.RoleInfoDTO;
import com.startdt.modules.role.dal.pojo.dto.RolePermissionDTO;
import com.startdt.modules.role.dal.pojo.request.role.ModifyRoleInfoReq;
import com.startdt.modules.role.dal.pojo.request.role.SaveRoleInfoReq;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色")
    public Result<Integer> registerUser(@RequestBody @Valid SaveRoleInfoReq saveRoleInfoReq) {

        //根据角色名称获取角色信息
        RoleInfoDTO roleInfoDTO = rolePermissionInfoService.getRoleByName(saveRoleInfoReq.getRoleName(),1);
        if(roleInfoDTO == null){
            return Result.ofErrorT(BizResultConstant.ROLE_IS_EXIST);
        }
        int save = rolePermissionInfoService.insertRole(saveRoleInfoReq);

        return Result.ofSuccess(save);
    }

    @GetMapping("/deleteRole")
    @ApiOperation(value =  "删除角色")
    public Result<Integer> deleteRole(@RequestParam("id") Integer id) {

        //根据角色id获取角色信息
        RolePermissionDTO roleInfoDTO = rolePermissionInfoService.getRoleById(id);
        if(roleInfoDTO != null){
            return Result.ofErrorT(BizResultConstant.ROLE_IS_NOT_EXIST);
        }
        int delete = rolePermissionInfoService.deleteRole(id);

        return Result.ofSuccess(delete);
    }

    @PostMapping("/editRole")
    @ApiOperation(value =  "修改角色")
    public Result<Integer> editRole(@RequestBody @Validated ModifyRoleInfoReq modifyRoleInfoReq) {

        //根据角色id获取角色信息
        RolePermissionDTO rolePermissionDTO = rolePermissionInfoService.getRoleById(modifyRoleInfoReq.getId());
        if(rolePermissionDTO == null){
            return Result.ofErrorT(BizResultConstant.ROLE_IS_NOT_EXIST);
        }
        boolean isSame = rolePermissionDTO.getRoleName().equals(modifyRoleInfoReq.getRoleName());

        RoleInfoDTO roleInfoDTO = rolePermissionInfoService.getRoleByName(modifyRoleInfoReq.getRoleName(),1);
        if(!isSame && roleInfoDTO!=null){
            return Result.ofErrorT(BizResultConstant.ROLE_IS_EXIST);
        }

        return Result.ofSuccess(rolePermissionInfoService.editRole(modifyRoleInfoReq));
    }

    @GetMapping("/listRole")
    @ApiOperation(value ="分页获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页", name = "currentPage", example = "1"),
            @ApiImplicitParam(value = "每页大小", name = "pageSize", example = "10")
    })
    public Result<Page<RoleInfoDTO>> listRole(@RequestParam("currentPage") Integer currentPage,@RequestParam("pageSize") Integer pageSize) {

        Page<RoleInfoDTO> roleInfoDTOPage = rolePermissionInfoService.listRole(null,currentPage,pageSize);

        return Result.ofSuccess(roleInfoDTOPage);
    }

    @GetMapping("/getMenuPermission")
    @ApiOperation(value = "通过userId分层获取所有菜单权限")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "用户id",name = "userId")
    )
    public Result<List<PermissionNodeDTO>> getMenuPermission(@RequestParam("userId") String userId) {

        return Result.ofSuccess(rolePermissionInfoService.getMenuPermission(userId));
    }

    @GetMapping("/getUrlPermission")
    @ApiOperation(value =  "通过userId获取所有的url权限集")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "用户id",name = "userId")
    )
    public Result<List<String>> getUrlPermission(@RequestParam("userId") String userId) {

        return Result.ofSuccess(rolePermissionInfoService.getUrlPermission(userId));
    }

    @GetMapping("/getBusinessPermission")
    @ApiOperation(value = "通过userId获取外部业务权限code集合")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "用户id",name = "userId")
    )
    public Result<List<String>> getBusinessPermission(@RequestParam("userId") String userId) {

        return Result.ofSuccess(rolePermissionInfoService.getBussinessPermission(userId));
    }

}
