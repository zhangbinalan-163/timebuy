package com.alan.app.timebuy.dao;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.Share;

import java.sql.SQLException;
import java.util.List;

/**
 * 分享DAO
 * Created by wyk on 15/9/15.
 */
public interface ShareDao {

    /**
     * 插入分享
     * @param share
     * @throws SQLException
     */
    void insert(Share share) throws TimeBuyException;


}
