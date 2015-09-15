package com.alan.app.timebuy.dao.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.dao.CommentDao;
import com.alan.app.timebuy.dao.mapper.CommentMapper;
import com.alan.app.timebuy.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * CommentDao的具体实现
 * Created by wyk on 15/9/15.
 */
@Repository(value = "commentDaoImpl")
public class CommentDaoImpl implements CommentDao{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insert(Comment comment) throws TimeBuyException {
        try{
          commentMapper.insert(comment);
        }catch (Exception e) {
            throw new TimeBuyException("插入评论异常",e);
        }
    }

    @Override
    public List<Comment> getCommentByNews(Comment comment) throws TimeBuyException{
        try{
           return commentMapper.getCommentByNews(comment);
        }catch (Exception e){
            throw new TimeBuyException("获取消息评论异常",e);
        }
    }

}
