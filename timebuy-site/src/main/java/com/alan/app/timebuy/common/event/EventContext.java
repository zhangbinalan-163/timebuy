package com.alan.app.timebuy.common.event;

/**
 * 事件的上下文
 * Created by zhangbinalan on 15/8/22.
 */
public interface EventContext {
    /**
     * 获得上下文中的指定项
     * @param key
     * @return
     */
    Object getByKey(String key);

    /**
     * 设置上下文指定项
     * @param key
     * @param value
     */
    void setContext(String key,Object value);
}
