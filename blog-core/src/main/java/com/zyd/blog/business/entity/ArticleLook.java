package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizArticleLook;

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
    private BizArticleLook bizArticleLook;

    public ArticleLook() {
        this.bizArticleLook = new BizArticleLook();
    }

    public ArticleLook(BizArticleLook bizArticleLook) {
        this.bizArticleLook = bizArticleLook;
    }

    @JsonIgnore
    public BizArticleLook getBizArticleLook() {
        return this.bizArticleLook;
    }

    public Long getId() {
        return this.bizArticleLook.getId();
    }

    public void setId(Long id) {
        this.bizArticleLook.setId(id);
    }

    public long getArticleId() {
        return this.bizArticleLook.getArticleId();
    }

    public void setArticleId(Long articleId) {
        this.bizArticleLook.setArticleId(articleId);
    }

    public Long getUserId() {
        return this.bizArticleLook.getUserId();
    }

    public void setUserId(long userId) {
        this.bizArticleLook.setUserId(userId);
    }

    public String getUserIp() {
        return this.bizArticleLook.getUserIp();
    }

    public void setUserIp(String userIp) {
        this.bizArticleLook.setUserIp(userIp);
    }

    public Date getLookTime() {
        return this.bizArticleLook.getLookTime();
    }

    public void setLookTime(Date lookTime) {
        this.bizArticleLook.setLookTime(lookTime);
    }

    public Date getCreateTime() {
        return this.bizArticleLook.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticleLook.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.bizArticleLook.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticleLook.setUpdateTime(updateTime);
    }

}

