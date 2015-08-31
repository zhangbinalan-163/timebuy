package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.User;
import com.alan.app.timebuy.service.UserService;
import com.alan.app.timebuy.web.vo.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录相关功能控制器
 * Created by wyk on 2015/8/28.
 */
@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController{

    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 用户登录请求响应方法
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user")
    @ResponseBody
    public String userLogin(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        String phone = request.getString("phone");
        User user = userService.getUserByPhone(phone);
        String password = request.getString("password");//MD5之后
        //检查参数
        if(!StringUtils.isLegalMobile(phone)){
            //手机号格式检查
            throw new InvalidParamException("该手机号不支持");
        }
        if(password.length()!=32){
            //检查MD5密码的长度
            throw new InvalidParamException("密码格式不支持");
        }
        if(userService.getUserByPhone(phone) == null || userService.getUserByPhone(phone).equals("")){
              return createFailResponse(2003,null);
        }
        String pw=StringUtils.md5(userService.getUserByPhone(phone).getSalt()+"#"+password);
        if(pw.equalsIgnoreCase(userService.getUserByPhone(phone).getPassword())){
                return createSuccessResponse(user);
        }else{
            return createFailResponse(2004,null);
        }
    }
}
