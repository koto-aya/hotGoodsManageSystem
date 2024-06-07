package com.kotoaya.common.utils;

import com.kotoaya.common.CustomException;
import com.kotoaya.common.ResultEnum;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ParameterVerificationUtil {
    //对象参数非空校验
    public static void checkObjectIsNull(Object obj){
        if (ObjectUtils.isEmpty(obj)){
            throw new CustomException(ResultEnum.PARAMATER_INVALID);
        }
    }
    //字符串参数非空校验
    public static void checkStringIsNull(String str){
        if (!StringUtils.hasLength(str)){
            throw new CustomException(ResultEnum.PARAMATER_INVALID);
        }
    }
    //多参数校验
    public static void checkMultiParam(Object ...param){
        for (Object obj : param) {
            if (obj instanceof String){
                checkStringIsNull((String) obj);
            } else{
                checkObjectIsNull(obj);
            }
        }
    }
}
