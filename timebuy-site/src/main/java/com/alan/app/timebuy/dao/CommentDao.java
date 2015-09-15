package com.alan.app.timebuy.dao;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.News;

import java.sql.SQLException;
import java.util.List;

/**
 * 评论操作DAO
 * Created by wyk on 15/9/15.
 */
public interface CommentDao {

    /**
     * 插入用户
     * @param comment
     * @throws SQLException
     */
    void insert(Comment comment) throws TimeBuyException;

    /**
     * 根据指定Id查找News
     * @param comment
     * @return List<User>
     * @throws TimeBuyException
     */
    List<Comment> getCommentByNews(Comment comment) throws TimeBuyException;

}
