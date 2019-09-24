package com.startdt.modules.role.controller;

import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.DataInfo;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.QueryPermissionDTO;
import com.startdt.modules.role.dal.pojo.request.permission.PermissionReq;
import com.startdt.modules.role.dal.pojo.request.permission.SortPermissionReq;
import com.startdt.modules.role.service.IResourcePermissionService;
import io.swagger.annotations.Api;
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
@RequestMapping("/starter")
@Api(value = "后台-权限管理", tags = "后台-权限管理")
public class PermissionController {

    @Autowired
    private IResourcePermissionService resourcePermissionService;

    @PostMapping("/v1/permissions")
    @ApiOperation(value = "保存新的模块资源权限信息")
    public Result<DataInfo<PermissionNodeDTO>> saveResourcePermission(@RequestBody @Valid PermissionNodeDTO permissionNodeDTO) {

        int save = resourcePermissionService.saveResourcePermission(permissionNodeDTO);
        if(save < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }

        return Result.ofSuccess(DataInfo.resultToData(permissionNodeDTO));
    }

    @PatchMapping("/v1/permissions")
    @ApiOperation(value = "修改模块资源权限信息")
    public Result<DataInfo<PermissionReq>> modifyResourcePermission(@RequestBody @Valid PermissionReq permissionReq) {
        int update = resourcePermissionService.modifyResourcePermission(permissionReq);

        if(update < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }

        return Result.ofSuccess(DataInfo.resultToData(permissionReq));
    }

    @PatchMapping("/v1/permissions/sort")
    @ApiOperation(value =  "修改同等级资源的排序")
    public Result<DataInfo<List<String>>> sortPermission(@RequestBody @Valid SortPermissionReq sortPermissionReq) {
        int update = resourcePermissionService.sortPermission(sortPermissionReq.getCodes());

        if(update < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }

        return Result.ofSuccess(DataInfo.resultToData(sortPermissionReq.getCodes()));
    }

    @DeleteMapping("/v1/permissions/{code}")
    @ApiOperation(value = "会删除所有子节点")
    public Result deletePermission(@PathVariable("code") String permissionCode) {
        int delete = resourcePermissionService.deletePermission(permissionCode);
        if(delete < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }

        return Result.ofSuccess();
    }

    @GetMapping("/v1/permissions")
    @ApiOperation(value = "根据条件查询权限资源列表")
    public Result<DataInfo<List<PermissionNodeDTO>>> permissionNodeSelective(@RequestBody QueryPermissionDTO queryPermissionDTO) {

        return Result.ofSuccess(DataInfo.resultToData(resourcePermissionService.permissionNodeSelective(queryPermissionDTO)));
    }
}
