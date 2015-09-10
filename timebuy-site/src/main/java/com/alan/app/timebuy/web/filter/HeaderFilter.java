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
 * 头过滤器。解析请求中的自定义的HEADER信息
 * Created by zhangbinalan on 15/8/15.
 */
public class HeaderFilter extends HandlerInterceptorAdapter
{
    private static Logger logger = LoggerFactory.getLogger(HeaderFilter.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String uri = request.getRequestURI();
        if(logger.isDebugEnabled()){
            logger.debug(uri);
        }
        Request requestVO = new Request();
        requestVO.setHttpRequest(request);
        String sid = WebUtils.getHeader(request, Constants.HEADER_NAME_SID);
        //SID不能为空
     /* if (StringUtils.isEmpty(sid)) {
            logger.warn("x-timebuy-sid is null");
            Response responseVO = new Response();
            responseVO.setSuccess(false);
            responseVO.setCode(1002);
            responseVO.setMsg("x-timebuy-sid is null");
            String jsonResult = StringUtils.toJsonString(responseVO);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(jsonResult);
            return false;
        }*/
        requestVO.setSid(sid);
        /**
         * 将解析出来的Request设置到http request中
         */
        request.setAttribute(Constants.REQUEST_KEY, requestVO);
        return true;
    }
}
