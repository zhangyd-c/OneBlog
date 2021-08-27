package com.zyd.blog.framework.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.framework.property.AppProperties;
import com.zyd.blog.framework.tag.ArticleTags;
import com.zyd.blog.framework.tag.CustomTags;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * freemarker配置类
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected CustomTags customTags;
    @Autowired
    protected ArticleTags articleTags;
    @Autowired
    private SysConfigService configService;
    @Autowired
    private AppProperties appProperties;

    /**
     * 添加自定义标签
     */
    @PostConstruct
    public void setSharedVariable() {
        configuration.setSharedVariable("zhydTag", customTags);
        configuration.setSharedVariable("articleTag", articleTags);
        try {
            configuration.setSharedVariable("config", configService.getConfigs());
            configuration.setSharedVariable("appInfo", appProperties);
            //shiro标签
            configuration.setSharedVariable("shiro", new ShiroTags());
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
    }
}
