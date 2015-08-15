package com.alan.app.timebuy.dao.mapper;

import com.alan.app.timebuy.entity.User;

import java.sql.SQLException;

/**
 * user信息的mapper
 * Created by zhangbin2 on 15/8/15.
 */
public interface UserMapper {
    /**
     * 根据手机号查询账号
     * @param phone
     * @return
     * @throws SQLException
     */
    User getByPhone(String phone) throws Exception;

    /**
     * 根据userId查询账号
     * @param userId
     * @return
     * @throws SQLException
     */
    User getById(int userId) throws Exception;

    /**
     * 插入账号
     * @param user
     * @throws SQLException
     */
    void insert(User user) throws Exception;
}
