package com.startdt.modules.common.utils.exception;

import com.startdt.modules.common.utils.result.BizResultConstant;

import java.io.Serializable;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/28 下午4:21
 * @Modified By:
 */
public class FrameworkException extends RuntimeException implements Serializable{
    private Integer code;

    public FrameworkException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public FrameworkException(BizResultConstant bizResultConstant){
        super(bizResultConstant.getDescription());
        this.code = bizResultConstant.getResultCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
