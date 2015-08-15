package com.alan.app.timebuy.common.util;

import com.alibaba.fastjson.JSON;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * Created by zhangbinalan on 15/8/15.
 */
public class StringUtils {
    /**
     * 将对象转换为json格式的字符创
     * @param object
     * @return
     */
    public static String toJsonString(Object object){
        return JSON.toJSONString(object);
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str==null||str.equals("");
    }

    /**
     * 检查手机号是否合法
     * @param mobile
     * @return
     */
    public static boolean isLegalMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        Pattern p = Pattern.compile("^(13|14|15|18|17)\\d{9}$");
        Matcher m = p.matcher(mobile);
        boolean b = m.matches();
        return b;
    }
}
