package com.kotoaya.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }
    @ExceptionHandler(CustomException.class)
    public Result customError(CustomException e){
        return Result.customize(e.getResultEnum());
    }
}
