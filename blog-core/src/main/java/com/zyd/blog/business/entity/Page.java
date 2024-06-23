/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2021
 */
package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizPage;

import java.util.Date;

/**
 * @author yadong.zhang email:yadong.zhang(a)innodev.com.cn
 * @version 1.0
 * @date 2021/10/23 00:19
 * @since 1.0
 */
public class Page {
    private static final long serialVersionUID = 1L;
    private final BizPage bizPage;

    public Page() {
        this.bizPage = new BizPage();
    }

    public Page(BizPage bizPage) {
        this.bizPage = bizPage;
    }

    @JsonIgnore
    public BizPage getBizPage() {
        return this.bizPage;
    }

    public Long getId() {
        return this.bizPage.getId();
    }

    public void setId(Long id) {
        this.bizPage.setId(id);
    }

    public String getUrl() {
        return this.bizPage.getUrl();
    }

    public void setUrl(String url) {
        this.bizPage.setUrl(url);
    }

    public String getContent() {
        return this.bizPage.getContent();
    }

    public void setContent(String content) {
        this.bizPage.setContent(content);
    }

    public Date getCreateTime() {
        return this.bizPage.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizPage.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.bizPage.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizPage.setUpdateTime(updateTime);
    }
}
