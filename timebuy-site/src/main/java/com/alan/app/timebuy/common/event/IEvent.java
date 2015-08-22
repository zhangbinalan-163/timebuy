package com.alan.app.timebuy.common.event;

/**
 * 事件接口定义
 * Created by zhangbinlan on 15/8/22.
 */
public interface IEvent<T> {
    /**
     * 获得事件产生源
     * @return
     */
    T getSourceElement();

    /**
     * 事件类型
     * @return
     */
    int getEventType();
}
