package com.alan.app.timebuy.common.exception;

/**
 * 用户已经存在
 * Created by zhangbinalan on 15/8/15.
 */
public class UserExsistException extends TimeBuyException
{
    private static final Integer ERROR_CODE = 20002;

    public UserExsistException() {
        super(ERROR_CODE);
    }
    public UserExsistException(String message) {
        super(message, ERROR_CODE);
    }

    public UserExsistException(String message, Throwable cause) {
        super(message, cause, ERROR_CODE);
    }

    public UserExsistException(Throwable cause) {
        super(cause, ERROR_CODE);
    }
}
