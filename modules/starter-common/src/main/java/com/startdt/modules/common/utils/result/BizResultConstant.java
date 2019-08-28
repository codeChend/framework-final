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

    public static final BizResultConstant ID_BLANK = new BizResultConstant(3001, "缺少id!");
    public static final BizResultConstant DB_MODIFY_ERROR = new BizResultConstant(3002, "数据库操作失败");
    public static final BizResultConstant PASSWORD_ERROR = new BizResultConstant(3003, "密码不正确");
    public static final BizResultConstant USER_NEW_PASSWORD_DISACCORD = new BizResultConstant(3004, "新密码两次输入不一致");
    public static final BizResultConstant USER_NEW_OLD_ACCORD = new BizResultConstant(3005, "新密码与旧密码不能相同");
    public static final BizResultConstant NO_USER = new BizResultConstant(3006, "该用户不存在");
    public static final BizResultConstant USER_DISABLED = new BizResultConstant(3007, "用户被禁用！");


}


