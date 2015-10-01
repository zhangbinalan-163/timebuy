package com.alan.app.timebuy.dao.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.dao.NewsDao;
import com.alan.app.timebuy.dao.UserDao;
import com.alan.app.timebuy.dao.mapper.NewsMapper;
import com.alan.app.timebuy.dao.mapper.UserMapper;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * NewsDao的具体实现
 * Created by wyk on 15/9/1.
 */
@Repository(value = "newsDaoImpl")
public class NewsDaoImpl implements NewsDao{

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public void insert(News news) throws TimeBuyException {
        try{
          newsMapper.insert(news);
        }catch (Exception e) {
            throw new TimeBuyException("插入消息异常",e);
        }
    }

    @Override
    public List<News> getNewsById(int userId) throws TimeBuyException{
        try {
            List<News> news = newsMapper.getNewsById(userId);
            return news;
        }catch (Exception e){
           throw  new TimeBuyException("查询指定消息异常",e);
        }
    }

    @Override
    public List<News> getNewsAll() throws TimeBuyException{
        try {
            return newsMapper.getNewsAll();
        }catch (Exception e){
            throw new TimeBuyException("查询所有消息异常",e);
        }
    }

    @Override
    public News selectNewsById(int newsId) throws TimeBuyException{
        try {
            return newsMapper.selectNewsById(newsId);
        }catch (Exception e){
           throw new TimeBuyException("查询单个消息异常",e);
        }
    }

    @Override
    public void accept(News news) throws TimeBuyException{
        try {
            newsMapper.accept(news);
        }catch (Exception e){
            throw new TimeBuyException("接收消息异常",e);
        }
    }

    @Override
    public List<News> scheduleNews(News news) throws TimeBuyException{
        try {
             return newsMapper.scheduleNews(news);
        }catch (Exception e){
            throw new  TimeBuyException("获取日常异常",e);
        }
    }

    @Override
    public List<News> accompany() throws TimeBuyException{
        try {
            return newsMapper.accompany();
        }catch (Exception e){
            throw new TimeBuyException("获取求助信息失败",e);
        }
    }

    @Override
    public List<News> run() throws TimeBuyException{
        try {
          return newsMapper.run();
        }catch (Exception e){
            throw new TimeBuyException("获取跑腿信息失败",e);
        }
    }

    @Override
    public List<News> study() throws TimeBuyException{
        try {
            return newsMapper.study();
        }catch (Exception e){
            throw new TimeBuyException("获取组局信息失败",e);
        }
    }

    @Override
    public List<News> welfare() throws TimeBuyException{
        try {
          return newsMapper.welfare();
        }catch (Exception e){
            throw new TimeBuyException("获取公益信息失败",e);
        }
    }

    @Override
    public void praise(News news) throws TimeBuyException{
        try {
          newsMapper.praise(news);
        }catch (Exception e){
            throw new TimeBuyException("点赞失败",e);
        }
    }
}
