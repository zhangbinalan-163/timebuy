package com.alan.app.timebuy.service.impl;

import com.alan.app.timebuy.common.cache.ICache;
import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.dao.UserDao;
import com.alan.app.timebuy.entity.User;
import com.alan.app.timebuy.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户业务类实现
 * Created by zhangbinalan on 15/8/16.
 */
@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService{
    private static final int CACHE_TIME=60*60*1000;

    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    @Resource(name = "memcachedCache")
    private ICache cache;

    @Override
    public User getUserByPhone(String phone) throws TimeBuyException {
        //先从缓存读取
        String key=getPhoneKey(phone);
        String cacheValue=cache.get(key);
        if(!StringUtils.isEmpty(cacheValue)){
            return StringUtils.jsonToObject(cacheValue,User.class);
        }
        User userInfo = userDao.getByPhone(phone);
        if(userInfo!=null){
            //设置缓存
            cache.set(getPhoneKey(phone),StringUtils.toJsonString(userInfo),CACHE_TIME);
        }
        return userInfo;
    }

    private String getPhoneKey(String phone) {
        return phone+"@userinfo@timebuy";
    }

    @Override
    public User getUserById(int userId) throws TimeBuyException {
        return null;
    }

    @Override
    public void addUser(User user) throws TimeBuyException {
        userDao.insert(user);
    }
}
