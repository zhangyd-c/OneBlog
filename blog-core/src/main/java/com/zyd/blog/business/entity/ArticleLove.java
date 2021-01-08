package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizArticleLove;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class ArticleLove {
    private static final long serialVersionUID = 1L;
    private BizArticleLove bizArticleLove;

    public ArticleLove() {
        this.bizArticleLove = new BizArticleLove();
    }

    public ArticleLove(BizArticleLove bizArticleLove) {
        this.bizArticleLove = bizArticleLove;
    }

    @JsonIgnore
    public BizArticleLove getBizArticleLove() {
        return this.bizArticleLove;
    }

    public Long getId() {
        return this.bizArticleLove.getId();
    }

    public void setId(Long id) {
        this.bizArticleLove.setId(id);
    }

    public long getArticleId() {
        return this.bizArticleLove.getArticleId();
    }

    public void setArticleId(long articleId) {
        this.bizArticleLove.setArticleId(articleId);
    }

    public long getUserId() {
        return this.bizArticleLove.getUserId();
    }

    public void setUserId(long userId) {
        this.bizArticleLove.setUserId(userId);
    }

    public String getUserIp() {
        return this.bizArticleLove.getUserIp();
    }

    public void setUserIp(String userIp) {
        this.bizArticleLove.setUserIp(userIp);
    }

    public Date getLoveTime() {
        return this.bizArticleLove.getLoveTime();
    }

    public void setLoveTime(Date loveTime) {
        this.bizArticleLove.setLoveTime(loveTime);
    }

    public Date getCreateTime() {
        return this.bizArticleLove.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticleLove.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.bizArticleLove.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticleLove.setUpdateTime(updateTime);
    }

}

