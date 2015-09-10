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
                        .param("label", "我我")
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
}