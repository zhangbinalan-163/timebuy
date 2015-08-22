package com.alan.app.timebuy.common.event;

import com.alan.app.timebuy.entity.User;

/**
 * 用户信息操作产生的事件
 * Created by zhangbinalan on 15/8/22.
 */
public class UserEvent extends BaseEvent<User>{
    public final static int CREATE = 0;//创建账号

    public final static int UPDATE = 1;//修改账号信息产生的事件

    public final static int PASSCHANGE = 2;//修改密码产生的事件

    public final static int DELETE = 3;//删除用户产生的事件

    public UserEvent(User user) {
        super(user);
    }

    public UserEvent(User user, int type) {
        super(user, type);
    }

    /**
     * 获得构造器
     * @return
     */
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private User user;

        public Builder user(User user){
            this.user=user;
            return this;
        }
        public UserEvent buildCreateEvent(){
            UserEvent userEvent=new UserEvent(this.user,CREATE);
            return userEvent;
        }
        public UserEvent buildDeleteEvent(){
            UserEvent userEvent=new UserEvent(this.user,DELETE);
            return userEvent;
        }
        public UserEvent buildUpdateEvent(){
            UserEvent userEvent=new UserEvent(this.user,UPDATE);
            return userEvent;
        }
    }
}
