package com.startdt.modules.role.controller;

import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.request.permission.PermissionReq;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.QueryPermissionDTO;
import com.startdt.modules.role.dal.pojo.request.permission.SortPermissionReq;
import com.startdt.modules.role.service.IResourcePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/starter/permission")
@Api(value = "后台-权限管理", tags = "后台-权限管理")
public class ResourcePermissionController {

    @Autowired
    private IResourcePermissionService resourcePermissionService;

    @PostMapping("/saveResourcePermission")
    @ApiOperation(value = "保存新的模块资源权限信息")
    public Result<Integer> saveResourcePermission(@RequestBody @Valid PermissionNodeDTO permissionNodeDTO) {

        return Result.ofSuccess(resourcePermissionService.saveResourcePermission(permissionNodeDTO));
    }

    @PostMapping("/modifyResourcePermission")
    @ApiOperation(value = "修改模块资源权限信息")
    public Result<Integer> modifyResourcePermission(@RequestBody @Valid PermissionReq permissionReq) {

        return Result.ofSuccess(resourcePermissionService.modifyResourcePermission(permissionReq));
    }

    @PostMapping("/sortPermission")
    @ApiOperation(value =  "修改同等级资源的排序")
    public Result<Integer> sortPermission(@RequestBody @Valid SortPermissionReq sortPermissionReq) {

        return Result.ofSuccess(resourcePermissionService.sortPermission(sortPermissionReq.getCodes()));
    }

    @GetMapping("/deletePermission")
    @ApiOperation(value = "会删除所有子节点")
    @ApiImplicitParam(value = "权限code",name = "permissionCode")
    public Result<Integer> deletePermission(@RequestParam("permissionCode") String permissionCode) {

        return Result.ofSuccess(resourcePermissionService.deletePermission(permissionCode));
    }

    @GetMapping("/PermissionNodeSelective")
    @ApiOperation(value = "根据条件查询权限资源列表")
    public Result<List<PermissionNodeDTO>> PermissionNodeSelective(@RequestBody QueryPermissionDTO queryPermissionDTO) {

        return Result.ofSuccess(resourcePermissionService.permissionNodeSelective(queryPermissionDTO));
    }
}
