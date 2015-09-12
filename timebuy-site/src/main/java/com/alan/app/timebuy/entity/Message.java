package com.alan.app.timebuy.entity;

import java.util.Date;

/**
 * 用户交流消息实体
 * Created by wyk on 15/9/12.
 */
public class Message {

    private int messageId;//消息主键
    private int acceptUserId;//接受消息用户主键
    private int sendUserId;//发送消息用户主键
    private String message;//消息内容
    private Date time;//发送消息时间

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(int acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public int getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(int sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
