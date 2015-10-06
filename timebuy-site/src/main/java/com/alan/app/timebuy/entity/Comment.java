package com.alan.app.timebuy.entity;

import java.util.Date;

/**
 * 评论实体
 * Created by wyk on 15/9/12.
 */
public class Comment {

    private int commentId;//评论主键
    private int newsId;//消息主键
    private int userId;//被评论用户主键
    private Date commentTime;//评论时间
    private int speed;//速度,为1-5五个分数
    private int service;//服务质量 为1-5五个分数
    private int kind;//评论类型 0发布方评论响应方； 1相反
    private int doId;//评论人的主键

    public int getDoId() {
        return doId;
    }

    public void setDoId(int doId) {
        this.doId = doId;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

}
