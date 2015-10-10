package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.Appeal;
import com.alan.app.timebuy.entity.Share;

/**
 * 分享业务接口封装
 * Created by wyk on 15/9/15.
 */
public interface AppealService {
    /**
     * 添加分享
     * @param appeal
     * @throws TimeBuyException
     */
    void addAppeal(Appeal appeal) throws TimeBuyException;
}
