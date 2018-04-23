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
import com.zyd.blog.persistence.beans.BizArticleArchives;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class ArticleArchives {

    private BizArticleArchives bizArticleArchives;
    private List<Article> articleList;

    public ArticleArchives(BizArticleArchives bizArticleArchives) {
        this.bizArticleArchives = bizArticleArchives;
    }

    public ArticleArchives() {
    }

    @JsonIgnore
    public BizArticleArchives getBizArticleArchives() {
        return bizArticleArchives;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Long getId() {
        return this.bizArticleArchives.getId();
    }

    public void setId(Long id) {
        this.bizArticleArchives.setId(id);
    }

    public String getTitle() {
        return this.bizArticleArchives.getTitle();
    }

    public void setTitle(String title) {
        this.bizArticleArchives.setTitle(title);
    }

    public String getOriginal() {
        return this.bizArticleArchives.getOriginal();
    }

    public void setOriginal(String original) {
        this.bizArticleArchives.setOriginal(original);
    }

    public String getDatetime() {
        return this.bizArticleArchives.getDatetime();
    }

    public void setDatetime(String datetime) {
        this.bizArticleArchives.setDatetime(datetime);
    }

    public String getDay() {
        String time = getDatetime();
        return time.substring(time.lastIndexOf("-") + 1);
    }
}
