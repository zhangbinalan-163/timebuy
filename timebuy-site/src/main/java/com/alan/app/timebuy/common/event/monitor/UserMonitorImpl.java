package com.alan.app.timebuy.common.event.monitor;

import com.alan.app.timebuy.common.event.UserEvent;
import com.alan.app.timebuy.common.event.listener.IEventListener;
import com.alan.app.timebuy.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 用户信息的监控器
 * Created by zhangbinalan on 15/8/22.
 */
@Component(value = "userMonitorImpl")
public class UserMonitorImpl extends BasicMonitor implements UserMonitor{

    @Resource(name="userEventCacheListener")
    private IEventListener userCacheListener;

    @PostConstruct
    public void init(){
        //注册清理账号缓存的事件监听器
        registerListener(userCacheListener);
        //其他的监听器可以在这里设置
    }
    @Override
    public void afterCreated(User user) {
        UserEvent userEvent = UserEvent.builder().user(user).buildCreateEvent();
        fireOnListener(userEvent);
    }

    @Override
    public void afterDeleted(User user) {
        UserEvent userEvent = UserEvent.builder().user(user).buildDeleteEvent();
        fireOnListener(userEvent);
    }

    @Override
    public void afterUpdated(User user) {
        UserEvent userEvent = UserEvent.builder().user(user).buildUpdateEvent();
        fireOnListener(userEvent);
    }
}
