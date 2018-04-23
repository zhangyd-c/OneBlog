/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.business.consts.DateConst;
import com.zyd.blog.persistence.beans.SysUpdateRecorde;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
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

