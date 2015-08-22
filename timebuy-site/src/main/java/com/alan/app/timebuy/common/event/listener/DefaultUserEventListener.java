package com.alan.app.timebuy.common.event.listener;

import com.alan.app.timebuy.common.event.IEvent;
import com.alan.app.timebuy.common.event.UserEvent;
import com.alan.app.timebuy.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 用户事件的监听器
 * Created by zhangbinalan on 15/8/22.
 */
public class DefaultUserEventListener implements IEventListener{
    private static Logger logger= LoggerFactory.getLogger(DefaultUserEventListener.class);

    @Override
    public void processEvent(IEvent<?> event) {
        if(event instanceof UserEvent){
            UserEvent userEvent = (UserEvent) event;
            processUserEvent(userEvent);
        }else{
            logger.warn("can not process this event={},must ={}",event.getClass().getName(),UserEvent.class.getName());
        }
    }

    private void processUserEvent(UserEvent userEvent) {
        //产生该事件的User
        User sourceUserInfo = userEvent.getSourceElement();
        if (userEvent.getEventType()==UserEvent.CREATE) {
            doAfterCreate(sourceUserInfo);
        }else if(userEvent.getEventType()==UserEvent.DELETE){
            doAfterDelete(sourceUserInfo);
        }else if(userEvent.getEventType()==UserEvent.PASSCHANGE){
            doAfterCreatePasschange(sourceUserInfo);
        }else if(userEvent.getEventType()==UserEvent.UPDATE){
            doAfterUpdate(sourceUserInfo);
        }else{
            logger.warn("can not process this user event,event type={}",userEvent.getEventType());
        }
    }

    protected void doAfterUpdate(User sourceUserInfo) {

    }

    protected void doAfterCreatePasschange(User sourceUserInfo) {

    }

    protected void doAfterDelete(User sourceUserInfo) {

    }

    protected void doAfterCreate(User sourceUserInfo) {

    }
}
