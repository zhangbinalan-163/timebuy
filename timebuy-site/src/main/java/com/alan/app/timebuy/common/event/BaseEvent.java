package com.alan.app.timebuy.common.event;

/**
 * 基础的事件实现
 * Created by zhangbinalan on 15/8/22.
 */
public class BaseEvent<T> implements IEvent<T> {
    //事件源
    private T element;
    //事件类型
    private int type = 0;

    protected BaseEvent(T t) {
        this.element = t;
    }

    protected BaseEvent(T t, int type) {
        this.element = t;
        this.type = type;
    }

    @Override
    public int getEventType() {
        return type;
    }

    @Override
    public T getSourceElement() {
        return element;
    }

    public int getType() {
        return type;
    }
}
