package com.zyd.blog.business.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/01/16 12:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseConfig {
    private String homeDesc;
    private String homeKeywords;
    private String domain;
    private String cmsUrl;
    private String siteUrl;
    private String siteName;
    private String siteDesc;
    private String siteFavicon;

    private String staticWebSite;
    private String authorName;
    private String authorEmail;

    private String wxCode;
    private String qq;
    private String weibo;
    private String github;
    private Boolean maintenance;
    private Date maintenanceDate;

    private String qiniuBasePath;
    private String qiniuAccessKey;
    private String qiniuSecretKey;
    private String qiniuBucketName;


    private String baiduPushToken;
    /**
     * 赞赏码
     */
    private String wxPraiseCode;
    private String zfbPraiseCode;
    /**
     * 百度api授权AK(获取地址：http://lbsyun.baidu.com/apiconsole/key)
     * 调用百度的api时必须
     */
    private String baiduApiAk;

    private Boolean comment;
    private Boolean anonymous;
    private String editorPlaceholder;
    private String editorAlert;

    private Date updateTime;

    public boolean getComment() {
        return null == comment ? false : comment;
    }

    public boolean getAnonymous() {
        return null == anonymous ? false : anonymous;
    }
}

