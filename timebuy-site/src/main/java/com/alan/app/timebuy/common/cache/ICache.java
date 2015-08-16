package com.alan.app.timebuy.common.cache;

/**
 *
 * Created by zhangbinalan on 15/8/16.
 */
public interface ICache {
    /**
     * 设置缓存。当前只支持string
     * @param key
     * @param value
     * @param time
     */
    void set(String key, String value, int time);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 删除缓存
     * @param key
     */
    void delete(String key);

    /**
     *
     * @param key
     * @param count
     * @return
     */
    long incr(String key, long count);
}
