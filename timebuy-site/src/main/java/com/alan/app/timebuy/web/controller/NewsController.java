package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.common.util.DateUtils;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.common.util.Page;
import com.alan.app.timebuy.common.util.PageUtils;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.service.NewsService;
import com.alan.app.timebuy.web.vo.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

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
    public String news(@RequestParam("pics") MultipartFile[] pics,HttpServletRequest httpRequest) throws Exception{

        StringBuffer picture = new StringBuffer();

        for(MultipartFile pic : pics){
            if(pic.isEmpty()){
                System.out.println("文件未上传");
            }else{
                //生成文件名
                String fileName1 = pic.getOriginalFilename();
                String prefix=fileName1.substring(fileName1.lastIndexOf(".") + 1);
                int x=(int)(Math.random()*100);
                //生成文件名为时间戳+随机数+文件后缀名
                String pictu = new Date().getTime() + String.valueOf(x) +"."+ prefix;
                //将所有文件名以“，”分开
                picture.append(pictu).append(",");
                String path = httpRequest.getSession().getServletContext().getRealPath("/")+"upload"+File.separator+pic.getName();
                File targetFile = new File(path);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                //保存
                try {
                    pic.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Request request = getRequest(httpRequest);
        //获取相关业务参数
        String phone = request.getString("phone");
        String news = request.getString("news");
        Date starttime =  DateUtils.StringToDate2(request.getString("starttime"));
        Date finishtime =  DateUtils.StringToDate2(request.getString("finishtime"));
        int label = Integer.parseInt(request.getString("label"));
        float money = Float.parseFloat(request.getString("money"));
        String coordname = request.getString("coordname");
        double coordx = Float.parseFloat(request.getString("coordx"));
        double coordy = Float.parseFloat(request.getString("coordy"));
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
        news1.setPic(picture.toString());
        news1.setUserid(userid);
        news1.setPraise(0);

        newsService.addNews(news1);
        return createSuccessResponse("1000",null);
  }

    /**
     * 查询某个userId的全部News
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user")
    @ResponseBody
    public String userNews(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        int userId = Integer.parseInt(request.getString("userId"));
        List<News> l = newsService.getNewsById(userId);
        if (l.size()==0){
              return createFailResponse(1002,"无消息");
        }else {
            return createSuccessResponse(l);
        }
    }

    /**
     * 查询全部News
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/all")
    @ResponseBody
    public String allNews(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关参数
        Date timeNow = DateUtils.StringToDate3(request.getString("timeNow"));
        int userId = Integer.parseInt(request.getString("userId"));
        double coordx = Float.parseFloat(request.getString("coordx"));
        double coordy = Float.parseFloat(request.getString("coordy"));

        List<News> l = newsService.getNewsAll();

        //对所有消息进行筛选排
        for (int i = l.size()-1; i >= 0; i--){
            double distance = (l.get(i).getCoordx()-coordx)*(l.get(i).getCoordx()-coordx) + (l.get(i).getCoordy()-coordy)*(l.get(i).getCoordy()-coordy);
            Date timeEarly = DateUtils.StringToDate3(request.getString("starttime"));
            long temp = timeNow.getTime() - timeEarly.getTime();//相差秒数
            if (distance<9000000){
               l.remove(i);
            }else if (temp>3600){
               l.remove(i);
            }
        }

        // 获得记录数
        int totalCount = l.size();
        int currentPage = Integer.parseInt(request.getString("currentPage"));
        // 设置分页信息
        Page page = PageUtils.createPage(5, totalCount, currentPage);
        //取得该频道下的记录
        List<News> l2 = new ArrayList<News>();
        for(int i=0;i<page.getEveryPage();i++){
            l2.add(i,l.get((currentPage-1)*5+i));
        }
        if(l.size()==0){
            return createFailResponse(1002,"无消息");
        }else {
            return createSuccessResponse(l2);
        }
    }

    /**
     * 查询某个newsId的news
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/one")
    @ResponseBody
    public String oneNews(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        int newsId = Integer.parseInt(request.getString("newsId"));
        News n = newsService.selectNewsById(newsId);
        if (n == null){
           return createFailResponse(1002,"消息无内容");
        }else {
            return createSuccessResponse(n);
        }
    }

    /**
     * 接受消息
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/accept")
    @ResponseBody
    public String accept(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        int acceptUserId = Integer.parseInt(request.getString("acceptUserId"));
        int newsid = Integer.parseInt(request.getString("newsId"));
        if(acceptUserId==0 ||newsid==0 ){
               return createFailResponse(1002, "获取参数失败");
        }
        News news = new News();
        news.setNewsId(newsid);
        news.setAcceptUserid(acceptUserId);
        newsService.accept(news);
        return createSuccessResponse("1000",null);
    }

    /**
     * 我的日常
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/schedule")
    @ResponseBody
    public String scheduleNews(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关业务参数
        int userId = Integer.parseInt(request.getString("userId"));
        if(userId==0){
             return createFailResponse(1002,"获取参数失败");
        }
        News news = new News();
        news.setUserid(userId);
        List<News> l = newsService.scheduleNews(news);
        return createSuccessResponse(l);
    }

    /**
     * 所有求助消息
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/help")
    @ResponseBody
    public String help(HttpServletRequest httpRequest) throws Exception{
         Request request = getRequest(httpRequest);
         List<News> l = newsService.help();
        if(l.size()==0){
            return createFailResponse(2006,null);
        }else {
            return createSuccessResponse(l);
        }
    }

    /**
     * 所有跑腿消息
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/run")
    @ResponseBody
    public String run(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        List<News> l = newsService.run();
        if(l.size()==0){
            return createFailResponse(2006,null);
        }else {
            return createSuccessResponse(l);
        }
    }

    /**
     * 所有组局消息
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/group")
    @ResponseBody
    public String group(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        List<News> l = newsService.group();
        if(l.size()==0){
            return createFailResponse(2006,null);
        }else {
            return createSuccessResponse(l);
        }
    }

    /**
     * 所有公益消息
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/welfare")
    @ResponseBody
    public String welfare(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        List<News> l = newsService.welfare();
        if(l.size()==0){
            return createFailResponse(2006,null);
        }else {
            return createSuccessResponse(l);
        }
    }
}
