package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.business.enums.LogLevelEnum;
import com.zyd.blog.business.enums.LogTypeEnum;
import com.zyd.blog.persistence.beans.SysLog;

import java.util.Date;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/01/09 17:40
 * @since 1.0
 */
public class Log {
    private static final long serialVersionUID = 1L;
    private SysLog sysLog;

    /* generateConstructor */
    public Log() {
        this.sysLog = new SysLog();
    }

    public Log(SysLog sysLog) {
        this.sysLog = sysLog;
    }

    @JsonIgnore
    public SysLog getSysLog() {
        return this.sysLog;
    }

    public Long getId() {
        return this.sysLog.getId();
    }

    public void setId(Long id) {
        this.sysLog.setId(id);
    }

    public Long getUserId() {
        return this.sysLog.getUserId();
    }

    public void setUserId(Long userId) {
        this.sysLog.setUserId(userId);
    }

    public String getLogLevel() {
        return this.sysLog.getLogLevel();
    }

    public void setLogLevel(LogLevelEnum logLevel) {
        if (null == logLevel) {
            return;
        }
        this.sysLog.setLogLevel(logLevel.toString());
    }

    public void setLogLevel(String logLevel) {
        this.sysLog.setLogLevel(logLevel);
    }

    public String getIp() {
        return this.sysLog.getIp();
    }

    public void setIp(String ip) {
        this.sysLog.setIp(ip);
    }

    public String getContent() {
        return this.sysLog.getContent();
    }

    public void setContent(String content) {
        this.sysLog.setContent(content);
    }


    public String getParams() {
        return this.sysLog.getParams();
    }

    public void setParams(String params) {
        this.sysLog.setParams(params);
    }

    public String getType() {
        return this.sysLog.getType();
    }

    public void setType(LogTypeEnum type) {
        if (null == type) {
            return;
        }
        this.sysLog.setType(type.toString());
    }

    public void setType(String type) {
        this.sysLog.setType(type);
    }

    public String getUa() {
        return this.sysLog.getUa();
    }

    public void setUa(String ua) {
        this.sysLog.setUa(ua);
    }

    public String getOs() {
        return this.sysLog.getOs();
    }

    public void setOs(String os) {
        this.sysLog.setOs(os);
    }

    public String getBrowser() {
        return this.sysLog.getBrowser();
    }

    public void setBrowser(String browser) {
        this.sysLog.setBrowser(browser);
    }

    public String getRequestUrl() {
        return this.sysLog.getRequestUrl();
    }

    public void setRequestUrl(String requestUrl) {
        this.sysLog.setRequestUrl(requestUrl);
    }

    public String getReferer() {
        return this.sysLog.getReferer();
    }

    public void setReferer(String referer) {
        this.sysLog.setReferer(referer);
    }

    public String getSpiderType() {
        return this.sysLog.getSpiderType();
    }

    public void setSpiderType(String spiderType) {
        this.sysLog.setSpiderType(spiderType);
    }

    public Date getCreateTime() {
        return this.sysLog.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.sysLog.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.sysLog.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysLog.setUpdateTime(updateTime);
    }

}

