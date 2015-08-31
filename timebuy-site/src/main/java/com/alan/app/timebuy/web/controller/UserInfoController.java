package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.service.UserService;
import com.alan.app.timebuy.web.vo.Request;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.common.util.DateUtils;
import com.alan.app.timebuy.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * �û���Ϣ�Ĳ�ѯ���޸Ŀ�����
 * Created by wyk on 15/8/15.
 */
@Controller
@RequestMapping(value = "/user")
public class UserInfoController extends BaseController{

    @Resource(name = "userServiceImpl")
    private UserService userService;
    /**
     * �û���Ϣ������Ӧ����
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    public String userInfo(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //��ȡ���ҵ�����
        String phone = request.getString("phone");
        //������
        if(!StringUtils.isLegalMobile(phone)){
            //�ֻ��Ÿ�ʽ���
            throw new InvalidParamException("���ֻ��Ų�֧��");
        }
        //ִ�л�ȡ����
        User user= userService.getUserByPhone(phone);
        if(userService.getUserByPhone(phone) == null || userService.getUserByPhone(phone).equals("")){
            return createFailResponse(2004, null);
        }else{
            return createSuccessResponse(user);
        }
    }

    /**
     * �û���Ϣ�޸���Ӧ����
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String userUpdate(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //��ȡ���ҵ�����
        int userId = Integer.parseInt(request.getString("userId"));
        String nickName = request.getString("nickName");
        String headIcon = request.getString("headIcon");
        int sex  = Integer.parseInt(request.getString("sex"));
        Date birthDay = DateUtils.StringToDate(request.getString("birthDay"));
        String profession = request.getString("profession");
        String signature = request.getString("signature");
        String phone = request.getString("phone");
        //ȡ�÷��ض���
        User user = userService.getUserById(userId);
        if(request.getString("userId") != null){
            user.setNickName(nickName);
            user.setHeadIcon(headIcon);
            user.setSex(sex);
            user.setBirthDay(birthDay);
            user.setProfession(profession);
            user.setSignature(signature);
            user.setPhone(phone);
            userService.updateUser(user);
            return createSuccessResponse(null);
        }else {
            return createFailResponse(2005,null);
        }
}

}
