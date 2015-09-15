package com.alan.app.timebuy.entity;

import java.util.Date;

/**
 * 评论实体
 * Created by wyk on 15/9/12.
 */
public class Comment {

    private int commentId;//评论主键
    private int newsId;//消息主键
    private int userId;//用户主键
    private Date commentTime;//评论时间
    private String comment;//评论内容

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
