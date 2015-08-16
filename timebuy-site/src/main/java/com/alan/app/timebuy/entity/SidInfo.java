package com.alan.app.timebuy.entity;

import java.util.Date;

/**
 * SID信息
 * Created by zhangbinalan on 15/8/16.
 */
public class SidInfo {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_FORBIDDEN = 1;
    private Integer id;//DB的id
    private String sid;//
    private Integer clientType;//客户端类型
    private String clientVersion;//客户端的版本号
    private String did;//设备号
    private Integer status = STATUS_NORMAL;//状态
    private Integer userId;//对应的登录用户ID
    private Date loginTime;//登录的时间
    private Date expireTime;//登录过期时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
