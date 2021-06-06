package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.business.enums.NoticeStatusEnum;
import com.zyd.blog.persistence.beans.SysNotice;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Notice {
    private SysNotice sysNotice;

    public Notice() {
        this.sysNotice = new SysNotice();
    }

    public Notice(SysNotice sysNotice) {
        this.sysNotice = sysNotice;
    }

    @JsonIgnore
    public SysNotice getSysNotice() {
        return this.sysNotice;
    }

    public Long getId() {
        return this.sysNotice.getId();
    }

    public void setId(Long id) {
        this.sysNotice.setId(id);
    }

    public long getUserId() {
        return this.sysNotice.getUserId();
    }

    public void setUserId(long userId) {
        this.sysNotice.setUserId(userId);
    }

    public String getStatus() {
        return this.sysNotice.getStatus();
    }

    public NoticeStatusEnum getStatusEnum() {
        return NoticeStatusEnum.valueOf(this.sysNotice.getStatus());
    }

    public void setStatus(String status) {
        this.sysNotice.setStatus(status);
    }

    public String getTitle() {
        return this.sysNotice.getTitle();
    }

    public void setTitle(String title) {
        this.sysNotice.setTitle(title);
    }

    public String getContent() {
        return this.sysNotice.getContent();
    }

    public void setContent(String content) {
        this.sysNotice.setContent(content);
    }

    public Date getCreateTime() {
        return this.sysNotice.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.sysNotice.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.sysNotice.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysNotice.setUpdateTime(updateTime);
    }

}

