package com.startdt.modules.role.controller;

import com.startdt.modules.common.utils.enums.PrincipalTypeEnum;
import com.startdt.modules.common.utils.enums.ResourceTypeEnum;
import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.DataInfo;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
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

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/24 上午10:24
 * @Modified By:
 */
@RestController
@RequestMapping("/starter")
@Api(value = "后台-用户绑定角色", tags = "后台-用户绑定角色")
public class UserRoleController {

    @Autowired
    private IGrantPermissionService grantPermissionService;

    @PostMapping("/v1/userRoles")
    @ApiOperation(value = "用户赋予某个角色")
    public Result<DataInfo<GrantUserRoleReq>> grantUserRole(@RequestBody @Valid GrantUserRoleReq permissionNodeDTO) {
        int save = grantPermissionService.grantUserRole(permissionNodeDTO);
        if(save < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }
        return Result.ofSuccess(DataInfo.resultToData(permissionNodeDTO));
    }

    @DeleteMapping("/v1/userRoles")
    @ApiOperation(value = "禁用用户的某个角色")
    public Result deleteUserRole(@RequestBody @Valid GrantUserRoleReq permissionNodeDTO) {
        int delete = grantPermissionService.deleteUserRole(permissionNodeDTO.getUserId(),permissionNodeDTO.getRoleCode());
        if(delete < 1){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }
        return Result.ofSuccess();
    }

    @GetMapping("/v1/userRoles")
    @ApiOperation(value = "分页获取用户所有的角色")
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
}
