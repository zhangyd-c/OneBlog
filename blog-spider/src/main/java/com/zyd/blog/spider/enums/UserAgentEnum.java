package com.zyd.blog.spider.enums;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/9/26 14:21
 * @since 1.8
 */
public enum UserAgentEnum {

    PC("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36"),
    MOBILE("Mozilla/5.0 (Linux; U; Android 4.0; en-us; GT-I9300 Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

    private String ua;

    UserAgentEnum(String ua) {
        this.ua = ua;
    }

    public String getUa() {
        return ua;
    }
}
