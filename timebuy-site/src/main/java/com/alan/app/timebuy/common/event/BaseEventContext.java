package com.alan.app.timebuy.common.event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangbinalan on 15/8/22.
 */
public class BaseEventContext implements EventContext{
    private Map<String,Object> context= new HashMap<String,Object>();

    @Override
    public void setContext(String key, Object value) {
        context.put(key,value);
    }

    @Override
    public Object getByKey(String key) {
        return context.get(key);
    }
}
