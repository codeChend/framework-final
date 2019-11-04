package com.startdt.modules.common.utils.result;

import java.io.Serializable;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/8/27 下午8:42
 * @Modified By:
 */
public class Result<T> implements Serializable {

    private boolean success;
    private int code;
    private String message;
    private T value;

    private Result() {
    }

    public static <T> Result<T> ofSuccess() {
        return ofSuccess(null);
    }

    public static <T> Result<T> ofSuccess(T value) {
        Result<T> result = new Result();
        result.setSuccess(true);
        result.setValue(value);
        result.setCode(0);
        return result;
    }

    public static <T> Result<T> ofErrorT(int codeNum, String codeDesc) {
        Result<T> result = new Result();
        result.setSuccess(false);
        result.setCode(codeNum);
        result.setMessage(codeDesc);
        return result;
    }

    public static <T> Result<T> ofErrorT(BizResultConstant baseResultConstant) {
        Result<T> result = new Result();
        result.setSuccess(false);
        result.setCode(baseResultConstant.getResultCode());
        result.setMessage(baseResultConstant.getDescription());
        return result;
    }

    public static Result ofError(int codeNum, String codeDesc) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(codeNum);
        result.setMessage(codeDesc);
        return result;
    }

    public static Result ofError(BizResultConstant baseResultConstant) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(baseResultConstant.getResultCode());
        result.setMessage(baseResultConstant.getDescription());
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", value=" + value +
                '}';
    }
}
