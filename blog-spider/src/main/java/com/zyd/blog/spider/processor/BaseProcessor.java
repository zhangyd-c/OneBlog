package com.zyd.blog.spider.processor;

import com.zyd.blog.spider.model.BaseModel;
import com.zyd.blog.spider.model.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 统一对页面进行解析处理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/7/31 17:37
 */
@Slf4j
public class BaseProcessor implements PageProcessor {
    private static BaseModel model;

    BaseProcessor() {
    }

    BaseProcessor(BaseModel m) {
        model = m;
    }

    @Override
    public void process(Page page) {
        Processor processor = new HtmlProcessor();
        if (model.getAjaxRequest()) {
            processor = new JsonProcessor();
        }
        processor.process(page, model);

    }

    @Override
    public Site getSite() {
        Site site = Site.me()
                .setCharset(model.getCharset())
                .setDomain(model.getDomain())
                .setSleepTime(model.getSleepTime())
                .setRetryTimes(model.getRetryTimes());

        //添加抓包获取的cookie信息
        List<Cookie> cookies = model.getCookies();
        if (CollectionUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (StringUtils.isEmpty(cookie.getDomain())) {
                    site.addCookie(cookie.getName(), cookie.getValue());
                    continue;
                }
                site.addCookie(cookie.getDomain(), cookie.getName(), cookie.getValue());
            }
        }
        //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
        Map<String, String> headers = model.getHeaders();
        if (MapUtils.isNotEmpty(headers)) {
            Set<Map.Entry<String, String>> entrySet = headers.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                site.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return site;
    }
}
