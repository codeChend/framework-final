package com.startdt.modules.role.controller;

import com.startdt.modules.common.utils.result.DataInfo;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.UrlMethodDTO;
import com.startdt.modules.role.service.IGrantPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/10 上午10:35
 * @Modified By:
 */
@RestController
@RequestMapping("/api/starter")
@Api(value = "后台-用户授权管理", tags = "后台-用户授权管理")
public class GrantPermissionController {

    @Autowired
    private IGrantPermissionService grantPermissionService;

    @GetMapping("/v1/menuPermission/{userId}")
    @ApiOperation(value = "通过userId分层获取所有菜单权限")
    public Result<DataInfo<List<PermissionNodeDTO>>> getMenuPermission(@PathVariable("userId") String userId,
                                                                       @RequestParam(value = "spaceCode",required = false) List<String> spaceCode) {

        return Result.ofSuccess(DataInfo.resultToData(grantPermissionService.getMenuPermission(userId,spaceCode)));
    }

    @GetMapping("/v1/urlPermission/{userId}")
    @ApiOperation(value =  "通过userId获取所有的url权限集")
    public Result<DataInfo<List<UrlMethodDTO>>> getUrlPermission(@PathVariable("userId") String userId,
                                                                 @RequestParam(value = "spaceCode",required = false) String spaceCode) {

        return Result.ofSuccess(DataInfo.resultToData(grantPermissionService.getUrlPermission(userId,spaceCode)));
    }

    @GetMapping("/v1/functionPermission/{userId}")
    @ApiOperation(value = "通过userId获取所有功能权限集")
    public Result<DataInfo<List<String>>> getFunctionPermission(@PathVariable("userId") String userId,
                                                                @RequestParam(value = "spaceCode",required = false) String spaceCode) {


        return Result.ofSuccess(DataInfo.resultToData(grantPermissionService.getFunctionPermission(userId,spaceCode)));
    }

}
