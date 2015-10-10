package com.alan.app.timebuy.dao;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.Appeal;
import com.alan.app.timebuy.entity.Share;

import java.sql.SQLException;

/**
 * 分享DAO
 * Created by wyk on 15/9/15.
 */
public interface AppealDao {

    /**
     * 插入申诉
     * @param appeal
     * @throws SQLException
     */
    void insert(Appeal appeal) throws TimeBuyException;

}
