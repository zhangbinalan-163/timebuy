package com.alan.app.timebuy.web.vo;

import java.io.Serializable;

/**
 * WEB请求响应信息封装
 * Created by zhangbinalan on 15/8/15.
 */
public class Response implements Serializable{
    private boolean success;
    private String msg;
    private int code;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
