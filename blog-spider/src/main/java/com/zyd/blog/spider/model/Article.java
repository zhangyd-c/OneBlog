package com.zyd.blog.spider.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/7/31 17:20
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article {

    private String title;
    private String content;
    private String author;
    private Date releaseDate;
    private String source;
    private String description;
    private String keywords;
    private List<String> tags;

    public Article(String title, String content, String author, String releaseDate, String source, String description, String keywords, List<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.source = source;
        this.tags = tags;
        this.description = description;
        this.keywords = keywords;
    }

    public Article() {
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public Article setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Article setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Article setSource(String source) {
        this.source = source;
        return this;
    }

    public Article setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Article setDescription(String description) {
        this.description = description;
        return this;
    }

    public Article setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }
}
