package com.alan.app.timebuy.common.exception;
/**
 * 参数异常
 * Created by zhangbinalan on 15/8/15.
 */
public class InvalidParamException extends TimeBuyException{
    private static final Integer ERROR_CODE = 10002;

    public InvalidParamException() {
        super(ERROR_CODE);
    }
    public InvalidParamException(String message) {
        super(message, ERROR_CODE);
    }

    public InvalidParamException(String message, Throwable cause) {
        super(message, cause, ERROR_CODE);
    }

    public InvalidParamException(Throwable cause) {
        super(cause, ERROR_CODE);
    }
}
