package com.iweb.Exception;

//自定义异常类继承官方的Exception
//自定义异常类继承官方的RuntimeException
public class CustomException extends Exception {
    private String code;


    public CustomException(String message, String code) {
        super(message);//把message传给父类，用于初始化父类的detailMessage
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
