package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.User;

/**
 * 用户相关业务接口封装
 * Created by zhangbinalan on 15/8/16.
 */
public interface UserService {
    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return
     * @throws TimeBuyException
     */
    User getUserByPhone(String phone) throws TimeBuyException;

    /**
     * 根据userId获取用户信息
     * @param userId
     * @return
     * @throws TimeBuyException
     */
    User getUserById(int userId) throws TimeBuyException;

    /**
     * 添加用户信息
     * @param user
     * @throws TimeBuyException
     */
    void addUser(User user) throws TimeBuyException;

}
