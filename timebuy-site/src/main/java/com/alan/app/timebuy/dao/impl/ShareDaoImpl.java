package com.alan.app.timebuy.dao.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.dao.CommentDao;
import com.alan.app.timebuy.dao.ShareDao;
import com.alan.app.timebuy.dao.mapper.ShareMapper;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * ShareDao的具体实现
 * Created by wyk on 15/9/15.
 */
@Repository(value = "shareDaoImpl")
public class ShareDaoImpl implements ShareDao{

    @Autowired
    private ShareMapper shareMapper;

    @Override
    public void insert(Share share) throws TimeBuyException {
        try{
          shareMapper.insert(share);
        }catch (Exception e) {
            throw new TimeBuyException("插入评论异常",e);
        }
    }

}
