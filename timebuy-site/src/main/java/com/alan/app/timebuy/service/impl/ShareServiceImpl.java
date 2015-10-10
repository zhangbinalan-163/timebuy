package com.alan.app.timebuy.service.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.dao.CommentDao;
import com.alan.app.timebuy.dao.ShareDao;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.Share;
import com.alan.app.timebuy.service.CommentService;
import com.alan.app.timebuy.service.ShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分享业务类实现
 * Created by wyk on 15/8/16.
 */
@Service(value = "shareServiceImpl")
public class ShareServiceImpl implements ShareService{

    @Resource(name = "shareDaoImpl")
    private ShareDao shareDao;

    /**
     * 增加一条分享
     * @param share
     * @return
     */
    public void addShare(Share share) throws TimeBuyException{
       shareDao.insert(share);
    }
}
