package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.fail;

/**
 * Created by zhangbinalan on 15/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springContext-core.xml","classpath*:springContext-dao.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
    private static Logger logger= LoggerFactory.getLogger(UserServiceTest.class);

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Test
    public void getByPhoneTest() {
        try {
            User userInfo = userService.getUserByPhone("18069812067");
            logger.info(StringUtils.toJsonString(userInfo));
        } catch (Exception e) {
            logger.error("fail",e);
            fail();
        }
    }
}
