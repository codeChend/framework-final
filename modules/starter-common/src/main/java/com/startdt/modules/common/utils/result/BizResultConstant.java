package com.startdt.modules.common.utils.result;

/**
 * @Author: weilong
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

    public static final BizResultConstant SYSTEM_NOT_SUPPORT = new BizResultConstant(1000, "系统错误");

    public static final BizResultConstant PARAM_ERROR = new BizResultConstant(3000, "参数错误!");


    public static final BizResultConstant ID_BLANK = new BizResultConstant(3001, "缺少id!");
    public static final BizResultConstant DB_MODIFY_ERROR = new BizResultConstant(3002, "数据库操作失败");
    public static final BizResultConstant PASSWORD_ERROR = new BizResultConstant(3003, "密码不正确");
    public static final BizResultConstant USER_NEW_PASSWORD_DISACCORD = new BizResultConstant(3004, "新密码两次输入不一致");
    public static final BizResultConstant USER_NEW_OLD_ACCORD = new BizResultConstant(3005, "新密码与旧密码不能相同");
    public static final BizResultConstant NO_USER = new BizResultConstant(3006, "该用户不存在");
    public static final BizResultConstant USER_DISABLED = new BizResultConstant(3007, "用户被禁用！");

    public static final BizResultConstant TOKEN_VERIFY = new BizResultConstant(3008, "Token验签失败!");
    public static final BizResultConstant ERROR_USER_SESSION_EXPIRED = new BizResultConstant(3009, "请重新登录");

    public static final BizResultConstant USER_NAME_EXIST = new BizResultConstant(3010, "该用户名已存在");
    public static final BizResultConstant NO_CONTENT_DATA = new BizResultConstant(3011, "数据不存在");
    public static final BizResultConstant ROLE_IS_EXIST = new BizResultConstant(3012, "角色已存在");
    public static final BizResultConstant ROLE_IS_NOT_EXIST = new BizResultConstant(3012, "角色不存在");
    public static final BizResultConstant PERMISSION_IS_NOT_SAME_LEVEL = new BizResultConstant(3013, "权限不同级");


}


