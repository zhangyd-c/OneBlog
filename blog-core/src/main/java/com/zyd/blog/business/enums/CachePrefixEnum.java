package com.zyd.blog.business.enums;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/7/15 22:00
 * @since 1.0
 */
public enum CachePrefixEnum {

    BIZ("biz_cache_"),
    VIEW("view_cache_"),
    DDOS("ddos_cache_"),
    WX("wx_api_cache_"),
    SPIDER("spider_cache_"),
    ;
    private String prefix;

    CachePrefixEnum(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
