package com.alan.app.timebuy.web.controller;

import com.alan.app.timebuy.common.exception.IllegalOperationException;
import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alan.app.timebuy.common.util.StringUtils;
import com.alan.app.timebuy.web.Constants;
import com.alan.app.timebuy.web.vo.Request;
import com.alan.app.timebuy.web.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器基类
 * Created by zhangbinalan on 15/8/15.
 */
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 生成JSON响应信息
     * @param msg
     * @param data
     * @return
     */
    protected String createSuccessResponse(String msg, Object data)
    {
        Response dataResponse = new Response();
        dataResponse.setCode(1000);
        dataResponse.setMsg(msg);
        dataResponse.setSuccess(true);
        dataResponse.setData(data);
        return StringUtils.toJsonString(dataResponse);
    }

    /**
     * 生成JSON响应信息
     * @param data
     * @return
     */
    protected String createSuccessResponse(Object data) {
        return createSuccessResponse("成功！！！", data);
    }

    /**
     * 生成异常响应信息
     * @param code
     * @param message
     * @return
     */
    protected String createFailResponse(int code, String message)
    {
        Response dataResponse = new Response();
        dataResponse.setCode(code);
        dataResponse.setMsg(message);
        dataResponse.setSuccess(false);
        dataResponse.setData(null);
        return StringUtils.toJsonString(dataResponse);
    }

    /**
     * 默认的异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    protected String handleException(Exception e)
    {
        logger.error("[timebuy]request fail", e);
        int code = TimeBuyException.code.intValue();
        String message = "系统异常";
        if ((e instanceof TimeBuyException)) {
            TimeBuyException membersException = (TimeBuyException)e;
            code = membersException.getCode().intValue();
            message = membersException.getMessage();
        } else {
            Throwable t = e.getCause();
            if ((t instanceof TimeBuyException)) {
                TimeBuyException membersException = (TimeBuyException)t;
                code = membersException.getCode().intValue();
                message = membersException.getMessage();
            }
        }
        return createFailResponse(code, message);
    }

    /**
     * 从实际HTTP请求中获得封装的请求对象
     * @param httpRequest
     * @return
     */
    protected Request getRequest(HttpServletRequest httpRequest) throws IllegalOperationException {
        Request request = (Request)httpRequest.getAttribute(Constants.REQUEST_KEY);
        if(request==null){
            throw new IllegalOperationException();
        }
        return request;
    }
}
