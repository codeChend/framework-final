package com.startdt.modules.role.dal.pojo.request.permission;

import com.startdt.modules.role.dal.pojo.dto.UrlMethodDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/26 下午2:51
 * @Modified By:
 */
@ApiModel(value = "权限保存请求参数")
public class PermissionNodeReq{
    /** 权限code **/
    @ApiModelProperty(value = "父类权限code",name = "parentCode")
    private String parentCode;

    /** 权限名称 **/
    @ApiModelProperty(value = "权限名称",name = "name")
    @NotEmpty(message = "权限名称不能为空")
    private String name;

    /** 权限值 **/
    @ApiModelProperty(value = "权限值，全局唯一且不能为空",name = "value")
    @NotEmpty(message = "权限值不能为空")
    private String value;

    @ApiModelProperty(value = "权限url与请求方法",name = "urlMethod")
    private UrlMethodDTO urlMethod;

    /** 类型，0 菜单，1 按钮 **/
    @ApiModelProperty(value = "类型，0 菜单，1 按钮，2 资源 ",name = "type",example = "0")
    private Byte type;

    /** 图标 **/
    @ApiModelProperty(value = "图标",name = "icon")
    private String icon;

    @ApiModelProperty(value = "权限子类集合",name = "permissionNodeSon",dataType = "List")
    private List<PermissionNodeReq> permissionNodeSon;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<PermissionNodeReq> getPermissionNodeSon() {
        return permissionNodeSon;
    }

    public void setPermissionNodeSon(List<PermissionNodeReq> permissionNodeSon) {
        this.permissionNodeSon = permissionNodeSon;
    }

    public UrlMethodDTO getUrlMethod() {
        return urlMethod;
    }

    public void setUrlMethod(UrlMethodDTO urlMethod) {
        this.urlMethod = urlMethod;
    }
}
