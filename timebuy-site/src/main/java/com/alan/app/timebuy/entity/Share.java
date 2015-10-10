package com.alan.app.timebuy.entity;

import java.util.Date;

/**
 * 分享实体
 * Created by wyk on 15/10/9.
 */
public class Share {

    private int shareId;//分享主键
    private Date shareTime;//分享时间
    private String content;//分享内容
    private int newsId;//消息主键
    private int userId;//用户主键

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
