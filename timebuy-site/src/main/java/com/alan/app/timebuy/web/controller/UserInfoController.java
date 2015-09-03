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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户信息的查询和修改控制器
 * Created by wyk on 15/8/15.
 */
@Controller
@RequestMapping(value = "/user")
public class UserInfoController extends BaseController{

    @Resource(name = "userServiceImpl")
    private UserService userService;
    /**
     * 用户信息请求响应方法
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    public String userInfo(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        String phone = request.getString("phone");
        //检查参数
        if(!StringUtils.isLegalMobile(phone)){
            //手机号格式检查
            throw new InvalidParamException("该手机号不支持");
        }
        //执行获取资料
        User user= userService.getUserByPhone(phone);
        if(userService.getUserByPhone(phone) == null || userService.getUserByPhone(phone).equals("")){
            return createFailResponse(2004, null);
        }else{
            return createSuccessResponse(user);
        }
    }

    /**
     * 用户信息修改响应方法
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String userUpdate(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //头像上传
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
        MultipartFile file  =  multipartRequest.getFile("headIcon");
        String fileName = file.getOriginalFilename();
        String path="upload"+File.separator+fileName;
        File targetFile = new File(path);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取相关业务参数
        int userId = Integer.parseInt(request.getString("userId"));
        String nickName = request.getString("nickName");
        String headIcon = fileName;
        int sex  = Integer.parseInt(request.getString("sex"));
        Date birthDay = DateUtils.StringToDate(request.getString("birthDay"));
        String profession = request.getString("profession");
        String signature = request.getString("signature");
        String phone = request.getString("phone");
        //取得返回对象
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
