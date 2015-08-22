package com.alan.app.timebuy.common.event.monitor;

import com.alan.app.timebuy.entity.User;

/**
 * 用户信息监控器接口定义
 * Created by zhangbinalan on 15/8/22.
 */
public interface UserMonitor {
    /**
     * 用户被创建
     * @param user
     */
    void afterCreated(User user);

    /**
     * 用户被删除
     * @param user
     */
    void afterDeleted(User user);

    /**
     * 用户被更新
     * @param user
     */
    void afterUpdated(User user);
}
