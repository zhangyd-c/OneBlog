package com.zyd.blog.business.enums;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/12 9:37
 * @since 1.8
 */
public enum ConfigKeyEnum {
    /**
     * 站点简介
     */
    SITE_DESC("siteDesc"),
    /**
     * 主域名
     */
    DOMAIN("domain"),
    /**
     * 博客地址
     */
    SITE_URL("siteUrl"),
    /**
     * 站点图标
     */
    SITE_FAVICON("siteFavicon"),
    /**
     * 资源文件域名
     */
    STATIC_WEB_SITE("staticWebSite"),
    /**
     * CMS后管系统地址
     */
    CMS_URL("cmsUrl"),

    /**
     * 网站Title
     */
    SITE_NAME("siteName"),
    /**
     * 网站首页的Description
     */
    HOME_DESC("homeDesc"),
    /**
     * 网站首页的Keywords
     */
    HOME_KEYWORDS("homeKeywords"),
    /**
     * 百度推送Token
     */
    BAIDU_PUSH_TOKEN("baiduPushToken"),

    /**
     * 存储类型
     */
    STORAGE_TYPE("storageType"),
    /**
     * 文件服务器域名
     */
    LOCAL_FILE_URL("localFileUrl"),
    /**
     * 文件存储路径
     */
    LOCAL_FILE_PATH("localFilePath"),
    /**
     * 七牛云Bucket 名称
     */
    QINIU_BUCKET_NAME("qiniuBucketName"),
    /**
     * 七牛云AccessKey
     */
    QINIU_ACCESS_KEY("qiniuAccessKey"),
    /**
     * 七牛云Secret Key
     */
    QINIU_SECRET_KEY("qiniuSecretKey"),
    /**
     * 七牛云cdn域名
     */
    QINIU_BASE_PATH("qiniuBasePath"),
    /**
     * 阿里云Bucket 名称
     */
    ALIYUN_BUCKET_NAME("aliyunBucketName"),
    /**
     * 阿里云地域节点（EndPoint）
     */
    ALIYUN_ENDPOINT("aliyunEndpoint"),
    /**
     * 阿里云Bucket 域名
     */
    ALIYUN_FILE_URL("aliyunFileUrl"),
    /**
     * 阿里云Access Key
     */
    ALIYUN_ACCESS_KEY("aliyunAccessKey"),
    /**
     * 阿里云Access Key Secret
     */
    ALIYUN_ACCESS_KEY_SECRET("aliyunAccessKeySecret"),

    /**
     * 第三方登陆github Client ID
     */
    GITHUB_CLIENT_ID("githubClientId"),
    /**
     * 第三方登陆github Client Secret
     */
    GITHUB_CLIENT_SECRET("githubClientSecret"),
    /**
     * 第三方登陆gitee Client ID
     */
    GITEE_CLIENT_ID("giteeClientId"),
    /**
     * 第三方登陆gitee Client ID
     */
    GITEE_CLIENT_SECRET("giteeClientSecret"),
    /**
     * 第三方登陆 微博App Key
     */
    WEIBO_CLIENT_ID("weiboClientId"),
    /**
     * 第三方登陆 微博App Secret
     */
    WEIBO_CLIENT_SECRET("weiboClientSecret"),
    /**
     * 第三方登陆 QQ App Key
     */
    QQ_CLIENT_ID("qqClientId"),
    /**
     * 第三方登陆 QQ App Secret
     */
    QQ_CLIENT_SECRET("qqClientSecret"),

    /**
     * 允许匿名评论
     */
    ANONYMOUS("anonymous"),
    /**
     * 开启留言板评论
     */
    COMMENT("comment"),
    /**
     * 占位符，当没输入内容时显示该值
     */
    EDITOR_PLACEHOLDER("editorPlaceholder"),
    /**
     * 评论框右下角显示的内容
     */
    EDITOR_ALERT("editorAlert"),

    /**
     * 站长名称
     */
    AUTHOR_NAME("authorName"),
    /**
     * 站长邮箱
     */
    AUTHOR_EMAIL("authorEmail"),
    /**
     * 微信二维码
     */
    WX_CODE("wxCode"),
    /**
     * QQ
     */
    QQ("qq"),
    /**
     * weibo
     */
    WEIBO("weibo"),
    /**
     * github
     */
    GITHUB("github"),

    /**
     * 微信赞赏码
     */
    WX_PRAISE_CODE("wxPraiseCode"),
    /**
     * 支付宝赞赏码
     */
    ZFB_PRAISECODE("zfbPraiseCode"),

    /**
     * 百度Api的AK
     */
    BAIDU_API_AK("baiduApiAk"),
    /**
     * 是否显示维护通知
     */
    MAINTENANCE("maintenance"),
    /**
     * 维护通知的日期
     */
    MAINTENANCE_DATE("maintenanceDate"),
    /**
     * 维护通知大约需要的时间
     */
    MAINTENANCE_TIME("maintenanceTime"),

    /**
     * 系统最后一次更新的日期
     */
    UPDATE_TIME("updateTime"),
    ;

    private String key;

    ConfigKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }}
