package com.alan.app.timebuy.service;

import com.alan.app.timebuy.entity.Comment;
import com.alan.app.timebuy.entity.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wyk on 15/9/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springContext-core.xml","classpath*:springContext-dao.xml"})
public class CommentServiceTest extends AbstractJUnit4SpringContextTests {
    private static Logger logger= LoggerFactory.getLogger(CommentServiceTest.class);

    @Resource(name = "commentServiceImpl")
    private CommentService commentService;

    @Test
    public void getNewsById() {
        try {
            Comment c= new Comment();
            c.setNewsId(1);
            List<Comment> a = commentService.getCommentByNews(c);
            System.out.print("======================================="+"\n");
            System.out.print(a.size()+"\n");
            System.out.print("======================================="+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
