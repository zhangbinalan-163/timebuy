package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.web.Constants;
import com.alan.app.timebuy.web.vo.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册相关功能控制器
 * Created by zhangbinalan on 15/8/15.
 */
@Controller
@RequestMapping({"/reg"})
public class RegisterController extends BaseController{
    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    /**
     * 用户注册请求响应方法
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping({"/user"})
    @ResponseBody
    public String userReg(HttpServletRequest httpRequest) throws Exception {
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        String phone = request.getString("phone");
        String code = request.getString("code");
        String password = request.getString("password");

        return createSuccessResponse(null);
    }

    /**
     * 用户注册发送短信响应方法
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping({"/sms"})
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
        //生成响应信息
        return createSuccessResponse(null);
    }
}
