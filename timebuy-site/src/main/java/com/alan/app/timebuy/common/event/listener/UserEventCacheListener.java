package com.alan.app.timebuy.common.event.listener;

import com.alan.app.timebuy.common.cache.ICache;
import com.alan.app.timebuy.common.util.CacheKeyUtils;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户事件监听器--清理缓存
 * <br/>
 * 现在是同步处理，后期优化可以做成异步
 * Created by zhangbinalan on 15/8/22.
 */
@Component(value = "userEventCacheListener")
public class UserEventCacheListener extends DefaultUserEventListener{

    @Resource(name = "memcachedCacheImpl")
    private ICache cache;

    @Override
    protected void doAfterUpdate(User userInfo) {
        if(!StringUtils.isEmpty(userInfo.getPhone())){
            String key=CacheKeyUtils.UserCache.getUserInfoKeyByPhone(userInfo.getPhone());
            cache.delete(key);
        }
        if(userInfo.getUserId()!=null){
            String key=CacheKeyUtils.UserCache.getUserInfoKeyByUserId(String.valueOf(userInfo.getUserId()));
            cache.delete(key);
        }
    }
    @Override
    protected void doAfterDelete(User userInfo) {
        if(!StringUtils.isEmpty(userInfo.getPhone())){
            String key=CacheKeyUtils.UserCache.getUserInfoKeyByPhone(userInfo.getPhone());
            cache.delete(key);
        }
        if(userInfo.getUserId()!=null){
            String key=CacheKeyUtils.UserCache.getUserInfoKeyByUserId(String.valueOf(userInfo.getUserId()));
            cache.delete(key);
        }
    }
    @Override
    protected void doAfterCreate(User userInfo) {
        if(!StringUtils.isEmpty(userInfo.getPhone())){
            String key=CacheKeyUtils.UserCache.getUserInfoKeyByPhone(userInfo.getPhone());
            cache.delete(key);
        }
        if(userInfo.getUserId()!=null){
            String key=CacheKeyUtils.UserCache.getUserInfoKeyByUserId(String.valueOf(userInfo.getUserId()));
            cache.delete(key);
        }
    }
}
