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

    /**
     * 根据指定Id查找News
     * @param userId
     * @return List<User>
     * @throws TimeBuyException
     */
    public List<News> getNewsById(int userId) throws TimeBuyException{
        return newsDao.getNewsById(userId);
    }

    /**
     * 获取指定所有News
     * @return List<News>
     * @throws TimeBuyException
     */
    public List<News> getNewsAll() throws  TimeBuyException{
       return newsDao.getNewsAll();
    }

    /**
     * 根据newsId获取一条news
     * @param  newsId
     * @throws TimeBuyException
     */
    public News selectNewsById(int newsId) throws TimeBuyException{
        return newsDao.selectNewsById(newsId);
    }

    /**
     * 我的日常
     * @param  news
     * @throws TimeBuyException
     */
    public List<News> scheduleNews(News news) throws TimeBuyException{
        return newsDao.scheduleNews(news);
    }

    /**
     * 所有求助消息
     * @throws TimeBuyException
     */
    public List<News> accompany(News news) throws TimeBuyException{
        return newsDao.accompany(news);
    }

    /**
     * 所有跑腿消息
     * @throws TimeBuyException
     */
    public List<News> run(News news) throws TimeBuyException{
        return newsDao.run(news);
    }

    /**
     * 所有组局消息
     * @throws TimeBuyException
     */
    public List<News> study(News news) throws TimeBuyException{
        return newsDao.study(news);
    }

    /**
     * 所有公益消息
     * @throws TimeBuyException
     */
    public List<News> welfare() throws TimeBuyException{
        return newsDao.welfare();
    }

    /**
     * 分享
     * @throws TimeBuyException
     */
     public void share(News news) throws TimeBuyException{
        newsDao.share(news);
     }

    /**
     * 申诉
     * @throws TimeBuyException
     */
    public void appeal(News news) throws TimeBuyException{
        newsDao.appeal(news);
    }

    /**
     * 修改消息
     * @throws TimeBuyException
     */
    public void update(News news) throws TimeBuyException{
        newsDao.update(news);
    }

    /**
     * 所有在线消息
     * @throws TimeBuyException
     */
    public List<News> newsOnline(News news) throws TimeBuyException{
        return newsDao.newsOnline(news);
    }

    /**
     * 所有个人在线消息
     * @throws TimeBuyException
     */
    public List<News> myNewsOnline(News news) throws TimeBuyException{
        return newsDao.myNewsOnline(news);
    }

    /**
     * 查询个人不同状态消息
     * @throws TimeBuyException
     */
    public List<News> selectNewsTag(News news) throws TimeBuyException{
        return newsDao.selectNewsTag(news);
    }

}
