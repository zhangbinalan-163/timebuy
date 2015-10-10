package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.util.DateUtils;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.News;
import com.alan.app.timebuy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by wyk on 15/9/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springContext-core.xml","classpath*:springContext-dao.xml"})
public class NewsServiceTest extends AbstractJUnit4SpringContextTests {
    private static Logger logger= LoggerFactory.getLogger(NewsServiceTest.class);

    @Resource(name = "newsServiceImpl")
    private NewsService newsService;

    @Test
    public void getNewsById() {
        try {
            List<News> a = newsService.getNewsById(12);
            System.out.print("======================================="+"\n");
            System.out.print(a.size()+"\n");
            System.out.print("======================================="+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNewsAll(){
        try {
            List<News> a = newsService.getNewsAll();
            System.out.print("======================================="+"\n");
            System.out.print(a.size()+"\n");
            System.out.print("======================================="+"\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void selectNewsById(){
        try {
            News a = newsService.selectNewsById(13);
            System.out.print("======================================="+"\n");
            System.out.print(a.getCoordname()+"\n");
            System.out.print("======================================="+"\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void welfare(){
        try {
           List<News> l = newsService.welfare();
            System.out.print("======================================="+"\n");
            System.out.print(l.size()+"\n");
            System.out.print("======================================="+"\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void newsOnline(){
        try {
            News news = new News();
            news.setAccepttime(DateUtils.StringToDate3("2010-01-010 12:12:00"));
            List<News> l = newsService.newsOnline(news);
            System.out.print("======================================="+"\n");
            System.out.print(l.size() + "\n");
            System.out.print("=======================================" + "\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
