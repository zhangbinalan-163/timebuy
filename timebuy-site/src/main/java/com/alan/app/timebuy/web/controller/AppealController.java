package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.util.DateUtils;
import com.alan.app.timebuy.entity.Appeal;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.entity.Share;
import com.alan.app.timebuy.service.AppealService;
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
 * 申诉控制器
 * Created by wyk on 15/9/15.
 */
@Controller
@RequestMapping(value = "/appeal")
public class AppealController extends BaseController{

    @Resource(name = "appealServiceImpl")
    private AppealService appealService;

    @Resource(name = "newsServiceImpl")
    private NewsService newsService;
    /**
     * 插入申诉接口
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    public String addAppeal(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        int newsId = Integer.parseInt(request.getString("newsId"));
        Date appealTime = DateUtils.StringToDate3(request.getString("appealTime"));
        String content = request.getString("content");
        Appeal appeal = new Appeal();
        appeal.setNewsId(newsId);
        appeal.setContent(content);
        appeal.setAppealTime(appealTime);
        News news = new News();
        news.setNewsId(newsId);
        if(newsId==0){
           return createFailResponse(1002,"消息获取失败");
        }else {
           try {
               appealService.addAppeal(appeal);
               newsService.appeal(news);
           }catch (Exception e){
               e.printStackTrace();
           }
            return  createSuccessResponse("1000",null);
        }
    }


}
