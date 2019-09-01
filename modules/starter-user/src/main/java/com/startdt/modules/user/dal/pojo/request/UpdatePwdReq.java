package com.startdt.modules.user.dal.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


/**
 * desc
 *
 * @author xutianzhe
 * @Date 2019/7/11
 **/
@ApiModel(value = "用户修改密码请求对象")
public class UpdatePwdReq {

    @NotEmpty(message = "旧密码不能为空")
    @ApiModelProperty(value = "旧密码", name = "旧密码")
    private String oldPwd;

    @NotEmpty(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码", name = "新密码")
    private String newPwd;

    @NotEmpty(message = "确认新密码不能为空")
    @ApiModelProperty(value = "确认新密码", name = "确认新密码")
    private String confirmNewPwd;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",name = "id",example = "1")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfirmNewPwd() {
        return confirmNewPwd;
    }

    public void setConfirmNewPwd(String confirmNewPwd) {
        this.confirmNewPwd = confirmNewPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
