package com.alan.app.timebuy.common.exception;

/**
 * 非法操作异常
 * Created by zhangbalan on 15/8/15.
 */
public class IllegalOperationException extends TimeBuyException{
    private static final Integer ERROR_CODE = 10010;

    public IllegalOperationException() {
        super(ERROR_CODE);
    }
    public IllegalOperationException(String message) {
        super(message, ERROR_CODE);
    }

    public IllegalOperationException(String message, Throwable cause) {
        super(message, cause, ERROR_CODE);
    }

    public IllegalOperationException(Throwable cause) {
        super(cause, ERROR_CODE);
    }
}
