package com.alan.app.timebuy.dao.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.dao.UserDao;
import com.alan.app.timebuy.dao.mapper.UserMapper;
import com.alan.app.timebuy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * UserDao的具体实现
 * Created by zhangbinalan on 15/8/15.
 */
@Repository(value = "userDaoImpl")
public class UserDaoImpl implements UserDao{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByPhone(String phone) throws TimeBuyException {
        try {
            return userMapper.getByPhone(phone);
        } catch (Exception e) {
            throw new TimeBuyException("查询用户数据异常",e);
        }
    }

    @Override
    public User getById(int userId) throws TimeBuyException {
        try {
            return userMapper.getById(userId);
        } catch (Exception e) {
            throw new TimeBuyException("查询用户数据异常",e);
        }
    }

    @Override
    public void insert(User user) throws TimeBuyException {
        try {
            userMapper.insert(user);
        } catch (Exception e) {
            throw new TimeBuyException("插入用户数据异常",e);
        }
    }

    @Override
    public void update(User user) throws TimeBuyException {
        try{
          userMapper.update(user);
        }catch (Exception e) {
            throw new TimeBuyException("修改用户信息异常",e);
        }
    }

    @Override
    public List<Map<String,Object>> getAll() throws TimeBuyException{
        try{
            return userMapper.getAll();
        }catch (Exception e){
           throw new TimeBuyException("查询所有用户信息异常",e);
        }
    }

}
