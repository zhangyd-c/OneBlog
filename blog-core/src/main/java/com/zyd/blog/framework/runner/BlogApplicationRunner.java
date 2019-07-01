package com.zyd.blog.framework.runner;

import com.zyd.blog.framework.property.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

/**
 * 程序启动后通过ApplicationRunner处理一些事务
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/6/6 16:07
 * @since 1.0
 */
@Slf4j
@Component
public class BlogApplicationRunner extends ContextLoaderListener implements ApplicationRunner {

    @Value("${server.port}")
    private int port;
    @Value("${spring.profiles.active}")
    private String profile;
    @Value("${app.enabledConfigLog}")
    private Boolean enabledConfigLog;

    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.info("博客部署完成，博客访问地址：http://localhost:" + port);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        if (null != enabledConfigLog && enabledConfigLog) {
            log.info("博客关键配置信息：");
            String[] activeProfiles = configurableApplicationContext.getEnvironment().getActiveProfiles();
            if (ObjectUtils.isEmpty(activeProfiles)) {
                String[] defaultProfiles = configurableApplicationContext.getEnvironment().getDefaultProfiles();
                log.info("No active profile set, falling back to default profiles: " + StringUtils.arrayToCommaDelimitedString(defaultProfiles));
            } else {
                log.info("The following profiles are active: " + StringUtils.arrayToCommaDelimitedString(activeProfiles));
            }

            log.info("Data Source：");
            log.info("  - url：{}", dataSourceProperties.getUrl());
            log.info("  - username：{}", dataSourceProperties.getUsername());
            log.info("  - password：{}", dataSourceProperties.getPassword());

            log.info("Redis：");
            log.info("  - database：{}", redisProperties.getDatabase());
            log.info("  - host：{}", redisProperties.getHost());
            log.info("  - port：{}", redisProperties.getPort());
            log.info("  - password：{}", redisProperties.getPassword());
        }
    }
}
