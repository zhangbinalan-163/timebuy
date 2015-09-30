package com.alan.app.timebuy.dao;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
     * 根据QQ查询账号
     * @param user
     * @return
     * @throws SQLException
     */
    User getByQQ(User user) throws TimeBuyException;

    /**
     * 根据新浪查询账号
     * @param user
     * @return
     * @throws SQLException
     */
    User getBySina(User user) throws TimeBuyException;

    /**
     * 根据微信查询账号
     * @param user
     * @return
     * @throws SQLException
     */
    User getByWx(User user) throws TimeBuyException;

    /**
     * 根据支付宝查询账号
     * @param user
     * @return
     * @throws SQLException
     */
    User getByZfb(User user) throws TimeBuyException;

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

    /**
     * 修改信息
     * @param user
     * @return
     * @throws SQLException
     */
    void update(User user) throws TimeBuyException;

    /**
     * 获取所有用户参数
     * @return
     * @throws SQLException
     */
    List<User> getAll() throws Exception;

}
