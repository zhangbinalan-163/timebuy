package com.alan.app.timebuy.common.exception;

/**
 * 密码错误异常
 * Created by zhangbinalan on 15/8/15.
 */
public class PasswordErrorException extends TimeBuyException
{
    private static final Integer ERROR_CODE = 20004;

    public PasswordErrorException() {
        super(ERROR_CODE);
    }
    public PasswordErrorException(String message) {
        super(message, ERROR_CODE);
    }

    public PasswordErrorException(String message, Throwable cause) {
        super(message, cause, ERROR_CODE);
    }

    public PasswordErrorException(Throwable cause) {
        super(cause, ERROR_CODE);
    }
}
