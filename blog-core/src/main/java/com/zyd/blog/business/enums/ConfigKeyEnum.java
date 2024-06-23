package com.zyd.blog.business.enums;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
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
     * 百度站长平台的Cookie
     */
    BAIDU_PUSH_COOKIE("baiduPushCookie"),

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

    /**
     * 文章编辑器
     */
    ARTICLE_EDITOR("articleEditor"),
    /**
     * TinyMCE API Key
     */
    TINY_MCE_KEY("tinyMCEKey"),

    /**
     * 网站安装时间，默认为执行init_data.sql的时间
     */
    INSTALLDATE("installdate"),

    /**
     * 当切换浏览器tab时，在原tab上的标题。比如https://docs.zhyd.me上的“麻溜儿回来~~~”
     */
    DYNAMIC_TITLE("dynamicTitle"),

    /**
     * “关于本站”的页面内容
     */
    ABOUT_ME_HTML("aboutMeHtml"),

    /**
     * “留言板”的页面内容
     */
    GUESTBOOK_HTML("guestbookHtml"),

    /**
     * “友情链接”的页面内容
     */
    LINKS_HTML("linksHtml"),

    /**
     * footer 内容
     */
    FOOTER_HTML("footerHtml"),

    /**
     * web 端鼠标点击后弹出的文字，半角逗号分隔
     */
    BUBBLE_WORD("bubbleWord"),

    /**
     * web 端，热门搜索 中的 选项
     */
    SEARCH_OPTIONS("searchOptions"),

    /**
     * 自定义“免责声明”内容
     */
    DISCLAIMER_HTML("disclaimerHtml"),

    /**
     * 自定义“js”脚本内容
     */
    CUSTOM_JS("customJs"),

    /**
     * 自定义“CSS”内容
     */
    CUSTOM_CSS("customCss"),

    /**
     * blog-hunter 配置文件，如果没有添加该配置，则默认取 src/main/resources/HunterConfig.json
     */
    BLOG_HUNTER_CONFIG("blogHunterConfig"),

    /**
     * 启用 Hitokoto（一言）。一言，随机显示一句话的插件，该插件部分时候加载较慢，如果不需要请自行关闭
     */
    ENABLE_HITOKOTO("enableHitokoto"),
    /**
     * 微信公众号appId
     */
    WX_GZH_APP_ID("wxGzhAppId"),
    /**
     * 微信公众号AppSecret
     */
    WX_GZH_APP_SECRET("wxGzhAppSecret"),
    ;

    private final String key;

    ConfigKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }}
