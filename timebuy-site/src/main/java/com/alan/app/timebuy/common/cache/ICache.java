package com.alan.app.timebuy.common.cache;

/**
 * 缓存接口
 *
 * Created by zhangbinalan on 15/8/16.
 */
public interface ICache {
    /**
     * 设置缓存
     * @param key
     * @param value
     * @param time
     *      单位S
     */
    void set(String key, String value, int time);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    String get(String key);
    /**
     * 设置缓存
     * @param key
     * @param value
     * @param time
     *      单位S
     */
    void setObject(String key, Object value, int time);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    <T> T getObject(String key,Class<T> targetClass);

    /**
     * 删除缓存
     * @param key
     */
    void delete(String key);

    /**
     * 原子自增
     * @param key
     * @param count
     * @param defaultValue
     * @param time
     *      如果不存在，设置defaultValue，有效期为time 单位S
     * @return
     */
    long incr(String key, long count,long defaultValue,int time);
}
