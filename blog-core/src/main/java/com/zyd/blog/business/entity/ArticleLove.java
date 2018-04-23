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
import com.zyd.blog.persistence.beans.BizArticleLove;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class ArticleLove {
    private static final long serialVersionUID = 1L;
    private BizArticleLove bizArticleLove;

    public ArticleLove() {
        this.bizArticleLove = new BizArticleLove();
    }

    public ArticleLove(BizArticleLove bizArticleLove) {
        this.bizArticleLove = bizArticleLove;
    }

    @JsonIgnore
    public BizArticleLove getBizArticleLove() {
        return this.bizArticleLove;
    }

    public Long getId() {
        return this.bizArticleLove.getId();
    }

    public void setId(Long id) {
        this.bizArticleLove.setId(id);
    }

    public long getArticleId() {
        return this.bizArticleLove.getArticleId();
    }

    public void setArticleId(long articleId) {
        this.bizArticleLove.setArticleId(articleId);
    }

    public long getUserId() {
        return this.bizArticleLove.getUserId();
    }

    public void setUserId(long userId) {
        this.bizArticleLove.setUserId(userId);
    }

    public String getUserIp() {
        return this.bizArticleLove.getUserIp();
    }

    public void setUserIp(String userIp) {
        this.bizArticleLove.setUserIp(userIp);
    }

    public Date getLoveTime() {
        return this.bizArticleLove.getLoveTime();
    }

    public void setLoveTime(Date loveTime) {
        this.bizArticleLove.setLoveTime(loveTime);
    }

    public Date getCreateTime() {
        return this.bizArticleLove.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticleLove.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.bizArticleLove.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticleLove.setUpdateTime(updateTime);
    }

}

