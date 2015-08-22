package com.alan.app.timebuy.service.impl;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.exception.UserExsistException;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.User;
import com.alan.app.timebuy.service.RegisterService;
import com.alan.app.timebuy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 注册业务实现类
 * Created by zhangbinalan on 15/8/15.
 */
@Service(value = "registerServiceImpl")
public class RegisterServiceImpl implements RegisterService {
    private static Logger logger= LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Override
    public void registerUser(User user) throws TimeBuyException {
        //检查该用户是否已经存在
        User oldUser = userService.getUserByPhone(user.getPhone());
        if(oldUser!=null){
            logger.warn("register user fail,user exsist,phone={}", user.getPhone());
            throw new UserExsistException("手机号已经注册");
        }
        //加密密码
        int salt = genSalt();
        user.setSalt(salt);
        String password = user.getPassword();//调用者确保已经md5过了
        password = StringUtils.md5(salt + "#" + password);
        user.setPassword(password);
        //插入数据
        userService.addUser(user);
        logger.info("register user success,phone={}", user.getPhone());
    }
    //生成密码盐
    private int genSalt() {
        return (int) (Math.random() * Integer.MAX_VALUE);
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
