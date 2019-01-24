package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.SysConfig;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Config {
    private SysConfig sysConfig;

    public Config() {
        this.sysConfig = new SysConfig();
    }

    public Config(SysConfig sysConfig) {
        this.sysConfig = sysConfig;
    }

    @JsonIgnore
    public SysConfig getSysConfig() {
        return this.sysConfig;
    }

    public Long getId() {
        return this.sysConfig.getId();
    }

    public void setId(Long id) {
        this.sysConfig.setId(id);
    }

    public String getConfigKey() {
        return this.sysConfig.getConfigKey();
    }

    public void setConfigKey(String key) {
        this.sysConfig.setConfigKey(key);
    }

    public String getConfigValue() {
        return this.sysConfig.getConfigValue();
    }

    public void setConfigValue(String value) {
        this.sysConfig.setConfigValue(value);
    }

    public Date getCreateTime() {
        return this.sysConfig.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.sysConfig.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.sysConfig.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysConfig.setUpdateTime(updateTime);
    }

}

