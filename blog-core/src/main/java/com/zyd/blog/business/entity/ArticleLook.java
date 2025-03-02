package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizArticleLookV2;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class ArticleLook {
    private static final long serialVersionUID = 1L;
    private BizArticleLookV2 bizArticleLookV2;

    public ArticleLook() {
        this.bizArticleLookV2 = new BizArticleLookV2();
    }

    public ArticleLook(BizArticleLookV2 bizArticleLookV2) {
        this.bizArticleLookV2 = bizArticleLookV2;
    }

    @JsonIgnore
    public BizArticleLookV2 getBizArticleLook() {
        return this.bizArticleLookV2;
    }

    public Long getId() {
        return this.bizArticleLookV2.getId();
    }

    public void setId(Long id) {
        this.bizArticleLookV2.setId(id);
    }

    public long getArticleId() {
        return this.bizArticleLookV2.getArticleId();
    }

    public void setArticleId(Long articleId) {
        this.bizArticleLookV2.setArticleId(articleId);
    }

    public Integer getLookCount() {
        return this.bizArticleLookV2.getLookCount();
    }

    public void setLookCount(Integer ookCount) {
        this.bizArticleLookV2.setLookCount(ookCount);
    }

    public Date getCreateTime() {
        return this.bizArticleLookV2.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticleLookV2.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.bizArticleLookV2.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticleLookV2.setUpdateTime(updateTime);
    }

}

