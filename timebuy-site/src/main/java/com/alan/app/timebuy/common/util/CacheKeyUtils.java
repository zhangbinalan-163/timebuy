package com.alan.app.timebuy.common.util;

/**
 * 缓存KEY相关的工具方法
 * Created by zhangbinalan on 15/8/22.
 */
public class CacheKeyUtils {
    /**
     * 用户信息CACHE相关
     */
    public static class UserCache{
        /**
         * 获取根据phone缓存的user的key
         * @param phone
         * @return
         */
        public static String getUserInfoKeyByPhone(String phone){
            return phone+"@phone@userinfo@timebuy";
        }

        /**
         * 获取根据userId缓存的user的key
         * @param userId
         * @return
         */
        public static String getUserInfoKeyByUserId(String userId){
            return userId+"@userid@userinfo@timebuy";
        }
    }
}
