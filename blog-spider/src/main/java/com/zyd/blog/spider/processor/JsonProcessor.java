package com.zyd.blog.spider.processor;

import com.zyd.blog.spider.model.BaseModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.List;

public class JsonProcessor implements Processor {
    @Override
    public void process(Page page, BaseModel model) {
        String rawText= page.getRawText();
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
