package com.kotoaya.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(200,"成功"),
    FAIL(201,"失败"),
    PARAMATER_INVALID(300,"参数非法"),
    USERNAME_OR_PASSWORD_IS_NOT(301,"用户名或密码为空"),
    REGISTERED(302,"已注册"),
    LOGIN_FAIL(303,"用户名或密码错误"),
    GOODS_IS_NOT_EXIST(304,"商品不存在"),
    INCOMPLETE_GOODS_INFO(305,"商品信息不完整"),
    USER_IS_NOT_LOGIN(306,"用户未登录"),
    FILE_NOT_EXIST(307,"文件不存在");
    private final Integer code;
    private final String message;
}