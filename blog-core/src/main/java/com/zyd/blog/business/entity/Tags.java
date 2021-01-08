package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizTags;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Tags {
    private BizTags bizTags;

    public Tags() {
        this.bizTags = new BizTags();
    }

    public Tags(BizTags bizTags) {
        this.bizTags = bizTags;
    }

    @JsonIgnore
    public BizTags getBizTags() {
        return this.bizTags;
    }

    public Long getId() {
        return this.bizTags.getId();
    }

    public void setId(Long id) {
        this.bizTags.setId(id);
    }

    public String getName() {
        return this.bizTags.getName();
    }

    public void setName(String name) {
        this.bizTags.setName(name);
    }

    public String getDescription() {
        return this.bizTags.getDescription();
    }

    public void setDescription(String description) {
        this.bizTags.setDescription(description);
    }

    public Date getCreateTime() {
        return this.bizTags.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizTags.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.bizTags.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizTags.setUpdateTime(updateTime);
    }

}

