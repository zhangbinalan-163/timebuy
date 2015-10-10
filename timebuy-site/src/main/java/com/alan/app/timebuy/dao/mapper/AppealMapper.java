package com.alan.app.timebuy.dao.mapper;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.Appeal;
import com.alan.app.timebuy.entity.Share;

import java.sql.SQLException;

/**
 * 分享的Mapper
 * Created by wyk on 15/10/9.
 */
public interface AppealMapper {

    /**
     * 插入申诉
     * @param appeal
     * @throws SQLException
     */
    void insert(Appeal appeal) throws TimeBuyException;

}
