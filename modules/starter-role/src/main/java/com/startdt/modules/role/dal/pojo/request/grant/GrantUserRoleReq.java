package com.startdt.modules.role.dal.pojo.request.grant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午5:37
 * @Modified By:
 */
@ApiModel(value = "用户授予角色请求实体")
@Data
public class GrantUserRoleReq {

    @ApiModelProperty(value = "用户id",name = "userId",example = "1")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @ApiModelProperty(value = "角色code",name = "roleCode",example = "2")
    @NotNull(message = "角色code不能为空")
    private Integer roleCode;

    @ApiModelProperty(value = "空间code",name = "spaceCode")
    private String spaceCode;

    @ApiModelProperty(value = "说明",name = "note")
    private String note;
}
