package com.alan.app.timebuy.common.event;

/**
 * 事件接口
 * Created by zhangbinalan on 15/8/22.
 */
public interface Event<T> {
    /**
     * 获得事件源
     * @return
     */
    T getSource();

    /**
     * 获得事件的上下文信息
     * @return
     */
    EventContext getContext();
}
