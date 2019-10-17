package com.startdt.modules.role.dal.pojo.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午3:55
 * @Modified By:
 */
@ApiModel(value =  "添加角色请求实体")
@Data
public class SaveRoleInfoReq {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称",name = "roleName")
    @NotEmpty(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "平台code",name = "platformCode")
    private String platformCode;

    /**
     * 描述
     */
    @ApiModelProperty(value = "角色描述",name = "note")
    private String note;
}
