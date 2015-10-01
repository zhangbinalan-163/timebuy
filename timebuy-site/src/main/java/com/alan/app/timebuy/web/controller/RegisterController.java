package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.exception.UserExsistException;
import com.alan.app.timebuy.common.util.DateUtils;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.*;
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
import com.alan.app.timebuy.entity.ClientType;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.Date;

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

    /**
     * 用户第三方登录
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/other")
    @ResponseBody
    public String RegLogin(HttpServletRequest httpRequest) throws Exception {
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        String phone = request.getString("phone");
        String userName = request.getString("userName");
        int source = request.getInt("source");
        //检查参数
        if(!StringUtils.isLegalMobile(phone)){
            throw new InvalidParamException("该手机号不支持");
        }
        //获取设备信息
        DeviceInfo deviceInfo = new DeviceInfo();
        SidInfo sidInfo = new SidInfo();
        String clientVersion = request.getString("clientVersion");
        String did = request.getString("did");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date loginTime = DateUtils.StringToDate3(df.format(new Date()));
        if(request.getInt("clientType")==10) {
            /*deviceInfo.setClientType(ClientType.ANDROID_APP);
            deviceInfo.setDeviceId(request.getString("deviceId"));
            ClientVersion clientVersion = new ClientVersion();
            clientVersion.setMajor(Integer.parseInt(request.getString("major")));
            clientVersion.setMinor(Integer.parseInt(request.getString("minor")));
            clientVersion.setRevision(Integer.parseInt(request.getString("revision")));
            deviceInfo.setClientVersion(clientVersion);
            request.setDeviceInfo(deviceInfo);*/
            sidInfo.setSid(phone);
            sidInfo.setClientType(10);
            sidInfo.setClientVersion(clientVersion);
            sidInfo.setDid(did);
            sidInfo.setLoginTime(loginTime);
            sidService.addSid(sidInfo);
        }else if(request.getInt("clientType")==20){
            sidInfo.setSid(phone);
            sidInfo.setClientType(20);
            sidInfo.setClientVersion(clientVersion);
            sidInfo.setDid(did);
            sidInfo.setLoginTime(loginTime);
            sidService.addSid(sidInfo);
        }
        //检查该用户是否已经存在
        User oldUser = userService.getUserByPhone(phone);
        if(oldUser!=null){
            if(oldUser.getSource()==1){
                oldUser.setSource(source);
            }else if(oldUser.getSource()!=source){
                return createFailResponse(2002,null);
            }
            return createSuccessResponse(oldUser);

        }else {
            //执行业务
            User user  = new User();
            user.setPhone(phone);
            user.setUserName(userName);
            user.setSource(source);
            registerService.registerUser(user);
        }
        //生成响应信息
        return createSuccessResponse(null);
    }

    /**
     * 用户手机号登录
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/phone")
    @ResponseBody
    public String RegPhone(HttpServletRequest httpRequest) throws Exception {
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        String phone = request.getString("phone");
        int source = request.getInt("source");
        //检查参数
        if(!StringUtils.isLegalMobile(phone)){
            throw new InvalidParamException("该手机号不支持");
        }
        //获取设备信息
        DeviceInfo deviceInfo = new DeviceInfo();
        SidInfo sidInfo = new SidInfo();
        String clientVersion = request.getString("clientVersion");
        String did = request.getString("did");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date loginTime = DateUtils.StringToDate3(df.format(new Date()));
        if(request.getInt("clientType")==10) {
            /*deviceInfo.setClientType(ClientType.ANDROID_APP);
            deviceInfo.setDeviceId(request.getString("deviceId"));
            ClientVersion clientVersion = new ClientVersion();
            clientVersion.setMajor(Integer.parseInt(request.getString("major")));
            clientVersion.setMinor(Integer.parseInt(request.getString("minor")));
            clientVersion.setRevision(Integer.parseInt(request.getString("revision")));
            deviceInfo.setClientVersion(clientVersion);
            request.setDeviceInfo(deviceInfo);*/
            sidInfo.setSid(phone);
            sidInfo.setClientType(10);
            sidInfo.setClientVersion(clientVersion);
            sidInfo.setDid(did);
            sidInfo.setLoginTime(loginTime);
            sidService.addSid(sidInfo);
        }else if(request.getInt("clientType")==20){
            sidInfo.setSid(phone);
            sidInfo.setClientType(20);
            sidInfo.setClientVersion(clientVersion);
            sidInfo.setDid(did);
            sidInfo.setLoginTime(loginTime);
            sidService.addSid(sidInfo);
        }
        //检查该用户是否已经存在
        User oldUser = userService.getUserByPhone(phone);
        if(oldUser!=null){
            return createSuccessResponse(oldUser);
        }else {
            //执行业务
            User user = new User();
            user.setPhone(phone);
            user.setSource(1);
            registerService.registerUser(user);
        }
        //生成响应信息
        return createSuccessResponse(null);
    }
}
