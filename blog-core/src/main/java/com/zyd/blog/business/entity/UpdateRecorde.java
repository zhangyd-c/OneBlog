package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.business.consts.DateConst;
import com.zyd.blog.persistence.beans.SysUpdateRecorde;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class UpdateRecorde {
    private SysUpdateRecorde sysUpdateRecorde;

    public UpdateRecorde() {
        this.sysUpdateRecorde = new SysUpdateRecorde();
    }

    public UpdateRecorde(SysUpdateRecorde sysUpdateRecorde) {
        this.sysUpdateRecorde = sysUpdateRecorde;
    }

    @JsonIgnore
    public SysUpdateRecorde getSysUpdateRecorde() {
        return this.sysUpdateRecorde;
    }

    public Long getId() {
        return this.sysUpdateRecorde.getId();
    }

    public void setId(Long id) {
        this.sysUpdateRecorde.setId(id);
    }

    public String getVersion() {
        return this.sysUpdateRecorde.getVersion();
    }

    public void setVersion(String version) {
        this.sysUpdateRecorde.setVersion(version);
    }

    public String getDescription() {
        return this.sysUpdateRecorde.getDescription();
    }

    public void setDescription(String description) {
        this.sysUpdateRecorde.setDescription(description);
    }

    @JsonFormat(timezone = "GMT+8", pattern = DateConst.YYYY_MM_DD_HH_MM_SS_EN)
    public Date getRecordeTime() {
        return this.sysUpdateRecorde.getRecordeTime();
    }

    @DateTimeFormat(pattern = DateConst.YYYY_MM_DD_HH_MM_SS_EN)
    public void setRecordeTime(Date recordeTime) {
        this.sysUpdateRecorde.setRecordeTime(recordeTime);
    }

    public Date getCreateTime() {
        return this.sysUpdateRecorde.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.sysUpdateRecorde.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.sysUpdateRecorde.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysUpdateRecorde.setUpdateTime(updateTime);
    }

}

