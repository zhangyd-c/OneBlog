package com.zyd.blog.spider.processor;

import com.zyd.blog.spider.model.BaseModel;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.JsonPathSelector;

/**
 * 解析处理Ajax渲染的页面(待完善)
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/7/31 17:37
 */
public class JsonProcessor implements Processor {
    @Override
    public void process(Page page, BaseModel model) {
        String rawText = page.getRawText();
        String title = new JsonPathSelector(model.getTitleRegex()).select(rawText);
        if (!StringUtils.isEmpty(title) && !"null".equals(title)) {
            page.putField("title", title);
            page.putField("releaseDate", new JsonPathSelector(model.getReleaseDateRegex()).select(rawText));
            page.putField("author", new JsonPathSelector(model.getAuthorRegex()).select(rawText));
            page.putField("content", new JsonPathSelector(model.getContentRegex()).select(rawText));
            page.putField("source", page.getRequest().getUrl());
        }
        page.addTargetRequests(page.getHtml().links().regex(model.getTargetLinksRegex()).all());
    }
}
