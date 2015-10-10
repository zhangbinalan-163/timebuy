package com.alan.app.timebuy.dao.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.dao.AppealDao;
import com.alan.app.timebuy.dao.ShareDao;
import com.alan.app.timebuy.dao.mapper.AppealMapper;
import com.alan.app.timebuy.entity.Appeal;
import com.alan.app.timebuy.entity.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * ShareDao的具体实现
 * Created by wyk on 15/9/15.
 */
@Repository(value = "appealDaoImpl")
public class AppealDaoImpl implements AppealDao{

    @Autowired
    private AppealMapper appealMapper;

    @Override
    public void insert(Appeal appeal) throws TimeBuyException {
        try{
            appealMapper.insert(appeal);
        }catch (Exception e) {
            throw new TimeBuyException("插入评论异常",e);
        }
    }

}
