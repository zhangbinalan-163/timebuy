package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.util.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 消息API测试
 * Created by zhangbinalan on 15/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent",locations = {"classpath*:springContext-core.xml","classpath*:springContext-dao.xml"}),
        @ContextConfiguration(name = "child", locations = "classpath*:springContext-mvc.xml")
})
public class NewsControllerTest {
    private static Logger logger= LoggerFactory.getLogger(NewsControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addNews() throws Exception {

        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/info")
                        .param("phone", "18767122228")
                        .param("news", "123")
                        .param("starttime", "2011-1-1 10:11")
                        .param("finishtime", "2022-1-1 10:11")
                        .param("label", "1")
                        .param("money", "18")
                        .param("coordname", "18767122228")
                        .param("coordx", "1876")
                        .param("coordy", "123")
                        .param("pic","123")
                        .param("userid","12")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void getNewsById() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/user")
                        .param("userId", "15")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void getNewsAll() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/all")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void getNewsOne() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/one")
                        .param("newsId", "10")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }


    @Test
    public void accept() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/accept")
                        .param("acceptUserId", "10")
                        .param("newsId", "27")
                        .param("accepttime","2011-11-11 10:10:10")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void scheduleNews() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/schedule")
                        .param("userId","13")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void help() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/help")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void run() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/run")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void group() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/group")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void welfare() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/welfare")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void delay() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/finish")
                        .param("newsid", "27")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void newsOnline() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/online")
                        .param("timeNow", "2015-10-09 19:42:28")
                        .param("coordx", "1")
                        .param("coordy", "1")
                        .param("currentPage","1")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void myNewsOnline() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/news/myNewsOnline")
                        .param("timeNow", "2015-10-09 19:42:28")
                        .param("userId","1")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void Share() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/share/info")
                        .param("shareTime", "2015-10-09 19:42:28")
                        .param("newsId", "27")
                        .param("userId", "21")
                        .param("content", "success")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }

    @Test
    public void Appeal() throws Exception {
        MockHttpServletRequestBuilder request =
                MockMvcRequestBuilders.get("/appeal/info")
                        .param("appealTime", "2015-10-09 19:42:28")
                        .param("newsId", "27")
                        .param("content", "success")
                        .header("x-timebuy-sid", "d6089681f79c7627bbac829307e041a7");
        MvcResult result = mockMvc.perform(request)
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        logger.info(resultContent);
    }
}