package com.zyd.blog.spider.processor;

import com.zyd.blog.spider.model.BaseModel;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.Arrays;

public class HtmlProcessor implements Processor {

    @Override
    public void process(Page page, BaseModel model) {
        Html pageHtml = page.getHtml();
        String title = pageHtml.xpath(model.getTitleRegex()).get();
        String source = page.getRequest().getUrl();
        if (!StringUtils.isEmpty(title) && !"null".equals(title) && !Arrays.asList(model.getEntryUrls()).contains(source)) {
            page.putField("title", title);
            page.putField("source", source);
            page.putField("releaseDate", pageHtml.xpath(model.getReleaseDateRegex()).get());
            page.putField("author", pageHtml.xpath(model.getAuthorRegex()).get());
            page.putField("content", pageHtml.xpath(model.getContentRegex()).get());
            page.putField("tags", pageHtml.xpath(model.getTagRegex()).all());
            page.putField("description", pageHtml.xpath(model.getDescriptionRegex()).get());
            page.putField("keywords", pageHtml.xpath(model.getKeywordsRegex()).get());
        }
        page.addTargetRequests(page.getHtml().links().regex(model.getTargetLinksRegex()).all());
    }
}
