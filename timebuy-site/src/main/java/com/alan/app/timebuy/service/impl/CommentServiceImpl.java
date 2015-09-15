package com.alan.app.timebuy.service.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.dao.CommentDao;
import com.alan.app.timebuy.dao.NewsDao;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.service.CommentService;
import com.alan.app.timebuy.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户发送消息业务类实现
 * Created by wyk on 15/8/16.
 */
@Service(value = "commentServiceImpl")
public class CommentServiceImpl implements CommentService{

    @Resource(name = "commentDaoImpl")
    private CommentDao commentDao;

    /**
     * 增加一条评论
     * @param comment
     * @return
     */
    public void addComment(Comment comment) throws TimeBuyException{
       commentDao.insert(comment);
    }

    /**
     * 根据指定消息查找评论
     * @param comment
     * @return List<Comment>
     * @throws TimeBuyException
     */
    public List<Comment> getCommentByNews(Comment comment) throws TimeBuyException{
        return commentDao.getCommentByNews(comment);
    }

}
