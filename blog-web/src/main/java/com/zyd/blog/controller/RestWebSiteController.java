package com.zyd.blog.controller;

import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Template;
import com.zyd.blog.business.enums.PlatformEnum;
import com.zyd.blog.business.enums.TemplateKeyEnum;
import com.zyd.blog.business.service.*;
import com.zyd.blog.util.FreeMarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 站点相关的接口类，主要为sitemap和robots的生成
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@RestController
public class RestWebSiteController {
    @Autowired
    private BizArticleService articleService;
    @Autowired
    private BizTypeService typeService;
    @Autowired
    private BizTagsService tagsService;
    @Autowired
    private SysTemplateService templateService;
    @Autowired
    private SysConfigService configService;

    @GetMapping(value = "/sitemap.xml", produces = {"application/xml"})
    @BussinessLog(value = "查看sitemap.xml", platform = PlatformEnum.WEB)
    public String sitemapXml() {
        return getSitemap(TemplateKeyEnum.TM_SITEMAP_XML);
    }

    @GetMapping(value = "/sitemap.txt", produces = {"text/plain"})
    @BussinessLog(value = "查看sitemap.txt", platform = PlatformEnum.WEB)
    public String sitemapTxt() {
        return getSitemap(TemplateKeyEnum.TM_SITEMAP_TXT);
    }

    @GetMapping(value = "/sitemap.html", produces = {"text/html"})
    @BussinessLog(value = "查看sitemap.html", platform = PlatformEnum.WEB)
    public String sitemapHtml() {
        return getSitemap(TemplateKeyEnum.TM_SITEMAP_HTML);
    }

    @GetMapping(value = "/robots.txt", produces = {"text/plain"})
    @BussinessLog(value = "查看robots", platform = PlatformEnum.WEB)
    public String robots() {
        Template template = templateService.getTemplate(TemplateKeyEnum.TM_ROBOTS);
        Map<String, Object> map = new HashMap<>();
        map.put("config", configService.getConfigs());
        return FreeMarkerUtil.template2String(template.getRefValue(), map, true);
    }

    private String getSitemap(TemplateKeyEnum key) {
        Template template = templateService.getTemplate(key);
        Map<String, Object> map = new HashMap<>();
        map.put("articleTypeList", typeService.listAll());
        map.put("articleTagsList", tagsService.listAll());
        map.put("articleList", articleService.listAll());
        map.put("config", configService.getConfigs());
        return FreeMarkerUtil.template2String(template.getRefValue(), map, true);
    }
}
