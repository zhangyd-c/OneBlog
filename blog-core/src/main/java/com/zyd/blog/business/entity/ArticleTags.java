package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizArticleTags;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class ArticleTags {
    private static final long serialVersionUID = 1L;
    private BizArticleTags bizArticleTags;

    public ArticleTags() {
        this.bizArticleTags = new BizArticleTags();
    }

    public ArticleTags(BizArticleTags bizArticleTags) {
        this.bizArticleTags = bizArticleTags;
    }

    @JsonIgnore
    public BizArticleTags getBizArticleTags() {
        return this.bizArticleTags;
    }

    public Long getId() {
        return this.bizArticleTags.getId();
    }

    public void setId(Long id) {
        this.bizArticleTags.setId(id);
    }

    public long getTagId() {
        return this.bizArticleTags.getTagId();
    }

    public void setTagId(long tagId) {
        this.bizArticleTags.setTagId(tagId);
    }

    public long getArticleId() {
        return this.bizArticleTags.getArticleId();
    }

    public void setArticleId(long articleId) {
        this.bizArticleTags.setArticleId(articleId);
    }

    public Date getCreateTime() {
        return this.bizArticleTags.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticleTags.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.bizArticleTags.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticleTags.setUpdateTime(updateTime);
    }

}

