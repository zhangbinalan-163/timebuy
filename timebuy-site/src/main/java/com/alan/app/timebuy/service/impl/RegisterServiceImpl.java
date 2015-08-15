package com.alan.app.timebuy.service.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.entity.User;
import com.alan.app.timebuy.service.RegisterService;

/**
 * 注册业务实现类
 * Created by zhangbinalan on 15/8/15.
 */
public class RegisterServiceImpl implements RegisterService {

    @Override
    public void registerUser(User user) throws TimeBuyException {
        //检查该用户是否已经存在
        //加密密码
        //插入数据
    }

    @Override
    public void sendUserRegSms(String phone) throws TimeBuyException {
        //频率控制
        //生成验证码
        //发送短信
        //记录缓存(没必要持久化,大不了重发就OK)
    }

    @Override
    public void checkUserRegSms(String phone, String code) throws TimeBuyException {
        //查询缓存获得验证码信息
        //TODO 如果错误，记录错误信息，验证错误次数过多拒绝
    }
}
