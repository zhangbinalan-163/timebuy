package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.DeviceInfo;
import com.alan.app.timebuy.entity.SidInfo;
import com.alan.app.timebuy.entity.User;


/**
 * SID业务接口
 * Created by zhangbinalan on 15/8/16.
 */
public interface SidService {
    /**
     * 根据设备信息生成SID
     * @param paramDeviceInfo
     * @return
     * @throws TimeBuyException
     */
    String generateSid(DeviceInfo paramDeviceInfo) throws TimeBuyException;

    /**
     * 刷新账号登录绑定有效期
     * @param deviceInfo
     * @param sid
     * @throws TimeBuyException
     */
    void refreshSid(DeviceInfo deviceInfo, String sid) throws TimeBuyException;

    /**
     * 根据SID获得SID信息
     */
    SidInfo getSid(String sid) throws TimeBuyException;

    /**
     * 新增SID信息
     * @param sidInfo
     * @throws TimeBuyException
     */
    void addSid(SidInfo sidInfo) throws TimeBuyException;

    /**
     * 绑定账号登录
     * @param sid
     * @param user
     * @throws TimeBuyException
     */
    void bindLoginUser(String sid, User user) throws TimeBuyException;
}
