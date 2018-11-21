package com.zyd.blog.spider.util;

import com.zyd.blog.spider.enums.ExitWayEnum;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/9/27 17:40
 * @since 1.8
 */
public class CommonUtil {

    public static Map<String, Function> exitWay = new HashMap<>();

    static {
        exitWay.put(ExitWayEnum.DEFAULT.toString(), (obj) -> "当前程序将会在处理完所有匹配到的连接后自动停止...");
        exitWay.put(ExitWayEnum.URL_COUNT.toString(), (count) -> String.format("当前程序将会在抓取够%s个连接后自动停止...", count));
        exitWay.put(ExitWayEnum.DURATION.toString(), (count) -> String.format("当前程序将会在运行%s秒后自动停止...", count));
    }

    public static String subDescStr(String description, String content) {
        String desc = StringUtils.isNotEmpty(description) ? description.replaceAll("\r\n| ", "") : content.length() > 100 ? content.substring(0, 100) : content;
        return StringUtils.isEmpty(desc) ? null : Jsoup.clean(desc.trim(), Whitelist.simpleText());
    }

    public static String subKeywordsStr(String keywords) {
        String keys = StringUtils.isNotEmpty(keywords) && !"null".equals(keywords) ? keywords.trim().replaceAll(" +|，", ",").replaceAll(",,", ",") : null;
        return StringUtils.isEmpty(keys) ? null : Jsoup.clean(keys, Whitelist.simpleText());
    }
}
