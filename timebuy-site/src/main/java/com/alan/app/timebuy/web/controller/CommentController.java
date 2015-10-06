package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.common.util.DateUtils;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.User;
import com.alan.app.timebuy.service.CommentService;
import com.alan.app.timebuy.service.UserService;
import com.alan.app.timebuy.web.vo.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * 用户评论控制器
 * Created by wyk on 15/9/15.
 */
@Controller
@RequestMapping(value = "/comment")
public class CommentController extends BaseController{

    @Resource(name = "commentServiceImpl")
    private CommentService commentService;
    /**
     * 插入评论接口
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publish")
    @ResponseBody
    public String addCommentPublish(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        int newsId = Integer.parseInt(request.getString("newsId"));
        int userId = Integer.parseInt(request.getString("userId"));
        Date commentTime = DateUtils.StringToDate3(request.getString("commentTime"));
        int speed = request.getInt("speed");
        int service = request.getInt("service");
        int doId = request.getInt("doId");
        Comment c = new Comment();
        c.setNewsId(newsId);
        c.setUserId(userId);
        c.setCommentTime(commentTime);
        c.setSpeed(speed);
        c.setService(service);
        c.setKind(0);
        c.setDoId(doId);

        if(newsId==0){
           return createFailResponse(1002,"消息获取失败");
        }else if(userId==0){
           return createFailResponse(1002,"用户信息获取失败");
        }else {
           try {
               commentService.addComment(c);
           }catch (Exception e){
               e.printStackTrace();
           }
            return  createSuccessResponse("1000",null);
        }
    }

    /**
     * 插入评论接口
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/respond")
    @ResponseBody
    public String addCommentRespond(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        int newsId = Integer.parseInt(request.getString("newsId"));
        int userId = Integer.parseInt(request.getString("userId"));
        Date commentTime = DateUtils.StringToDate3(request.getString("commentTime"));
        int service = request.getInt("service");
        int doId = request.getInt("doId");
        Comment c = new Comment();
        c.setNewsId(newsId);
        c.setUserId(userId);
        c.setCommentTime(commentTime);
        c.setService(service);
        c.setKind(1);
        c.setDoId(doId);

        if(newsId==0){
            return createFailResponse(1002,"消息获取失败");
        }else if(userId==0){
            return createFailResponse(1002,"用户信息获取失败");
        }else {
            try {
                commentService.addComment(c);
            }catch (Exception e){
                e.printStackTrace();
            }
            return  createSuccessResponse("1000",null);
        }
    }

    /**
     * 获取一条消息的评论的接口
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getComment")
    @ResponseBody
    public String getComment(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        int newsId = Integer.parseInt(request.getString("newsId"));

        if(newsId==0){
              return createFailResponse(1002,"获取用户信息失败");
        }else {
            Comment c = new Comment();
            c.setNewsId(newsId);
            return createSuccessResponse(commentService.getCommentByNews(c));
        }
    }

}
