package com.zom.factory.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：日期格式常量与日期格式转换 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.tools.DateFormat <p>
 */
public class DateFormat {

    /**
     * 获得年时间格式
     */
    public static String YEARS_FORMAT    = "yyyy";
    /**
     * 获得年月时间格式
     */
    public static String MONTH_FORMAT    = "yyyy-MM";
    /**
     * 获得年月日时间格式
     */
    public static String DAY_FORMAT      = "yyyy-MM-dd";
    /**
     * 获得年月日时间格式2
     */
    public static String DAY_FORMAT2     = "yyyy年MM月dd日";
    /**
     * 获得年月日小时时间格式
     */
    public static String HOURS_FORMAT    = "yyyy-MM-dd HH";
    /**
     * 获得年月日小时分钟时间格式
     */
    public static String MINUTES_FORMAT  = "yyyy-MM-dd HH:mm";
    /**
     * 获得年月日小时分钟秒时间格式
     */
    public static String SECONDS_FORMAT  = "yyyy-MM-dd HH:mm:ss";
    /**
     * 获得年月日小时分钟秒时间格式2
     */
    public static String SECONDS_FORMAT2 = "yyyy年MM月dd日HH时mm分ss秒";
    /**
     * 获得年月日小时分钟秒毫秒时间格式
     */
    public static String MS_FORMAT       = "yyyy-MM-dd HH:mm:ss:SS";

    public static String STAMP = "yyyyMMddHHmmss";

    /**
     * 时间格式 DAY_FORMAT
     */
    public static final SimpleDateFormat monthSdf    = new SimpleDateFormat(MONTH_FORMAT);
    /**
     * 时间格式 DAY_FORMAT
     */
    public static final SimpleDateFormat daySdf      = new SimpleDateFormat(DAY_FORMAT);
    /**
     * 时间格式 DAY_FORMAT2
     */
    public static final SimpleDateFormat daySdf2     = new SimpleDateFormat(DAY_FORMAT2);
    /**
     * 时间格式  MINUTES_FORMAT
     */
    public static final SimpleDateFormat minutesSdf  = new SimpleDateFormat(MINUTES_FORMAT);
    /**
     * 时间格式  SECONDS_FORMAT
     */
    public static final SimpleDateFormat secondsSdf  = new SimpleDateFormat(SECONDS_FORMAT);
    /**
     * 时间格式  SECONDS_FORMAT2
     */
    public static final SimpleDateFormat secondsSdf2 = new SimpleDateFormat(SECONDS_FORMAT2);

    public static final SimpleDateFormat stampSdf = new SimpleDateFormat(STAMP);

    /**
     * 将日期字符串按指定格式解析为Date对象
     *
     * @param date
     * @param format
     * @return Date
     */
    public static Date parse(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return parse(dateStr, sdf);
    }

    /**
     * 将日期字符串按指定格式解析为Date对象
     *
     * @param date
     * @param sdf  SimpleDateFormat
     * @return Date
     */
    public static Date parse(String dateStr, SimpleDateFormat sdf) {
        Date date = null;
        try {
            date = sdf.parse(sdf.format(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("日期格式化错误：dateStr-" + dateStr + " format-" + sdf);
        }
        return date;
    }

    /**
     * 将Date对象格式化为指定格式的字符串
     *
     * @param date
     * @param format
     * @return String
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return format(date, sdf);
    }

    /**
     * 将Date对象格式化为指定格式的字符串
     *
     * @param date
     * @param sdf  SimpleDateFormat
     * @return String
     */
    public static String format(Date date, SimpleDateFormat sdf) {
        return sdf.format(date);
    }

}
