package com.alan.app.timebuy.dao;

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
 * 用户信息的操作DAO
 *
 * Created by zhangbinalan on 15/8/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springContext-core.xml","classpath*:springContext-dao.xml"})
public class UserDaoTest extends AbstractJUnit4SpringContextTests {
    private static Logger logger= LoggerFactory.getLogger(UserDaoTest.class);

    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    @Test
    public void getByPhoneTest() {
        try {
            User userInfo = userDao.getByPhone("18069812065");
            logger.info(StringUtils.toJsonString(userInfo));
        } catch (Exception e) {
            logger.error("fail",e);
            fail();
        }
    }
    @Test
    public void getByIdTest() {
        try {
            User userInfo = userDao.getById(15);
            logger.info(StringUtils.toJsonString(userInfo));
        } catch (Exception e) {
            logger.error("fail",e);
            fail();
        }
    }
    @Test
    public void insertTest() {

    }
}