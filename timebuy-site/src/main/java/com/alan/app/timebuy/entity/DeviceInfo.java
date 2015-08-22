package com.alan.app.timebuy.entity;

/**
 * 设备信息实体
 * Created by zhangbinalan on 15/8/15.
 */
public class DeviceInfo {
    private ClientType clientType;//客户端类型
    private ClientVersion clientVersion;//客户端版本
    private String deviceId;//设备唯一编号

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public ClientVersion getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(ClientVersion clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
