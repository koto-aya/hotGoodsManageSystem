package com.kotoaya.common;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private ResultEnum resultEnum;
    public CustomException(String message) {
        super(message);
    }
    public CustomException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.resultEnum=resultEnum;
    }
}
