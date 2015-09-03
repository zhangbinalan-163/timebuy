package com.alan.app.timebuy.dao;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.News;

import java.sql.SQLException;

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
}
