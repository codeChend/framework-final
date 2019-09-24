package com.startdt.modules.role.controller;

import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.enums.PrincipalTypeEnum;
import com.startdt.modules.common.utils.enums.ResourceTypeEnum;
import com.startdt.modules.common.utils.page.PageInfo;
import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.result.DataInfo;
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
@RequestMapping("/starter")
@Api(value = "后台-用户授权管理", tags = "后台-用户授权管理")
public class GrantPermissionController {

    @Autowired
    private IGrantPermissionService grantPermissionService;

    @GetMapping("/v1/menuPermission/{userId}")
    @ApiOperation(value = "通过userId分层获取所有菜单权限")
    public Result<DataInfo<List<PermissionNodeDTO>>> getMenuPermission(@PathVariable("userId") Integer userId) {

        return Result.ofSuccess(DataInfo.resultToData(grantPermissionService.getMenuPermission(userId)));
    }

    @GetMapping("/v1/urlPermission/{userId}")
    @ApiOperation(value =  "通过userId获取所有的url权限集")
    public Result<DataInfo<List<String>>> getUrlPermission(@PathVariable("userId") Integer userId) {

        return Result.ofSuccess(DataInfo.resultToData(grantPermissionService.getUrlPermission(userId)));
    }

}
