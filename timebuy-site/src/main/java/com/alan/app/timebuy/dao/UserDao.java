package com.alan.app.timebuy.dao;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.User;

import java.sql.SQLException;

/**
 * 用户信息的操作DAO
 * Created by zhangbinalan on 15/8/15.
 */
public interface UserDao {
    /**
     * 根据手机号查询账号
     * @param phone
     * @return
     * @throws SQLException
     */
    User getByPhone(String phone) throws TimeBuyException;

    /**
     * 根据userId查询账号
     * @param userId
     * @return
     * @throws SQLException
     */
    User getById(int userId) throws TimeBuyException;

    /**
     * 插入账号
     * @param user
     * @throws SQLException
     */
    void insert(User user) throws TimeBuyException;
}
