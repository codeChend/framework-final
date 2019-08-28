package com.startdt.modules.common.utils.exception;


import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandler {

    // ⒉全局异常处理返回JSON
    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public Result jsonErrorHandler(HttpServletRequest req, UserException e) {
        return Result.ofError(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result methodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        for(ObjectError objectError : allErrors) {
            sb.append(objectError.getDefaultMessage()).append(".");
        }
        return Result.ofError(BizResultConstant.PARAM_ERROR.getResultCode(), sb.toString());
    }
}