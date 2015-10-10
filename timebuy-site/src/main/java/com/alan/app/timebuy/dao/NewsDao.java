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
     * 我的日常
     * @param  news
     * @throws SQLException
     */
    List<News> scheduleNews(News news) throws TimeBuyException;

    /**
     * 所有陪伴消息
     * @throws SQLException
     */
    List<News> accompany(News news) throws TimeBuyException;

    /**
     * 所有跑腿消息
     * @throws SQLException
     */
    List<News> run(News news) throws TimeBuyException;

    /**
     * 所有学霸消息
     * @throws SQLException
     */
    List<News> study(News news) throws TimeBuyException;

    /**
     * 所有公益消息
     * @throws SQLException
     */
    List<News> welfare() throws TimeBuyException;

    /**
     * 分享
     * @throws SQLException
     */
    void share(News news) throws TimeBuyException;

    /**
     * 申诉
     * @throws SQLException
     */
    void appeal(News news) throws TimeBuyException;

    /**
     * 点赞
     * @throws SQLException
     */
    void update(News news) throws TimeBuyException;

    /**
     * 查询所有有用的消息
     * @throws SQLException
     */
    List<News> newsOnline(News news) throws TimeBuyException;

    /**
     * 查询个人所有有用的消息
     * @throws SQLException
     */
    List<News> myNewsOnline(News news) throws TimeBuyException;

    /**
     * 查询个人不同状态的消息
     * @throws SQLException
     */
    List<News> selectNewsTag(News news) throws TimeBuyException;
}
