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
}
