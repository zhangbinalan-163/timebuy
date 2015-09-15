package com.alan.app.timebuy.common.util;

import com.alan.app.timebuy.common.exception.TimeBuyException;
import com.alibaba.fastjson.JSON;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ????????????
 * Created by wyk on 15/8/29.
 */
public class DateUtils {
    /**
     * ??Date????String
     * @param date
     * @return
     */
    public static String DateToString(Date date)
    {
        String dateString=new SimpleDateFormat("yyyy-MM-dd").format(date);
        return dateString;
    }

    /**
     * ??String????Date
     * @param dateStr
     * @return
     */
    public static Date StringToDate(String dateStr){
        DateFormat dd=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        java.sql.Date sqlDate=null ;
        try {
            date = (Date)dd.parse(dateStr);
            sqlDate = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    /**
     * String?ת����Date��ȷ����
     * @param dateStr
     * @return
     */
    public static Date StringToDate2(String dateStr){
        DateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=null;
        java.sql.Date sqlDate=null ;
        try {
            date = (Date)dd.parse(dateStr);
            sqlDate = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return sqlDate;
    }

    public static Date StringToDate3(String dateStr){
        DateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        java.sql.Date sqlDate=null ;
        try {
            date = (Date)dd.parse(dateStr);
            sqlDate = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return sqlDate;
    }

}
