package com.zyd.blog.spider.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/21 13:28
 * @since 1.8
 */
public enum PlatformUtil {
    INSTANCE;

    public String getHostAndNoneProtocol(String url) {
        String res = getHost(url);
        if (null == res) {
            return null;
        }
        return res.replace("https://", "").replace("http://", "");
    }

    public String getHost(String url) {
        return getPlatformDomain(url);
    }

    public boolean isImooc(String url) {
        String res = getPlatformDomain(url);
        if (null == res) {
            return false;
        }
        return res.contains("imooc.com");
    }

    public boolean isCsdn(String url) {
        String res = getPlatformDomain(url);
        if (null == res) {
            return false;
        }
        return res.contains("csdn.net");
    }

    public boolean isIteye(String url) {
        String res = getPlatformDomain(url);
        if (null == res) {
            return false;
        }
        return res.contains("iteye.com");
    }

    public boolean isCnblogs(String url) {
        String res = getPlatformDomain(url);
        if (null == res) {
            return false;
        }
        return res.contains("cnblogs.com");
    }

    private String getPlatformDomain(String url) {
        String regex = "(http|https)://(www.)?(\\w+(\\.)?)+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.find() ? matcher.group() : null;
    }
}
