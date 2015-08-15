package com.alan.app.timebuy.web.vo;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.DeviceInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 外部HTTP请求封装
 * Created by zhangbinalan on 15/8/15.
 */
public class Request {

    private HttpServletRequest httpRequest;
    private DeviceInfo deviceInfo;//当前请求的设备信息
    private String sid;//当前请求的SID
    private int userId;//当前在线的用户ID

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String get(String key) {
        return this.httpRequest.getParameter(key);
    }

    public int getInt(String key) throws TimeBuyException {
        String str = this.httpRequest.getParameter(key);
        try {
            return Integer.parseInt(str); } catch (NumberFormatException e) {
        }
        throw new InvalidParamException("参数缺失或格式错误:" + key);
    }

    public int getInt(String key, int defau) throws TimeBuyException
    {
        String str = this.httpRequest.getParameter(key);
        if ((str == null) || (str.trim().equals(""))) {
            return defau;
        }
        return getInt(key);
    }

    public long getLong(String key) throws TimeBuyException {
        String str = this.httpRequest.getParameter(key);
        try {
            return Long.parseLong(str); } catch (NumberFormatException e) {
        }
        throw new InvalidParamException("参数缺失或格式错误:" + key);
    }

    public long getLong(String key, long defau) throws TimeBuyException
    {
        String str = this.httpRequest.getParameter(key);
        if ((str == null) || (str.trim().equals(""))) {
            return defau;
        }
        return getLong(key);
    }

    public String getString(String key) throws TimeBuyException {
        String str = this.httpRequest.getParameter(key);
        if ((str == null) || (str.trim().equals(""))) {
            throw new InvalidParamException("参数缺失或格式错误:" + key);
        }
        return str;
    }
    public float getFloat(String key) throws TimeBuyException {
        String str = this.httpRequest.getParameter(key);
        if ((str == null) || (str.trim().equals("")))
            throw new InvalidParamException("参数缺失或格式错误:" + key);
        try
        {
            return Float.parseFloat(str);
        }
        catch (Exception e) {
            throw new InvalidParamException("参数缺失或格式错误:" + key, e);
        }
    }

    public String getString(String key, String defau) { String str = this.httpRequest.getParameter(key);
        if ((str == null) || (str.trim().equals(""))) {
            return defau;
        }
        return str; }

    public String[] getStringArray(String key) throws TimeBuyException
    {
        String[] arr = this.httpRequest.getParameterValues(key);
        if ((arr == null) || (arr.length == 0)) {
            throw new InvalidParamException("参数缺失或格式错误:" + key);
        }
        return arr;
    }

    public String[] getStringArray(String key, String[] defau) {
        String[] arr = this.httpRequest.getParameterValues(key);
        if ((arr == null) || (arr.length == 0)) {
            return defau;
        }
        return arr;
    }

    public Date getDate(String key, String pattern) throws TimeBuyException {
        String str = this.httpRequest.getParameter(key);
        if ((str == null) || (str.trim().equals(""))) {
            return null;
        }
        if ((pattern == null) || (pattern.trim().equals(""))) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(str); } catch (Exception e) {
        }
        throw new InvalidParamException("参数缺失或格式错误:" + key);
    }
    public Date getDate(String key) throws TimeBuyException {
        return getDate(key, "yyyy-MM-dd");
    }

    public boolean getBoolean(String key) {
        return "true".equals(this.httpRequest.getParameter(key));
    }
}
