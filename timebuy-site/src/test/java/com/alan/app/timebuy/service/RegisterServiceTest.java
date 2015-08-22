package com.alan.app.timebuy.service;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.exception.UserExsistException;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.entity.User;
import org.junit.Assert;
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
 *
 * Created by zhangbinalan on 15/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springContext-core.xml","classpath*:springContext-dao.xml"})
public class RegisterServiceTest extends AbstractJUnit4SpringContextTests {
    private static Logger logger= LoggerFactory.getLogger(RegisterServiceTest.class);

    @Resource(name = "registerServiceImpl")
    private RegisterService registerService;

    @Test
    public void registerUserTest() {
        try {
            User user=new User();
            user.setPhone("18069812067");
            user.setPassword(StringUtils.md5("123456"));
            registerService.registerUser(user);
        } catch (TimeBuyException e) {
            if(e instanceof UserExsistException){
                Assert.assertTrue(true);
            }else{
                logger.error("fail",e);
                Assert.fail();
            }
        }
    }
}
