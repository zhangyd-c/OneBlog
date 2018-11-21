package com.zyd.blog.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date操作工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
public class DateUtil {

    /**
     * 字符串日期转换为Date日期
     *
     * @param dateStr
     *         字符串日期
     * @param pattern
     *         参考DateConst.java
     * @return Date类型的日期
     */
    public static Date str2Date(String dateStr, String pattern) {
        Date resultDate = null;
        if (dateStr != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                resultDate = sdf.parse(dateStr);
            } catch (ParseException e) {
                log.error("日期转换异常", e);
            }
        }
        return resultDate;
    }

    /**
     * Date日期转换为字符串日期
     *
     * @param date
     *         Date日期
     * @param pattern
     *         参考DateConst.java
     * @return 字符串类型的日期
     */
    public static String date2Str(Date date, String pattern) {
        String dateStr = null;
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            dateStr = sdf.format(date);
        }
        return dateStr;
    }

    /**
     * 获取两个日期间隔的天数
     *
     * @param before
     *         较前的时间
     * @param after
     *         较后的时间
     * @return 返回两者相差的天数
     */
    public static int getGapDay(Date before, Date after) {
        return (null == before || null == after) ? 0 : Long.valueOf(Math.abs(after.getTime() - before.getTime()) / (24 * 60 * 60 * 1000)).intValue();
    }
}
