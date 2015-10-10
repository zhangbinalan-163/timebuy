package com.alan.app.timebuy.dao.mapper;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.Share;

import java.sql.SQLException;
import java.util.List;

/**
 * 分享的Mapper
 * Created by wyk on 15/10/9.
 */
public interface ShareMapper {

    /**
     * 插入分享
     * @param share
     * @throws SQLException
     */
    void insert(Share share) throws TimeBuyException;

}
