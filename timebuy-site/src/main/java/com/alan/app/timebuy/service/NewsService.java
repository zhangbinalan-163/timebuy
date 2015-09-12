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
     * 接受消息接口
     * @param  news
     * @throws SQLException
     */
    void accept(News news) throws Exception;

    /**
     * 我的日常
     * @param  news
     * @throws SQLException
     */
    List<News> scheduleNews(News news) throws TimeBuyException;

}
