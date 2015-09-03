package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.common.util.DateUtils;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.service.NewsService;
import com.alan.app.timebuy.web.vo.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 消息相关控制器
 * Created by wyk on 15/8/15.
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController extends BaseController{

    @Resource(name = "newsServiceImpl")
    private NewsService newsService;
    /**
     * 插入消息
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info")
    @ResponseBody
    public String news(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        String phone = request.getString("phone");
        String news = request.getString("news");
        Date starttime =  DateUtils.StringToDate2(request.getString("starttime"));
        Date finishtime =  DateUtils.StringToDate2(request.getString("finishtime"));
        String label = request.getString("label");
        float money = Float.parseFloat(request.getString("money"));
        String coordname = request.getString("coordname");
        float coordx = Float.parseFloat(request.getString("coordx"));
        float coordy = Float.parseFloat(request.getString("coordy"));
        String pic = request.getString("pic");
        int userid = Integer.parseInt(request.getString("userid"));

        News news1 = new News();
        news1.setPhone(phone);
        news1.setCoordname(coordname);
        news1.setCoordx(coordx);
        news1.setCoordy(coordy);
        news1.setFinishtime(finishtime);
        news1.setStarttime(starttime);
        news1.setMoney(money);
        news1.setLabel(label);
        news1.setNews(news);
        news1.setPic(pic);
        news1.setUserid(userid);

        newsService.addNews(news1);
        return createSuccessResponse(null);
  }
}
