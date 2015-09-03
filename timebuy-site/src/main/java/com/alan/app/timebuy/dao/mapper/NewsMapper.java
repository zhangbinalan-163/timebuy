package com.alan.app.timebuy.dao.mapper;

import com.alan.app.timebuy.entity.News;

import java.sql.SQLException;

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

}
