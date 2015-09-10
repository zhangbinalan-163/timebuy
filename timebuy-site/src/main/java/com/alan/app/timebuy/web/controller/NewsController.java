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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
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
/*              System.out.println("文件长度: " + pic.getSize());
                System.out.println("文件类型: " + pic.getContentType());
                System.out.println("文件名称: " + pic.getName());
                System.out.println("文件原名: " + pic.getOriginalFilename());
                System.out.println("========================================");*/
                String pictu = pic.getOriginalFilename();
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
        String label = request.getString("label");
        float money = Float.parseFloat(request.getString("money"));
        String coordname = request.getString("coordname");
        float coordx = Float.parseFloat(request.getString("coordx"));
        float coordy = Float.parseFloat(request.getString("coordy"));

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

        newsService.addNews(news1);
        return createSuccessResponse(null);
  }
}
