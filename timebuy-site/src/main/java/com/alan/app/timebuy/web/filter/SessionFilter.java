package com.alan.app.timebuy.web.filter;

import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.common.util.WebUtils;
import com.alan.app.timebuy.web.Constants;
import com.alan.app.timebuy.web.vo.Request;
import com.alan.app.timebuy.web.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录权限过滤器
 *
 * Created by zhangbinalan on 15/8/15.
 */
public class SessionFilter extends HandlerInterceptorAdapter
{
    private static Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
}
