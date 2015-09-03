package com.alan.app.timebuy.service.impl;

import com.alan.app.timebuy.common.cache.ICache;
import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.util.CacheKeyUtils;
import com.alan.app.timebuy.dao.NewsDao;
import com.alan.app.timebuy.dao.UserDao;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.entity.User;
import com.alan.app.timebuy.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户发送消息业务类实现
 * Created by wyk on 15/8/16.
 */
@Service(value = "newsServiceImpl")
public class NewsServiceImpl implements NewsService{

    @Resource(name = "newsDaoImpl")
    private NewsDao newsDao;

    /**
     * 增加一条用户的消息
     * @param news
     * @return
     */
    public void addNews(News news) throws TimeBuyException{
       newsDao.insert(news);
    }

}
