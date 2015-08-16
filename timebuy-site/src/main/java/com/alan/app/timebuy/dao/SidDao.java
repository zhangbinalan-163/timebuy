package com.alan.app.timebuy.dao;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.SidInfo;

/**
 * SID操作DAO
 * Created by zhangbinalan on 15/8/16.
 */
public interface SidDao {
    /**
     * 插入sid数据
     * @param sidInfo
     * @throws Exception
     */
    void insert(SidInfo sidInfo) throws TimeBuyException;

    /**
     * 根据sid号获得信息
     * @param sid
     * @return
     * @throws Exception
     */
    SidInfo getBySid(String sid) throws TimeBuyException;

    /**
     * 更新sid信息
     * @param sidInfo
     * @throws Exception
     */
    void update(SidInfo sidInfo) throws TimeBuyException;
}
