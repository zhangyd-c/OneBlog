package com.zyd.blog.framework.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    /**
     * 是否启用验证码
     */
    private boolean enableKaptcha = false;

    /**
     * 是否在项目启动时，打印配置文件中的 【数据库配置】，包括 mysql、redis，默认关闭，生产环境不建议开启
     */
    private boolean enabledPrintConfig;

    /**
     * 是否启用 redis 切面缓存。
     * <p>
     * 优先级高于 {@link com.zyd.blog.business.annotation.RedisCache#enable()} 配置
     */
    private boolean enableRedisCache;

    /**
     * 系统版本，不建议修改。有 OneBlog 作者定时更新
     */
    private String version;

}
