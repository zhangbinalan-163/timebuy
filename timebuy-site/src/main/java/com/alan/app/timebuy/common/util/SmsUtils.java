package com.alan.app.timebuy.common.util;

/**
 * 验证码工具类
 * Created by zhangbinalan on 15/8/15.
 */
public class SmsUtils {
    /**
     * 生成验证码
     * @return
     */
    public static String genCode()
    {
        int code = (int)(Math.random() * 899999 + 100000);
        return code + "";
    }
}
