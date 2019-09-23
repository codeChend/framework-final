package com.startdt.modules.user.dal.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * desc
 *
 * @author weilong
 * @Date 2019/7/11
 **/
@ApiModel(value="修改用户请求对象")
public class ModifyUserReq extends UserInfoReq{

    @ApiModelProperty(value = "用户id",name="id",example = "1")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
