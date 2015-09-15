package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.News;

import java.sql.SQLException;
import java.util.List;

/**
 * 评论相关业务接口封装
 * Created by wyk on 15/9/15.
 */
public interface CommentService {
    /**
     * 添加评论
     * @param comment
     * @throws TimeBuyException
     */
    void addComment(Comment comment) throws TimeBuyException;

    /**
     * 获取指定消息评论
     * @param comment
     * @return List<Comment>
     * @throws TimeBuyException
     */
    List<Comment> getCommentByNews(Comment comment) throws TimeBuyException;
}
