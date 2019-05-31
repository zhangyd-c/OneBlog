package com.zyd.blog.framework.property;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.zhyd.oauth.config.AuthConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/5/23 18:16
 * @since 1.8
 */
@Component
@ConfigurationProperties(prefix = "me.zhyd.oauth")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class JustAuthProperties {

    private AuthConfig gitee;
    private AuthConfig github;
    private AuthConfig weibo;
    private AuthConfig dingtalk;
    private AuthConfig baidu;
    private AuthConfig coding;
    private AuthConfig tencentCloud;
    private AuthConfig oschina;
    private AuthConfig alipay;
    private AuthConfig qq;
    private AuthConfig wechat;
    private AuthConfig taobao;
    private AuthConfig google;
    private AuthConfig facebook;
    private AuthConfig csdn;
}
