package com.zyd.blog.spider.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/7/31 17:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VirtualArticle {

    private String title;
    private String content;
    private String author;
    private Date releaseDate;
    private String source;
    private String description;
    private String keywords;
    private List<String> tags;

    public VirtualArticle(String title, String content, String author, String releaseDate, String source, String description, String keywords, List<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.source = source;
        if (CollectionUtils.isEmpty(this.tags = tags)) {
            this.tags = Collections.singletonList("其他");
        }
        if (StringUtils.isEmpty(this.description = description) || "null".equalsIgnoreCase(this.description)) {
            this.description = title;
        }
        if (StringUtils.isEmpty(this.keywords = keywords) || "null".equalsIgnoreCase(this.keywords)) {
            this.keywords = title;
        }
    }

    public VirtualArticle() {
    }

    public VirtualArticle setTitle(String title) {
        this.title = title;
        return this;
    }

    public VirtualArticle setContent(String content) {
        this.content = content;
        return this;
    }

    public VirtualArticle setAuthor(String author) {
        this.author = author;
        return this;
    }

    public VirtualArticle setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public VirtualArticle setSource(String source) {
        this.source = source;
        return this;
    }

    public VirtualArticle setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public VirtualArticle setDescription(String description) {
        this.description = description;
        return this;
    }

    public VirtualArticle setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }
}
