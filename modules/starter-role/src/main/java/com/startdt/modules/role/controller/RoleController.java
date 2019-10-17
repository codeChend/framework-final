package com.startdt.modules.role.controller;

import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.DataInfo;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfoExample;
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

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/30 上午10:01
 * @Modified By:
 */
@RestController
@RequestMapping("/api/starter")
@Api(value = "后台-角色管理", tags = "后台-角色管理")
public class RoleController {

    @Autowired
    private IRolePermissionInfoService rolePermissionInfoService;


    @PostMapping("/v1/roles")
    @ApiOperation(value = "添加角色")
    public Result<DataInfo<RoleInfoDTO>> registerUser(@RequestBody @Valid SaveRoleInfoReq saveRoleInfoReq) {

        //根据角色名称获取角色信息
        RoleInfoDTO roleInfoDTO = rolePermissionInfoService.getRoleByName(saveRoleInfoReq.getRoleName(),1);
        if(roleInfoDTO != null){
            return Result.ofErrorT(BizResultConstant.ROLE_IS_EXIST);
        }
        RoleInfoDTO infoDTO = rolePermissionInfoService.insertRole(saveRoleInfoReq);

        return Result.ofSuccess(DataInfo.resultToData(infoDTO));
    }

    @DeleteMapping("/v1/roles/{id}")
    @ApiOperation(value =  "删除角色")
    public Result deleteRole(@PathVariable("id") Integer id) {

        //根据角色id获取角色信息
        RolePermissionDTO roleInfoDTO = rolePermissionInfoService.getRoleById(id);
        if(roleInfoDTO == null){
            return Result.ofErrorT(BizResultConstant.ROLE_IS_NOT_EXIST);
        }
        int delete = rolePermissionInfoService.deleteRole(id);

        if(delete < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }
        return Result.ofSuccess();
    }

    @PatchMapping("/v1/roles")
    @ApiOperation(value =  "修改角色")
    public Result<DataInfo<ModifyRoleInfoReq>> editRole(@RequestBody @Validated ModifyRoleInfoReq modifyRoleInfoReq) {

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
        int edit = rolePermissionInfoService.editRole(modifyRoleInfoReq);

        if(edit < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }
        return Result.ofSuccess(DataInfo.resultToData(modifyRoleInfoReq));
    }

    @GetMapping("/v1/roles")
    @ApiOperation(value ="分页获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页", name = "currentPage", example = "1"),
            @ApiImplicitParam(value = "每页大小", name = "pageSize", example = "10"),
            @ApiImplicitParam(value = "平台code可为空", name = "platformCode", example = "10")
    })
    public Result<PageResult<RoleInfoDTO>> listRole(@RequestParam("currentPage") Integer currentPage,
                                                    @RequestParam("pageSize") Integer pageSize,
                                                    @RequestParam(value = "platformCode",required = false) String platformCode) {

        RolePermissionInfoExample example = new RolePermissionInfoExample();
        example.or().andStatusEqualTo((byte)1);


        PageResult<RoleInfoDTO> roleInfoDTOPage = rolePermissionInfoService.pageRole(platformCode,currentPage,pageSize);

        return Result.ofSuccess(roleInfoDTOPage);
    }


}
