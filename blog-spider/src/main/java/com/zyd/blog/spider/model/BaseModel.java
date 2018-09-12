package com.zyd.blog.spider.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/7/23 13:33
 */
@Data
public class BaseModel {
    @NotEmpty(message = "必须指定标题抓取规则(xpath)")
    private String titleRegex;
    @NotEmpty(message = "必须指定内容抓取规则(xpath)")
    private String contentRegex;
    @NotEmpty(message = "必须指定发布日期抓取规则(xpath)")
    private String releaseDateRegex;
    @NotEmpty(message = "必须指定作者抓取规则(xpath)")
    private String authorRegex;
    @NotEmpty(message = "必须指定待抓取的url抓取规则(xpath)")
    private String targetLinksRegex;
    private String tagRegex;
    private String keywordsRegex = "//meta [@name=keywords]/@content";
    private String descriptionRegex = "//meta [@name=description]/@content";
    @NotEmpty(message = "必须指定网站根域名")
    private String domain;
    private String charset = "utf8";

    /**
     * 每次爬取页面时的等待时间
     */
    @Max(value = 5000, message = "线程间隔时间最大只能指定为5000毫秒")
    @Min(value = 1000, message = "线程间隔时间最小只能指定为1000毫秒")
    private int sleepTime = 1000;

    /**
     * 抓取失败时重试的次数
     */
    @Max(value = 5, message = "抓取失败时最多只能重试5次")
    @Min(value = 1, message = "抓取失败时最少只能重试1次")
    private int retryTimes = 2;

    /**
     * 线程个数
     */
    @Max(value = 5, message = "最多只能开启5个线程（线程数量越多越耗性能）")
    @Min(value = 1, message = "至少要开启1个线程")
    private int threadCount = 1;

    /**
     * 抓取入口地址
     */
//    @NotEmpty(message = "必须指定待抓取的网址")
    private String[] entryUrls;

    /**
     * 退出方式{1:等待时间(waitTime必填),2:抓取到的url数量(urlCount必填)}
     */
    private int exitWay = 1;
    /**
     * 单位：秒
     */
    private int waitTime = 60;
    private int urlCount = 100;

    private List<Cookie> cookies = new ArrayList<>();
    private Map<String, String> headers = new HashMap<>();
    private String ua = "Mozilla/5.0 (ozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36";

    private String uid;
    private Integer totalPage;

    /* 保留字段，针对ajax渲染的页面 */
    private Boolean ajaxRequest = false;
    /* 是否转存图片 */
    private boolean convertImg = false;

    public String getUid() {
        return uid;
    }

    public BaseModel setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public BaseModel setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public BaseModel setTitleRegex(String titleRegex) {
        this.titleRegex = titleRegex;
        return this;
    }

    public BaseModel setContentRegex(String contentRegex) {
        this.contentRegex = contentRegex;
        return this;
    }

    public BaseModel setReleaseDateRegex(String releaseDateRegex) {
        this.releaseDateRegex = releaseDateRegex;
        return this;
    }

    public BaseModel setAuthorRegex(String authorRegex) {
        this.authorRegex = authorRegex;
        return this;
    }

    public BaseModel setTargetLinksRegex(String targetLinksRegex) {
        this.targetLinksRegex = targetLinksRegex;
        return this;
    }

    public BaseModel setTagRegex(String tagRegex) {
        this.tagRegex = tagRegex;
        return this;
    }

    public BaseModel setKeywordsRegex(String keywordsRegex) {
        this.keywordsRegex = keywordsRegex;
        return this;
    }

    public BaseModel setDescriptionRegex(String descriptionRegex) {
        this.descriptionRegex = descriptionRegex;
        return this;
    }

    public BaseModel setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public BaseModel setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public BaseModel setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public BaseModel setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }

    public BaseModel setThreadCount(int threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    public BaseModel setEntryUrls(String[] entryUrls) {
        this.entryUrls = entryUrls;
        return this;
    }

    public BaseModel setEntryUrls(String entryUrls) {
        if (StringUtils.isNotEmpty(entryUrls)) {
            this.entryUrls = entryUrls.split("\r\n");
        }
        return this;
    }

    public BaseModel setExitWay(int exitWay) {
        this.exitWay = exitWay;
        return this;
    }

    public BaseModel setWaitTime(int waitTime) {
        this.waitTime = waitTime;
        return this;
    }

    public BaseModel setHeader(String key, String value) {
        Map<String, String> headers = this.getHeaders();
        headers.put(key, value);
        return this;
    }

    public BaseModel setHeader(String headersStr) {
        if (StringUtils.isNotEmpty(headersStr)) {
            String[] headerArr = headersStr.split("\r\n");
            for (String s : headerArr) {
                String[] header = s.split("=");
                setHeader(header[0], header[1]);
            }
        }
        return this;
    }

    public BaseModel setCookie(String domain, String key, String value) {
        List<Cookie> cookies = this.getCookies();
        cookies.add(new Cookie(domain, key, value));
        return this;
    }

    public BaseModel setCookie(String cookiesStr) {
        if (StringUtils.isNotEmpty(cookiesStr)) {
            List<Cookie> cookies = this.getCookies();
            String[] cookieArr = cookiesStr.split(";");
            for (String aCookieArr : cookieArr) {
                String[] cookieNode = aCookieArr.split("=");
                if (cookieNode.length <= 1) {
                    continue;
                }
                cookies.add(new Cookie(cookieNode[0].trim(), cookieNode[1].trim()));
            }
        }
        return this;
    }

    public BaseModel setAjaxRequest(boolean ajaxRequest) {
        this.ajaxRequest = ajaxRequest;
        return this;
    }
}
