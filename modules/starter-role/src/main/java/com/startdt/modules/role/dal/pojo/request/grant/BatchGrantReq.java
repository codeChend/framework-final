package com.startdt.modules.role.dal.pojo.request.grant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author  : chendong
 * @date : Create in 2019/11/4 下午7:22
 */
@ApiModel(value = "批量赋予角色请求参数")
@Data
public class BatchGrantReq{

    @ApiModelProperty(value = "用户id",name = "userId",example = "1")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @ApiModelProperty(value = "角色list",name = "roleIds")
    @NotNull
    private List<RoleIdsReq> roleIds;

}
