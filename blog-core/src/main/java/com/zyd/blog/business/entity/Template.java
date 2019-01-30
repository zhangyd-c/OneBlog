package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.SysTemplate;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Template {
    private SysTemplate sysTemplate;

    public Template() {
        this.sysTemplate = new SysTemplate();
    }

    public Template(SysTemplate sysTemplate) {
        this.sysTemplate = sysTemplate;
    }

    @JsonIgnore
    public SysTemplate getSysTemplate() {
        return this.sysTemplate;
    }

    public Long getId() {
        return this.sysTemplate.getId();
    }

    public void setId(Long id) {
        this.sysTemplate.setId(id);
    }

    public String getRefKey() {
        return this.sysTemplate.getRefKey();
    }

    public void setRefKey(String refKey) {
        this.sysTemplate.setRefKey(refKey);
    }

    public String getRefValue() {
        return this.sysTemplate.getRefValue();
    }

    public void setRefValue(String refValue) {
        this.sysTemplate.setRefValue(refValue);
    }

    public Date getCreateTime() {
        return this.sysTemplate.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.sysTemplate.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.sysTemplate.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysTemplate.setUpdateTime(updateTime);
    }

}

