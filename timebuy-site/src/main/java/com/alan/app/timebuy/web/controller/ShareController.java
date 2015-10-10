package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.util.DateUtils;
import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.entity.Share;
import com.alan.app.timebuy.service.CommentService;
import com.alan.app.timebuy.service.NewsService;
import com.alan.app.timebuy.service.ShareService;
import com.alan.app.timebuy.web.vo.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 分享控制器
 * Created by wyk on 15/9/15.
 */
@Controller
@RequestMapping(value = "/share")
public class ShareController extends BaseController{

    @Resource(name = "shareServiceImpl")
    private ShareService shareService;

    @Resource(name = "newsServiceImpl")
    private NewsService newsService;
    /**
     * 插入分享接口
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    public String addShare(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        int newsId = Integer.parseInt(request.getString("newsId"));
        int userId = Integer.parseInt(request.getString("userId"));
        Date shareTime = DateUtils.StringToDate3(request.getString("shareTime"));
        String content = request.getString("content");
        Share share = new Share();
        share.setShareTime(shareTime);
        share.setContent(content);
        share.setNewsId(newsId);
        share.setUserId(userId);
        News news = new News();
        news.setNewsId(newsId);

        if(newsId==0){
           return createFailResponse(1002,"消息获取失败");
        }else if(userId==0){
           return createFailResponse(1002,"用户信息获取失败");
        }else {
           try {
              shareService.addShare(share);
               //消息的分享数加1
               newsService.share(news);
           }catch (Exception e){
               e.printStackTrace();
           }
            return  createSuccessResponse("1000",null);
        }
    }


}
