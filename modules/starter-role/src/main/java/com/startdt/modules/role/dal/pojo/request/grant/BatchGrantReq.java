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
public class BatchGrantReq extends GrantBaseReq{

    @ApiModelProperty(value = "角色list",name = "roleIds")
    @NotNull
    private List<GrantUserRoleReq> roleIds;

}
