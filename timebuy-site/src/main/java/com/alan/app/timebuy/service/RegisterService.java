package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.User;

/**
 * 注册业务类接口
 * Created by zhangbinalan on 15/8/15.
 */
public interface RegisterService {
    /**
     * 注册用户
     * @param user
     * @throws TimeBuyException
     */
    void registerUser(User user) throws TimeBuyException;

    /**
     * 注册用户时发送短信验证码
     * @param phone
     * @throws TimeBuyException
     */
    void sendUserRegSms(String phone) throws TimeBuyException;

    /**
     * 验证用户注册的短信验证码
     * @param phone
     * @param code
     * @throws TimeBuyException
     */
    void checkUserRegSms(String phone, String code) throws TimeBuyException;
}
