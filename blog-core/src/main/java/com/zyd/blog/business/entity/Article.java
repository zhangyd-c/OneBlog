package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.business.enums.ArticleStatusEnum;
import com.zyd.blog.persistence.beans.BizArticle;
import com.zyd.blog.persistence.beans.BizTags;
import com.zyd.blog.persistence.beans.BizType;

import java.util.Date;
import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Article {
    private BizArticle bizArticle;

    public Article() {
        this.bizArticle = new BizArticle();
    }

    public Article(BizArticle bizArticle) {
        this.bizArticle = bizArticle;
    }

    @JsonIgnore
    public BizArticle getBizArticle() {
        return this.bizArticle;
    }

    public Long getId() {
        return this.bizArticle.getId();
    }

    public void setId(Long id) {
        this.bizArticle.setId(id);
    }

    public String getTitle() {
        return this.bizArticle.getTitle();
    }

    public void setTitle(String title) {
        this.bizArticle.setTitle(title);
    }

    public long getUserId() {
        return this.bizArticle.getUserId();
    }

    public void setUserId(long userId) {
        this.bizArticle.setUserId(userId);
    }

    public String getCoverImage() {
        return this.bizArticle.getCoverImage();
    }

    public void setCoverImage(String coverImage) {
        this.bizArticle.setCoverImage(coverImage);
    }

    public String getQrcodePath() {
        return this.bizArticle.getQrcodePath();
    }

    public void setQrcodePath(String qrcodePath) {
        this.bizArticle.setQrcodePath(qrcodePath);
    }

    public boolean getIsMarkdown() {
        Boolean value = this.bizArticle.getIsMarkdown();
        return null == value ? false : value;
    }

    public void setIsMarkdown(boolean isMarkdown) {
        this.bizArticle.setIsMarkdown(isMarkdown);
    }

    public String getContent() {
        return this.bizArticle.getContent();
    }

    public void setContent(String content) {
        this.bizArticle.setContent(content);
    }

    public String getContentMd() {
        return this.bizArticle.getContentMd();
    }

    public void setContentMd(String contentMd) {
        this.bizArticle.setContentMd(contentMd);
    }

    public boolean isTop() {
        Boolean value = this.bizArticle.getTop();
        return value != null ? value : false;
    }

    public void setTop(boolean top) {
        this.bizArticle.setTop(top);
    }


    public Long getTypeId() {
        return this.bizArticle.getTypeId();
    }

    public void setTypeId(Long type) {
        this.bizArticle.setTypeId(type);
    }

    public ArticleStatusEnum getStatusEnum() {
        return ArticleStatusEnum.get(this.bizArticle.getStatus());
    }

    public Integer getStatus() {
        return this.bizArticle.getStatus();
    }

    public void setStatus(Integer type) {
        this.bizArticle.setStatus(type);
    }

    public boolean getRecommended() {
        Boolean value = this.bizArticle.getRecommended();
        return value == null ? false : value;
    }

    public void setRecommended(Boolean value) {
        this.bizArticle.setRecommended(value);
    }

    public boolean isOriginal() {
        Boolean value = this.bizArticle.getOriginal();
        return value != null ? value : false;
    }

    public void setOriginal(boolean original) {
        this.bizArticle.setOriginal(original);
    }


    public String getDescription() {
        return this.bizArticle.getDescription();
    }

    public void setDescription(String description) {
        this.bizArticle.setDescription(description);
    }

    public String getKeywords() {
        return this.bizArticle.getKeywords();
    }

    public void setKeywords(String keywords) {
        this.bizArticle.setKeywords(keywords);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.bizArticle.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticle.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.bizArticle.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticle.setUpdateTime(updateTime);
    }

    public boolean isComment() {
        Boolean value = this.bizArticle.getComment();
        return value != null ? value : false;
    }

    public void setComment(boolean comment) {
        this.bizArticle.setComment(comment);
    }

    public List<BizTags> getTags() {
        return this.bizArticle.getTags();
    }

    public BizType getType() {
        return this.bizArticle.getBizType();
    }

    public int getLookCount(){
        Integer lookCount = this.bizArticle.getLookCount();
        return lookCount == null ? 0 : lookCount;
    }

    public int getCommentCount(){
        Integer commentCount = this.bizArticle.getCommentCount();
        return commentCount == null ? 0 : commentCount;
    }

    public int getLoveCount(){
        Integer loveCount = this.bizArticle.getLoveCount();
        return loveCount == null ? 0 : loveCount;
    }
}

