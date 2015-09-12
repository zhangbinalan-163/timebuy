package com.alan.app.timebuy.dao;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户消息的操作DAO
 * Created by wyk on 15/8/15.
 */
public interface NewsDao {

    /**
     * 插入用户
     * @param news
     * @return
     * @throws SQLException
     */
    void insert(News news) throws TimeBuyException;

    /**
     * 根据指定Id查找News
     * @param userId
     * @return List<User>
     * @throws TimeBuyException
     */
    List<News> getNewsById(int userId) throws TimeBuyException;

    /**
     * 获取指定所有News
     * @return List<News>
     * @throws SQLException
     */
    List<News> getNewsAll() throws  TimeBuyException;

    /**
     * 根据newsId获取一条news
     * @param  newsId
     * @throws SQLException
     */
    News selectNewsById(int newsId) throws TimeBuyException;

    /**
     * 接受消息接口
     * @param  news
     * @throws SQLException
     */
    void accept(News news) throws TimeBuyException;

    /**
     * 我的日常
     * @param  news
     * @throws SQLException
     */
    List<News> scheduleNews(News news) throws TimeBuyException;
}
