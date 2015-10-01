package com.alan.app.timebuy.dao.mapper;

import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 消息的mapper
 * Created by wyk on 15/9/1.
 */
public interface NewsMapper {

    /**
     * 插入消息
     * @param news
     * @throws SQLException
     */
    void insert(News news) throws Exception;

    /**
     * 获取指定Id的News
     * @return List<User>
     * @throws SQLException
     */
    List<News> getNewsById(int userId) throws Exception;

    /**
     * 获取指定所有News
     * @return List<News>
     * @throws SQLException
     */
    List<News> getNewsAll() throws  Exception;

    /**
     * 根据newsId获取一条news
     * @param  newsId
     * @throws SQLException
     */
    News selectNewsById(int newsId) throws Exception;

    /**
     * 接受消息
     * @param  news
     * @throws SQLException
     */
    void accept(News news) throws Exception;

    /**
     * 我的日常
     * @param  news
     * @throws SQLException
     */
    List<News> scheduleNews(News news) throws Exception;

    /**
     * 所有求助消息
     * @throws SQLException
     */
    List<News> accompany() throws Exception;

    /**
     * 所有跑腿消息
     * @throws SQLException
     */
    List<News> run() throws Exception;

    /**
     * 所有组局消息
     * @throws SQLException
     */
    List<News> study() throws Exception;

    /**
     * 所有公益消息
     * @throws SQLException
     */
    List<News> welfare() throws Exception;

    /**
     * 点赞
     * @throws SQLException
     */
    void praise(News news) throws Exception;
}
