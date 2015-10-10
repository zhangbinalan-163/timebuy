package com.alan.app.timebuy.service.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.dao.AppealDao;
import com.alan.app.timebuy.dao.ShareDao;
import com.alan.app.timebuy.entity.Appeal;
import com.alan.app.timebuy.entity.Share;
import com.alan.app.timebuy.service.AppealService;
import com.alan.app.timebuy.service.ShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 分享业务类实现
 * Created by wyk on 15/8/16.
 */
@Service(value = "appealServiceImpl")
public class AppealServiceImpl implements AppealService{

    @Resource(name = "appealDaoImpl")
    private AppealDao appealDao;

    /**
     * 增加一条申诉
     * @param appeal
     * @return
     */
    public void addAppeal(Appeal appeal) throws TimeBuyException{
       appealDao.insert(appeal);
    }
}
