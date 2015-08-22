package com.alan.app.timebuy.common.event;

/**
 * 事件处理器
 * Created by zhangbinalan on 15/8/22.
 */
public interface EventHandler {
    /**
     * 处理事件
     * @param event
     */
    void handle(Event<?> event);
}
