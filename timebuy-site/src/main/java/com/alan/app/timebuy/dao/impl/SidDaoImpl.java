package com.alan.app.timebuy.dao.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.dao.SidDao;
import com.alan.app.timebuy.dao.UserDao;
import com.alan.app.timebuy.dao.mapper.SidMapper;
import com.alan.app.timebuy.dao.mapper.UserMapper;
import com.alan.app.timebuy.entity.SidInfo;
import com.alan.app.timebuy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * SIDDao的具体实现
 * Created by zhangbinalan on 15/8/15.
 */
@Repository(value = "sidDaoImpl")
public class SidDaoImpl implements SidDao{

    @Autowired
    private SidMapper sidMapper;

    @Override
    public void insert(SidInfo sidInfo) throws TimeBuyException {
        try {
            sidMapper.insert(sidInfo);
        } catch (Exception e) {
            throw new TimeBuyException("插入SID信息异常",e);
        }
    }

    @Override
    public SidInfo getBySid(String sid) throws TimeBuyException {
        try {
            return sidMapper.getBySid(sid);
        } catch (Exception e) {
            throw new TimeBuyException("查询SID信息异常",e);
        }
    }

    @Override
    public void update(SidInfo sidInfo) throws TimeBuyException {
        try {
            sidMapper.update(sidInfo);
        } catch (Exception e) {
            throw new TimeBuyException("修改SID信息异常",e);
        }
    }
}
