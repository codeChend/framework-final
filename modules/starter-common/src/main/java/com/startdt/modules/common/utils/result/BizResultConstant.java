package com.startdt.modules.common.utils.result;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/8/27 下午9:05
 * @Modified By:
 */
public class BizResultConstant {
    private int resultCode;
    private String description;

    public BizResultConstant(int resultCode, String description) {
        this.resultCode = resultCode;
        this.description = description;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public String getDescription() {
        return this.description;
    }

    public static final BizResultConstant SYSTEM_NOT_SUPPORT = new BizResultConstant(10000, "系统错误");

    public static final BizResultConstant SERVICE_NOT_SUPPORT = new BizResultConstant(20000, "服务级错误");

    public static final BizResultConstant PARAM_ERROR = new BizResultConstant(40000, "参数错误!");


    public static final BizResultConstant ID_BLANK = new BizResultConstant(50001, "缺少id!");
    public static final BizResultConstant DB_MODIFY_ERROR = new BizResultConstant(50002, "数据库操作失败");
    public static final BizResultConstant PASSWORD_ERROR = new BizResultConstant(50003, "密码不正确");
    public static final BizResultConstant USER_NEW_PASSWORD_DISACCORD = new BizResultConstant(50004, "新密码两次输入不一致");
    public static final BizResultConstant USER_NEW_OLD_ACCORD = new BizResultConstant(50005, "新密码与旧密码不能相同");
    public static final BizResultConstant NO_USER = new BizResultConstant(50006, "该用户不存在");
    public static final BizResultConstant USER_DISABLED = new BizResultConstant(50007, "用户被禁用！");

    public static final BizResultConstant TOKEN_VERIFY = new BizResultConstant(50008, "Token验签失败!");
    public static final BizResultConstant ERROR_USER_SESSION_EXPIRED = new BizResultConstant(50009, "请重新登录");

    public static final BizResultConstant USER_NAME_EXIST = new BizResultConstant(50010, "该用户名已存在");
    public static final BizResultConstant NO_CONTENT_DATA = new BizResultConstant(50011, "数据不存在");
    public static final BizResultConstant ROLE_IS_EXIST = new BizResultConstant(50012, "角色已存在");
    public static final BizResultConstant ROLE_IS_NOT_EXIST = new BizResultConstant(50012, "角色不存在");
    public static final BizResultConstant PERMISSION_IS_NOT_SAME_LEVEL = new BizResultConstant(50013, "权限不同级");
    public static final BizResultConstant PERMISSION_IS_NOT_EXIST = new BizResultConstant(50014, "权限不存在");
    public static final BizResultConstant ROLE_NO_AUTH = new BizResultConstant(50015, "角色未授权");

}


