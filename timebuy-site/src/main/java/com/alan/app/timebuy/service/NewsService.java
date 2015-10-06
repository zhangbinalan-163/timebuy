package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户相关业务接口封装
 * Created by zhangbinalan on 15/8/16.
 */
public interface NewsService {
    /**
     * 添加消息
     * @param news
     * @throws TimeBuyException
     */
    void addNews(News news) throws TimeBuyException;

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
    News selectNewsById(int newsId) throws Exception;

    /**
     * 我的日常
     * @param  news
     * @throws SQLException
     */
    List<News> scheduleNews(News news) throws TimeBuyException;

    /**
     * 所有求助消息
     * @throws SQLException
     */
    List<News> accompany() throws TimeBuyException;

    /**
     * 所有跑腿消息
     * @throws SQLException
     */
    List<News> run() throws TimeBuyException;

    /**
     * 所有组局消息
     * @throws SQLException
     */
    List<News> study() throws TimeBuyException;

    /**
     * 所有公益消息
     * @throws SQLException
     */
    List<News> welfare() throws TimeBuyException;

    /**
     * 点赞
     * @throws SQLException
     */
    void praise(News news) throws TimeBuyException;

    /**
     * 修改
     * @throws SQLException
     */
    void update(News news) throws TimeBuyException;
}
