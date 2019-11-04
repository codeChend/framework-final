package com.startdt.modules.role.dal.pojo.request.grant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author  : chendong
 * @date : Create in 2019/11/4 下午7:21
 */
@Data
public class GrantBaseReq {
    @ApiModelProperty(value = "用户id",name = "userId",example = "1")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

}
