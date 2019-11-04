package com.startdt.modules.role.dal.pojo.request.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/10 下午2:48
 * @Modified By:
 */
@ApiModel(value = "资源排序请求入参")
public class SortPermissionReq {

    @ApiModelProperty(value = "权限code列表",name = "codes")
    private List<String> codes;

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }
}
