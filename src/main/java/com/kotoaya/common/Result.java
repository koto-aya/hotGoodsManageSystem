package com.kotoaya.common;

import lombok.Data;

/**
 * @author wzw
 * @param <T>
 * 统一结果返回类
 */
@Data
public class Result<T> {
    private  Integer code;
    private  String message;
    private  T data;

    private Result(){}

    private static <T> Result<T> build(int code,String message){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    private static <T> Result<T> build(int code,String message,T data){
        Result<T> result = build(code, message);
        result.setData(data);
        return result;
    }

    /**
     * @author wzw
     * 该方法将返回一个代表操作成功的结果类
     */
    public static <T> Result<T> ok(){
        return build(ResultEnum.SUCCESS.getCode(), "成功");
    }

    /**
     * @author wzw
     * 该方法将返回一个代表操作成功的结果类，并且携带数据
     */
    public static <T> Result<T> ok(T data){
        Result<T> result = ok();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(){
        return build(ResultEnum.FAIL.getCode(), "失败");
    }

    public static <T> Result<T> fail(T data){
        Result<T> result = fail();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> customize(ResultEnum resultEnum){
        return build(resultEnum.getCode(), resultEnum.getMessage());
    }
}
