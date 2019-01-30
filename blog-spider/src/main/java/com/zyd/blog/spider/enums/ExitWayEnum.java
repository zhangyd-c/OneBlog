package com.zyd.blog.spider.enums;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/7/31 17:20
 */
public enum ExitWayEnum {
    /**
     * 默认方式,直到程序将所有url抓取完成才会退出
     */
    DEFAULT("默认", 0),
    /*
     * 持续时间
     */
    DURATION("持续时间(s)", 60),
    /**
     * 抓取的条数
     */
    URL_COUNT("链接条数", 10);

    private String desc;
    private int defaultCount;

    ExitWayEnum(String desc, int defaultCount) {
        this.desc = desc;
        this.defaultCount = defaultCount;
    }

    public String getDesc() {
        return desc;
    }

    public int getDefaultCount() {
        return defaultCount;
    }
}
