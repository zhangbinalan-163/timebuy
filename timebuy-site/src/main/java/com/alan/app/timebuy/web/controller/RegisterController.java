package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.exception.UserExsistException;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.User;
import com.alan.app.timebuy.service.RegisterService;
import com.alan.app.timebuy.service.SidService;
import com.alan.app.timebuy.service.UserService;
import com.alan.app.timebuy.web.Constants;
import com.alan.app.timebuy.web.vo.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 注册相关功能控制器
 * Created by zhangbinalan on 15/8/15.
 */
@Controller
@RequestMapping(value = "/reg")
public class RegisterController extends BaseController{

    @Resource(name = "registerServiceImpl")
    private RegisterService registerService;

    @Resource(name="sidServiceImpl")
    private SidService sidService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 用户注册请求响应方法
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user")
    @ResponseBody
    public String userReg(HttpServletRequest httpRequest) throws Exception {
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        String phone = request.getString("phone");
        String code = request.getString("code");
        String password = request.getString("password");//MD5之后

        //检查该用户是否已经存在
        User oldUser = userService.getUserByPhone(phone);
        if(oldUser!=null){
           return createFailResponse(2002,null);
        }

        /*if(userService.getUserByPhone(phone).getUserId() != null){
            return  createFailResponse(2002,null);
        }*/
        //检查参数
        if(!StringUtils.isLegalMobile(phone)){
            //手机号格式检查
            throw new InvalidParamException("该手机号不支持");
        }
        if(password.length()!=32){
            //检查MD5密码的长度
            throw new InvalidParamException("密码格式不正确");
        }
        //检查验证码
        registerService.checkUserRegSms(phone,code);
        //执行注册
        User userInfo=new User();
        userInfo.setPassword(password);
        userInfo.setPhone(phone);
        registerService.registerUser(userInfo);
        //注册后登录
        String sid=request.getSid();
        sidService.bindLoginUser(sid, userInfo);
        return createSuccessResponse(null);
    }

    /**
     * 用户注册发送短信响应方法
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sms")
    @ResponseBody
    public String sendUserRegSms(HttpServletRequest httpRequest) throws Exception {
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        String phone = request.getString("phone");
        //检查参数
        if(!StringUtils.isLegalMobile(phone)){
            throw new InvalidParamException("该手机号不支持");
        }
        //执行业务
        registerService.sendUserRegSms(phone);
        //生成响应信息
        return createSuccessResponse(null);
    }
}
