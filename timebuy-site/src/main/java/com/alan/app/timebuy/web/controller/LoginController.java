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
 * ��¼��ع��ܿ�����
 * Created by wyk on 2015/8/28.
 */
@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController{

    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * �û���¼������Ӧ����
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user")
    @ResponseBody
    public String userLogin(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //��ȡ���ҵ�����
        String phone = request.getString("phone");
        User user = userService.getUserByPhone(phone);
        String password = request.getString("password");//MD5֮��
        //������
        if(!StringUtils.isLegalMobile(phone)){
            //�ֻ��Ÿ�ʽ���
            throw new InvalidParamException("���ֻ��Ų�֧��");
        }
        if(password.length()!=32){
            //���MD5����ĳ���
            throw new InvalidParamException("�����ʽ��֧��");
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
