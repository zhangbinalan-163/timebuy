package com.alan.app.timebuy.service.impl;

import com.alan.app.timebuy.common.cache.ICache;
import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.util.CacheKeyUtils;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.dao.SidDao;
import com.alan.app.timebuy.entity.DeviceInfo;
import com.alan.app.timebuy.entity.SidInfo;
import com.alan.app.timebuy.entity.User;
import com.alan.app.timebuy.service.SidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * SID业务类实现接口
 * Created by zhangbinalan on 15/8/22.
 */
@Service(value = "sidServiceImpl")
public class SidServiceImpl implements SidService{
    private static Logger logger= LoggerFactory.getLogger(SidServiceImpl.class);

    private static final String SALT="timebuy@alan@love";

    private static final int CACHE_TIME=60*60;//缓存1小时

    private static final int EXP_TIME=2*7*24*60*60*1000;//两周

    @Resource(name = "memcachedCacheImpl")
    private ICache cache;

    @Resource(name = "sidDaoImpl")
    private SidDao sidDao;

    @Override
    public String generateSid(DeviceInfo deviceInfo) throws TimeBuyException {
        //其实就是一个MD5产生一个字符串
        //TODO 是否会重复，重复概率多大?
        StringBuffer sb = new StringBuffer();
        sb.append(deviceInfo.getDeviceId())
                .append("#")
                .append(deviceInfo.getClientType().getId())
                .append("#")
                .append(deviceInfo.getClientType().toString())
                .append("#")
                .append(SALT);
        return StringUtils.md5(sb.toString());
    }

    @Override
    public void bindLoginUser(String sid, User user) throws TimeBuyException {
        SidInfo sidInfo = getSid(sid);
        if (sidInfo == null) {
            throw new TimeBuyException("sid无效");
        }
        Calendar calendar = Calendar.getInstance();
        sidInfo.setLoginTime(calendar.getTime());
        calendar.add(Calendar.MILLISECOND, EXP_TIME);
        sidInfo.setExpireTime(calendar.getTime());
        sidInfo.setUserId(user.getUserId());
        //修改数据库
        updateSid(sidInfo);
        logger.info("bind sid with user success,sid={},userid={}", sidInfo.getSid(), user.getUserId());
    }

    @Override
    public void refreshSid(DeviceInfo deviceInfo, String sid) throws TimeBuyException {
        SidInfo sidInfo = getSid(sid);
        if(sidInfo==null){
            //新纪录
            sidInfo = new SidInfo();
            sidInfo.setClientType(deviceInfo.getClientType().getId());
            sidInfo.setDid(deviceInfo.getDeviceId());
            sidInfo.setClientVersion(deviceInfo.getClientVersion().toString());
            sidInfo.setSid(sid);
            sidInfo.setStatus(SidInfo.STATUS_NORMAL);
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            sidInfo.setCreateTime(calendar.getTime());
            addSid(sidInfo);
        } else if (checkHasLogin(sidInfo)) {
            //已经绑定登录
            if (!checkLoginExpire(sidInfo)) {
                //登录没有过期
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sidInfo.getExpireTime());
                calendar.add(Calendar.MILLISECOND, EXP_TIME);//
                sidInfo.setExpireTime(calendar.getTime());
                updateSid(sidInfo);
            } else {
                //登录已经过期
                sidInfo.setExpireTime(null);
                sidInfo.setUserId(0L);
                sidInfo.setLoginTime(null);
                updateSid(sidInfo);
            }
        }
    }

    /**
     * 更新sid信息
     * @param sidInfo
     * @throws TimeBuyException
     */
    private void updateSid(SidInfo sidInfo) throws TimeBuyException {
        //修改数据
        sidDao.update(sidInfo);
        //清理缓存
        String key=CacheKeyUtils.SIDCache.getKeyBySid(sidInfo.getSid());
        cache.delete(key);
    }
    /**
     * 检查登录是否已经过期
     * @param sidInfo
     * @return
     * @throws TimeBuyException
     */
    private boolean checkLoginExpire(SidInfo sidInfo) throws TimeBuyException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sidInfo.getExpireTime());
        return System.currentTimeMillis() > calendar.getTimeInMillis();
    }

    /**
     * 检查是否已经绑定登录
     * @param sidInfo
     * @return
     * @throws TimeBuyException
     */
    private boolean checkHasLogin(SidInfo sidInfo) throws TimeBuyException {
        return sidInfo.getUserId().intValue() != 0;
    }

    @Override
    public SidInfo getSid(String sid) throws TimeBuyException {
        //缓存先取
        String key=CacheKeyUtils.SIDCache.getKeyBySid(sid);
        SidInfo sidInfo = cache.getObject(key, SidInfo.class);
        if(sidInfo!=null){
            return sidInfo;
        }
        //
        sidInfo=sidDao.getBySid(sid);
        if(sidInfo==null){
            //设置缓存
            cache.setObject(key, sidInfo, CACHE_TIME);
        }
        return sidInfo;
    }

    @Override
    public void addSid(SidInfo sidInfo) throws TimeBuyException {
        sidDao.insert(sidInfo);
    }
}
