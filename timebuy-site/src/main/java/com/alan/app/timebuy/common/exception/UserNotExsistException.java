package com.alan.app.timebuy.common.exception;

/**
 * 用户不存在
 * Created by zhangbinalan on 15/8/15.
 */
public class UserNotExsistException extends TimeBuyException
{
    private static final Integer ERROR_CODE = 20003;

    public UserNotExsistException() {
        super(ERROR_CODE);
    }
    public UserNotExsistException(String message) {
        super(message, ERROR_CODE);
    }

    public UserNotExsistException(String message, Throwable cause) {
        super(message, cause, ERROR_CODE);
    }

    public UserNotExsistException(Throwable cause) {
        super(cause, ERROR_CODE);
    }
}
