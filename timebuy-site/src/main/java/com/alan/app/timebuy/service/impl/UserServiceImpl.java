package com.alan.app.timebuy.service.impl;

import com.alan.app.timebuy.common.cache.ICache;
import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.util.CacheKeyUtils;
import com.alan.app.timebuy.dao.UserDao;
import com.alan.app.timebuy.entity.User;
import com.alan.app.timebuy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户业务类实现
 * Created by zhangbinalan on 15/8/16.
 */
@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService{
    private static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    private static final int CACHE_TIME=60*60;//用户信息缓存1小时
    private static final String CACHE_TYPE_PHONE="phone";
    private static final String CACHE_TYPE_USERID="userId";
    private static final String CACHE_TYPE_QQ="qq";
    private static final String CACHE_TYPE_SINA="sina";
    private static final String CACHE_TYPE_WX="wx";
    private static final String CACHE_TYPE_ZFB="zfb";

    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    @Resource(name = "memcachedCacheImpl")
    private ICache cache;

    @Override
    public User getUserByPhone(String phone) throws TimeBuyException {
        //先从缓存读取
        User userInfo=getUserFromCache(CACHE_TYPE_PHONE, phone);
        if(userInfo!=null){
            return userInfo;
        }
        userInfo = userDao.getByPhone(phone);
        if(userInfo!=null){
            //设置缓存
            setUserInCache(CACHE_TYPE_PHONE,phone,userInfo);
        }
        return userInfo;
    }

    /**
     * 根据制定key从缓存获取user信息
     * @param keyType
     * @param key
     * @return
     */
    private User getUserFromCache(String keyType,String key){
        String cacheKey=getUserCacheKey(keyType,key);
        User userInfo=cache.getObject(cacheKey, User.class);
        return userInfo;
    }

    /**
     * 保存用户信息到缓存
     * @param keyType
     * @param key
     * @param userInfo
     */
    private void setUserInCache(String keyType,String key,User userInfo){
        String cacheKey=getUserCacheKey(keyType,key);
        cache.setObject(cacheKey,userInfo,CACHE_TIME);
    }

    /**
     * 组装缓存的key
     * @param keyType
     * @param key
     * @return
     */
    private String getUserCacheKey(String keyType,String key){
        if(keyType.equals(CACHE_TYPE_PHONE)){
            return CacheKeyUtils.UserCache.getUserInfoKeyByPhone(key);
        }else{
            return CacheKeyUtils.UserCache.getUserInfoKeyByUserId(key);
        }
    }

    @Override
    public User getUserById(int userId) throws TimeBuyException {
        //先从缓存读取
        User userInfo=getUserFromCache(CACHE_TYPE_USERID, String.valueOf(userId));
        if(userInfo!=null){
            return userInfo;
        }
        userInfo = userDao.getById(userId);
        if(userInfo!=null){
            //设置缓存
            setUserInCache(CACHE_TYPE_USERID,String.valueOf(userId),userInfo);
        }
        return userInfo;
    }

    @Override
    public void addUser(User user) throws TimeBuyException {
        userDao.insert(user);
    }

    @Override
    public void updateUser(User user) throws TimeBuyException {
        userDao.update(user);
    }

    @Override
    public List<User> getAllUser() throws Exception {
       return userDao.getAll();
    }

}
