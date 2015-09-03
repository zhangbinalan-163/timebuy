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

}
