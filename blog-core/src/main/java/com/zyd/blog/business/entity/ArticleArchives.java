package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizArticleArchives;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class ArticleArchives {

    private BizArticleArchives bizArticleArchives;
    private List<Article> articleList;

    public ArticleArchives(BizArticleArchives bizArticleArchives) {
        this.bizArticleArchives = bizArticleArchives;
    }

    public ArticleArchives() {
    }

    @JsonIgnore
    public BizArticleArchives getBizArticleArchives() {
        return bizArticleArchives;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Long getId() {
        return this.bizArticleArchives.getId();
    }

    public void setId(Long id) {
        this.bizArticleArchives.setId(id);
    }

    public String getTitle() {
        return this.bizArticleArchives.getTitle();
    }

    public void setTitle(String title) {
        this.bizArticleArchives.setTitle(title);
    }

    public String getOriginal() {
        return this.bizArticleArchives.getOriginal();
    }

    public void setOriginal(String original) {
        this.bizArticleArchives.setOriginal(original);
    }

    public String getDatetime() {
        return this.bizArticleArchives.getDatetime();
    }

    public void setDatetime(String datetime) {
        this.bizArticleArchives.setDatetime(datetime);
    }

    public String getDay() {
        String time = getDatetime();
        return time.substring(time.lastIndexOf("-") + 1);
    }
}
