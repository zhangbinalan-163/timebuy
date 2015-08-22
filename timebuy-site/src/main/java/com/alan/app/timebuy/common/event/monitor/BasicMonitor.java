package com.alan.app.timebuy.common.event.monitor;

import com.alan.app.timebuy.common.event.IEvent;
import com.alan.app.timebuy.common.event.listener.IEventListener;

import java.util.LinkedList;
import java.util.List;

/**
 * 监控器基础类
 * Created by zhangbinalan on 15/8/22.
 */
public class BasicMonitor {
    private List<IEventListener> listenerList=new LinkedList<IEventListener>();

    protected void registerListener(IEventListener listener){
        listenerList.add(listener);
    }
    protected void unRegisterListener(IEventListener listener){
        listenerList.remove(listener);
    }

    /**
     * 触发所有的监听器响应某一个事件
     * @param event
     */
    protected void fireOnListener(IEvent<?> event){
        for(IEventListener listener:listenerList){
            listener.processEvent(event);
        }
    }
}
