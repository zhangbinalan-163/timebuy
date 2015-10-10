package com.alan.app.timebuy.entity;

import java.util.Date;

/**
 * 申诉实体
 * Created by wyk on 15/10/9.
 */
public class Appeal {

    private int appealId;//申诉主键
    private Date appealTime;//申诉时间
    private String content;//申诉内容
    private int newsId;//被申诉消息主键

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getAppealId() {
        return appealId;
    }

    public void setAppealId(int appealId) {
        this.appealId = appealId;
    }

    public Date getAppealTime() {
        return appealTime;
    }

    public void setAppealTime(Date appealTime) {
        this.appealTime = appealTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
