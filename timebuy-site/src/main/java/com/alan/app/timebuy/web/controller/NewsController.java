package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.InvalidParamException;
import com.alan.app.timebuy.common.exception.TimeBuyException;
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
import java.util.*;

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
        news1.setShare(0);
        news1.setTag(0);

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
     * 查询全部在线News
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/online")
    @ResponseBody
    public String newsOnline(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关参数
        Date timeNow = DateUtils.StringToDate3(request.getString("timeNow"));
        double coordx = Float.parseFloat(request.getString("coordx"));
        double coordy = Float.parseFloat(request.getString("coordy"));

        News news =new News();
        news.setAccepttime(timeNow);
        List<News> l = newsService.newsOnline(news);
        HashMap<Integer, News> m = new HashMap<Integer, News>();
        //对所有消息进行筛选排序
        for (int i = l.size()-1; i >= 0; i--){
            Double distance = (l.get(i).getCoordx()-coordx)*(l.get(i).getCoordx()-coordx) + (l.get(i).getCoordy()-coordy)*(l.get(i).getCoordy()-coordy);
            int distanceInt= Integer.parseInt(new java.text.DecimalFormat("0").format(distance));
            m.put(distanceInt,l.get(i));
        }
        //排序
        Object[] key =  m.keySet().toArray();
        Arrays.sort(key);
        List<News> l2 = new ArrayList<News>();
        for(int i=0;i<key.length;i++){
            l2.add(i,m.get(key[i]));
        }
        // 获得记录数
        int totalCount = l2.size();
        int currentPage = Integer.parseInt(request.getString("currentPage"));
        // 设置分页信息
        Page page = PageUtils.createPage(5, totalCount, currentPage);
        int everyPage = page.getEveryPage();
        //取得该频道下的记录
        if(l2.size()<page.getEveryPage()){
            everyPage = l2.size();
        }
        List<News> l3 = new ArrayList<News>();
        for(int i=0;i<everyPage;i++){
            l3.add(i,l2.get((currentPage-1)*5+i));
        }
        if(l2.size()==0){
            return createFailResponse(1002,"无消息");
        }else {
            return createSuccessResponse(l2);
        }
    }

    /**
     * 查询全部在线陪伴News
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/onlineAccompany")
    @ResponseBody
    public String newsOnlineAccompany(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关参数
        Date timeNow = DateUtils.StringToDate3(request.getString("timeNow"));
        double coordx = Float.parseFloat(request.getString("coordx"));
        double coordy = Float.parseFloat(request.getString("coordy"));

        News news =new News();
        news.setAccepttime(timeNow);
        List<News> l = newsService.accompany(news);
        HashMap<Integer, News> m = new HashMap<Integer, News>();
        //对所有消息进行筛选排序
        for (int i = l.size()-1; i >= 0; i--){
            Double distance = (l.get(i).getCoordx()-coordx)*(l.get(i).getCoordx()-coordx) + (l.get(i).getCoordy()-coordy)*(l.get(i).getCoordy()-coordy);
            int distanceInt= Integer.parseInt(new java.text.DecimalFormat("0").format(distance));
            m.put(distanceInt,l.get(i));
        }
        //排序
        Object[] key =  m.keySet().toArray();
        Arrays.sort(key);
        List<News> l2 = new ArrayList<News>();
        for(int i=0;i<key.length;i++){
            l2.add(i,m.get(key[i]));
        }
        // 获得记录数
        int totalCount = l2.size();
        int currentPage = Integer.parseInt(request.getString("currentPage"));
        // 设置分页信息
        Page page = PageUtils.createPage(5, totalCount, currentPage);
        int everyPage = page.getEveryPage();
        //取得该频道下的记录
        if(l2.size()<page.getEveryPage()){
            everyPage = l2.size();
        }
        List<News> l3 = new ArrayList<News>();
        for(int i=0;i<everyPage;i++){
            l3.add(i,l2.get((currentPage-1)*5+i));
        }
        if(l2.size()==0){
            return createFailResponse(1002,"无消息");
        }else {
            return createSuccessResponse(l2);
        }
    }

    /**
     * 查询全部在线跑腿News
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/onlineRun")
    @ResponseBody
    public String newsOnlineRun(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关参数
        Date timeNow = DateUtils.StringToDate3(request.getString("timeNow"));
        double coordx = Float.parseFloat(request.getString("coordx"));
        double coordy = Float.parseFloat(request.getString("coordy"));

        News news =new News();
        news.setAccepttime(timeNow);
        List<News> l = newsService.accompany(news);
        HashMap<Integer, News> m = new HashMap<Integer, News>();
        //对所有消息进行筛选排序
        for (int i = l.size()-1; i >= 0; i--){
            Double distance = (l.get(i).getCoordx()-coordx)*(l.get(i).getCoordx()-coordx) + (l.get(i).getCoordy()-coordy)*(l.get(i).getCoordy()-coordy);
            int distanceInt= Integer.parseInt(new java.text.DecimalFormat("0").format(distance));
            m.put(distanceInt,l.get(i));
        }
        //排序
        Object[] key =  m.keySet().toArray();
        Arrays.sort(key);
        List<News> l2 = new ArrayList<News>();
        for(int i=0;i<key.length;i++){
            l2.add(i,m.get(key[i]));
        }
        // 获得记录数
        int totalCount = l2.size();
        int currentPage = Integer.parseInt(request.getString("currentPage"));
        // 设置分页信息
        Page page = PageUtils.createPage(5, totalCount, currentPage);
        int everyPage = page.getEveryPage();
        //取得该频道下的记录
        if(l2.size()<page.getEveryPage()){
            everyPage = l2.size();
        }
        List<News> l3 = new ArrayList<News>();
        for(int i=0;i<everyPage;i++){
            l3.add(i,l2.get((currentPage-1)*5+i));
        }
        if(l2.size()==0){
            return createFailResponse(1002,"无消息");
        }else {
            return createSuccessResponse(l2);
        }
    }

    /**
     * 查询全部在线学霸News
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/onlineStudy")
    @ResponseBody
    public String newsOnlineStudy(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        //获取相关参数
        Date timeNow = DateUtils.StringToDate3(request.getString("timeNow"));
        double coordx = Float.parseFloat(request.getString("coordx"));
        double coordy = Float.parseFloat(request.getString("coordy"));

        News news =new News();
        news.setAccepttime(timeNow);
        List<News> l = newsService.accompany(news);
        HashMap<Integer, News> m = new HashMap<Integer, News>();
        //对所有消息进行筛选排序
        for (int i = l.size()-1; i >= 0; i--){
            Double distance = (l.get(i).getCoordx()-coordx)*(l.get(i).getCoordx()-coordx) + (l.get(i).getCoordy()-coordy)*(l.get(i).getCoordy()-coordy);
            int distanceInt= Integer.parseInt(new java.text.DecimalFormat("0").format(distance));
            m.put(distanceInt,l.get(i));
        }
        //排序
        Object[] key =  m.keySet().toArray();
        Arrays.sort(key);
        List<News> l2 = new ArrayList<News>();
        for(int i=0;i<key.length;i++){
            l2.add(i,m.get(key[i]));
        }
        // 获得记录数
        int totalCount = l2.size();
        int currentPage = Integer.parseInt(request.getString("currentPage"));
        // 设置分页信息
        Page page = PageUtils.createPage(5, totalCount, currentPage);
        int everyPage = page.getEveryPage();
        //取得该频道下的记录
        if(l2.size()<page.getEveryPage()){
            everyPage = l2.size();
        }
        List<News> l3 = new ArrayList<News>();
        for(int i=0;i<everyPage;i++){
            l3.add(i,l2.get((currentPage-1)*5+i));
        }
        if(l2.size()==0){
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
        int newsId = Integer.parseInt(request.getString("newsid"));
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
        Date accepttime = DateUtils.StringToDate3(request.getString("accepttime"));
        if(acceptUserId==0 ||newsid==0 ){
               return createFailResponse(1002, "获取参数失败");
        }
        News news = new News();
        news.setNewsId(newsid);
        news.setAcceptUserid(acceptUserId);
        news.setAccepttime(accepttime);
        news.setTag(1);
        newsService.update(news);
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

    /**
     * 取消任务（被接受前）
     * @param httpRequest
     * @return
     * @throw Exception
     */
    @RequestMapping(value = "/cancel")
    @ResponseBody
    public String cancel(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        int newsId = request.getInt("newid");
        News news = new News();
        news.setNewsId(newsId);
        news.setTag(4);
        newsService.update(news);
        return createSuccessResponse("1000",null);
    }

    /**
     * 任务进入延时
     * @param httpRequest
     * @return
     * @throw Exception
     */
    @RequestMapping(value = "/delay")
    @ResponseBody
    public String delay(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        int newsId = request.getInt("newsid");
        News news = new News();
        news.setNewsId(newsId);
        news.setTag(5);
        try{
            newsService.update(news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return createSuccessResponse("1000",null);
    }

    /**
     * 确认达成任务
     * @param httpRequest
     * @return
     * @throw Exception
     */
    @RequestMapping(value = "/finish")
    @ResponseBody
    public String finish(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        int newsId = request.getInt("newsid");
        News news = new News();
        news.setNewsId(newsId);
        news.setTag(2);
        try{
            newsService.update(news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return createSuccessResponse("1000",null);
    }

    /**
     * 取消申诉
     * @param httpRequest
     * @return
     * @throw Exception
     */
    @RequestMapping(value = "/cancelAppeal")
    @ResponseBody
    public String cancelAppeal(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        int newsId = request.getInt("newsid");
        News news = new News();
        news.setNewsId(newsId);
        news.setTag(4);
        if (newsService.selectNewsById(newsId).getTag()!=3){
            return createFailResponse(1002,"改任务不在申诉中！");
        }
        try{
            newsService.update(news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return createSuccessResponse("1000",null);
    }

    /**
     * 获取某个人的所有在线消息
     * @param httpRequest
     * @return
     * @throw Exception
     */
    @RequestMapping(value = "/myNewsOnline")
    @ResponseBody
    public String myNewsOnline(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        int userid = request.getInt("userId");
        Date timeNow = DateUtils.StringToDate3(request.getString("timeNow"));
        News news = new News();
        news.setUserid(userid);
        news.setAccepttime(timeNow);
        List<News> l = new ArrayList<News>();
        try {
           l = newsService.myNewsOnline(news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return createSuccessResponse(l);
    }

    /**
     * 获取某个人所有等待中的发布
     * @param httpRequest
     * @return
     * @throw Exception
     */
    @RequestMapping(value = "/selectPublishWait")
    @ResponseBody
    public String selectPublishWait(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        int userid = request.getInt("userId");
        News news = new News();
        news.setUserid(userid);
        news.setTag(0);
        List<News> l = new ArrayList<News>();
        try {
            l = newsService.selectNewsTag(news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return createSuccessResponse(l);
    }

    /**
     * 获取某个人所有等待中的发布
     * @param httpRequest
     * @return
     * @throw Exception
     */
    @RequestMapping(value = "/selectPublishTag")
    @ResponseBody
    public String selectPublishTag(HttpServletRequest httpRequest) throws Exception{
        Request request = getRequest(httpRequest);
        int userid = request.getInt("userId");
        int tag = request.getInt("tag");
        if(tag!=0 || tag!=1 || tag!=2 || tag!=3 || tag!=4 || tag!=5){
             return createFailResponse(1002,"传入参数tag不符合要求！");
        }
        News news = new News();
        news.setUserid(userid);
        news.setTag(tag);
        List<News> l = new ArrayList<News>();
        try {
            l = newsService.selectNewsTag(news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return createSuccessResponse(l);
    }

}
