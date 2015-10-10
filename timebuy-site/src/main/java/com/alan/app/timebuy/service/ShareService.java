package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.Share;

import java.util.List;

/**
 * 分享业务接口封装
 * Created by wyk on 15/9/15.
 */
public interface ShareService {
    /**
     * 添加分享
     * @param share
     * @throws TimeBuyException
     */
    void addShare(Share share) throws TimeBuyException;
}
