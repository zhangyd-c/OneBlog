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
import com.zyd.blog.persistence.beans.BizTags;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
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

