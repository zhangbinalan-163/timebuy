package com.alan.app.timebuy.dao.mapper;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.News;

import java.sql.SQLException;
import java.util.List;

/**
 * 消息评论的Mapper
 * Created by wyk on 15/9/14.
 */
public interface CommentMapper {

    /**
     * 插入消息评论
     * @param comment
     * @throws SQLException
     */
    void insert(Comment comment) throws TimeBuyException;

    /**
     * 获取指定消息的评论
     * @param comment
     * @return List<Comment>
     * @throws SQLException
     */
    List<Comment> getCommentByNews(Comment comment) throws TimeBuyException;
}
