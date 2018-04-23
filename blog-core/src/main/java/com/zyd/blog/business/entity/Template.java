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

