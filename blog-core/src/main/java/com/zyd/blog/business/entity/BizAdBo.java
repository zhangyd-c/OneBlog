package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.business.consts.DateConst;
import com.zyd.blog.business.enums.AdPositionEnum;
import com.zyd.blog.business.enums.AdTypeEnum;
import com.zyd.blog.persistence.beans.BizAd;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2021/10/27 16:43
 * @since 1.8
 */
public class BizAdBo {
    private static final long serialVersionUID = 1L;
    private final BizAd bizAd;

    public BizAdBo() {
        this.bizAd = new BizAd();
    }

    public BizAdBo(BizAd bizAd) {
        this.bizAd = bizAd;
    }

    @JsonIgnore
    public BizAd getBizAd() {
        return this.bizAd;
    }

    public Long getId() {
        return this.bizAd.getId();
    }

    public void setId(Long id) {
        this.bizAd.setId(id);
    }

    public String getContent() {
        return this.bizAd.getContent();
    }

    public void setContent(String content) {
        this.bizAd.setContent(content);
    }

    public String getPicture() {
        return this.bizAd.getPicture();
    }

    public void setPicture(String picture) {
        this.bizAd.setPicture(picture);
    }

    public String getLink() {
        return this.bizAd.getLink();
    }

    public void setLink(String link) {
        this.bizAd.setLink(link);
    }

    public String getTitle() {
        return this.bizAd.getTitle();
    }

    public void setTitle(String title) {
        this.bizAd.setTitle(title);
    }

    public String getPosition() {
        return this.bizAd.getPosition();
    }

    public void setPosition(String position) {
        this.bizAd.setPosition(position);
    }

    public void setPosition(AdPositionEnum position) {
        this.bizAd.setPosition(position.name());
    }

    public AdPositionEnum getPositionEnum() {
        try {
            return AdPositionEnum.valueOf(this.getPosition());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getType() {
        return this.bizAd.getType();
    }

    public void setType(String type) {
        this.bizAd.setType(type);
    }

    public void setType(AdTypeEnum type) {
        this.bizAd.setType(type.name());
    }

    public AdTypeEnum getTypeEnum() {
        try {
            return AdTypeEnum.valueOf(this.getType());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @JsonFormat(timezone = "GMT+8", pattern = DateConst.YYYY_MM_DD_HH_MM_SS_EN)
    public Date getExpiringDate() {
        return this.bizAd.getExpiringDate();
    }

    public boolean getExpired() {
        return null != this.bizAd.getExpiringDate() && this.bizAd.getExpiringDate().before(new Date());
    }

    @DateTimeFormat(pattern = DateConst.YYYY_MM_DD_HH_MM_SS_EN)
    public void setExpiringDate(Date expiringDate) {
        this.bizAd.setExpiringDate(expiringDate);
    }

    public Integer getShowNumber() {
        return this.bizAd.getShowNumber();
    }

    public void setShowNumber(Integer showNumber) {
        this.bizAd.setShowNumber(showNumber);
    }

    public Integer getClickNumber() {
        return this.bizAd.getClickNumber();
    }

    public void setClickNumber(Integer clickNumber) {
        this.bizAd.setClickNumber(clickNumber);
    }

    public Date getCreateTime() {
        return this.bizAd.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizAd.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return this.bizAd.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizAd.setUpdateTime(updateTime);
    }
}

