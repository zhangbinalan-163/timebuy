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
     * 检查登录信息是否过期
     * @param paramSidInfo
     * @return
     * @throws TimeBuyException
     */
    boolean checkLoginExpire(SidInfo paramSidInfo)
            throws TimeBuyException;

    /**
     * 检查SID是否绑定账号登录
     * @param sidInfo
     * @return
     * @throws TimeBuyException
     */
    boolean checkHasLogin(SidInfo sidInfo)
            throws TimeBuyException;

    /**
     * sid与账号绑定登录
     * @param sid
     * @param user
     * @throws TimeBuyException
     */
    void bindLoginUser(String sid, User user)
            throws TimeBuyException;

    /**
     * sid解除账号登录绑定
     * @param sid
     * @throws TimeBuyException
     */
    void unBindLoginUser(String sid) throws TimeBuyException;

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
     * 获得sid信息
     * @param sid
     * @return
     * @throws TimeBuyException
     */
    SidInfo getSid(String sid) throws TimeBuyException;
}
