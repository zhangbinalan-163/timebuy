package com.alan.app.timebuy.common.event.listener;

import com.alan.app.timebuy.common.event.IEvent;

/**
 * 事件的监听器接口
 * Created by zhangbinalan on 15/8/22.
 */
public interface IEventListener {
    /**
     * 处理事件
     * @param event
     */
    void processEvent(IEvent<?> event);
}
