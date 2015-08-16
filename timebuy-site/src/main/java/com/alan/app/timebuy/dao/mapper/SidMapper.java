package com.alan.app.timebuy.dao.mapper;

import com.alan.app.timebuy.entity.SidInfo;

/**
 * sid信息Mapper
 * Created by zhangbinalan on 15/8/16.
 */
public interface SidMapper {
    /**
     * 插入sid数据
     * @param sidInfo
     * @throws Exception
     */
    void insert(SidInfo sidInfo) throws Exception;

    /**
     * 根据sid号获得信息
     * @param sid
     * @return
     * @throws Exception
     */
    SidInfo getBySid(String sid) throws Exception;

    /**
     * 更新sid信息
     * @param sidInfo
     * @throws Exception
     */
    void update(SidInfo sidInfo) throws Exception;
}
