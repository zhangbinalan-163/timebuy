package com.alan.app.timebuy.common.event;

/**
 * Created by zhangbinalan on 15/8/22.
 */
public class BaseEvent<T> implements Event<T>{
    private EventContext context=new BaseEventContext();

    private T source;

    public BaseEvent(EventContext context, T source) {
        this.context = context;
        this.source = source;
    }

    @Override
    public T getSource() {
        return source;
    }

    @Override
    public EventContext getContext() {
        return context;
    }
}
