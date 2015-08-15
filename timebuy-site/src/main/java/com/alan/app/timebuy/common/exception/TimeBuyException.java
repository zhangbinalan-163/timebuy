package com.alan.app.timebuy.common.exception;

/**
 * TimeBuy自定义顶层异常
 *
 * Created by zhangbinalan on 15/8/15.
 */
public class TimeBuyException extends Exception{

    public static Integer code = 10001;

    public TimeBuyException(String message) {
        super(message);
    }

    public TimeBuyException(String message, Throwable cause, Integer code) {
        super(message, cause);
        code = code;
    }
    public TimeBuyException(Integer code) {
        code = code;
    }
    public TimeBuyException(Throwable cause, Integer code) {
        super(cause);
        code = code;
    }
    public TimeBuyException(String message, Integer code) {
        super(message);
        code = code;
    }
    public TimeBuyException(Throwable cause) {
        super(cause);
    }
    public TimeBuyException(String message, Throwable cause) {
        super(message, cause);
    }
    public Integer getCode() {
        return code;
    }
}
