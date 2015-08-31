package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    /**
     * 修改信息
     * @param user
     * @return
     * @throws SQLException
     */
    void updateUser(User user) throws Exception;

    /**
     * 获取所有用户参数
     * @return
     * @throws SQLException
     */
//    List<Map<String,Object>> getAllUser() throws Exception;
}
