package com.zyd.blog.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * UrlEncoder & UrlDecoder
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
public class UrlCodeUtil {

    private static final String URL_ENCODE = "UTF-8";

    private UrlCodeUtil() {
    }

    public static String encode(String param) {
        return encode(param, URL_ENCODE);
    }

    public static String encode(String param, String encode) {
        String result = null;
        if (!StringUtils.isEmpty(param)) {
            try {
                result = URLEncoder.encode(param, encode);
            } catch (UnsupportedEncodingException e) {
                log.error("url编码发生异常", e);
            }
        }
        return result;
    }

    public static String decode(String param) {
        return decode(param, URL_ENCODE);
    }

    public static String decode(String param, String encode) {
        String result = null;
        if (!StringUtils.isEmpty(param)) {
            try {
                result = URLDecoder.decode(param, encode);
            } catch (UnsupportedEncodingException e) {
                log.error("url编码发生异常", e);
            }
        }
        return result;
    }
}
