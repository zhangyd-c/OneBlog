package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zyd.blog.business.enums.CommentStatusEnum;
import com.zyd.blog.business.enums.ExtraCommentTypeEnum;
import com.zyd.blog.persistence.beans.BizArticle;
import com.zyd.blog.persistence.beans.BizComment;
import com.zyd.blog.util.HtmlUtil;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    private BizComment bizComment;

    public Comment() {
        this.bizComment = new BizComment();
    }

    public Comment(BizComment bizComment) {
        this.bizComment = bizComment;
    }

    @JsonIgnore
    public BizComment getBizComment() {
        return this.bizComment;
    }

    public Long getId() {
        return this.bizComment.getId();
    }

    public void setId(Long id) {
        this.bizComment.setId(id);
    }

    public Long getSid() {
        return this.bizComment.getSid();
    }

    public void setSid(Long sid) {
        this.bizComment.setSid(sid);
    }

    /**
     * 获取评论文章的地址
     *
     * @return
     */
    public String getSourceUrl() {
        Long sid = getSid();
        ExtraCommentTypeEnum extraCommentType = ExtraCommentTypeEnum.getBySid(sid);
        if (null == extraCommentType) {
            return "";
        }
        if (extraCommentType.equals(ExtraCommentTypeEnum.ARTICLE)) {
            return extraCommentType.getUrl() + sid;
        }
        return extraCommentType.getUrl();
    }

    public String getArticleTitle() {
        BizArticle article = this.getArticle();
        String title = null == article ? null : article.getTitle();
        if (title == null) {
            Long sid = getSid();
            ExtraCommentTypeEnum extraCommentType = ExtraCommentTypeEnum.getBySid(sid);
            title = extraCommentType.getTitle();
        }
        return title;
    }

    public Long getUserId() {
        return this.bizComment.getUserId();
    }

    public void setUserId(Long userId) {
        this.bizComment.setUserId(userId);
    }

    public Long getPid() {
        return this.bizComment.getPid();
    }

    public void setPid(Long pid) {
        this.bizComment.setPid(pid);
    }

    public String getQq() {
        return this.bizComment.getQq();
    }

    public void setQq(String qq) {
        this.bizComment.setQq(qq);
    }

    public String getNickname() {
        return this.bizComment.getNickname();
    }

    public void setNickname(String nickname) {
        this.bizComment.setNickname(nickname);
    }

    public String getAvatar() {
        return this.bizComment.getAvatar();
    }

    public void setAvatar(String avatar) {
        this.bizComment.setAvatar(avatar);
    }

    public String getEmail() {
        return this.bizComment.getEmail();
    }

    public void setEmail(String email) {
        this.bizComment.setEmail(email);
    }

    public String getUrl() {
        return this.bizComment.getUrl();
    }

    public void setUrl(String url) {
        this.bizComment.setUrl(url);
    }

    public String getStatus() {
        return this.bizComment.getStatus();
    }

    public void setStatus(String status) {
        this.bizComment.setStatus(status);
    }

    public String getStatusDesc() {
        return CommentStatusEnum.valueOf(this.bizComment.getStatus()).getDesc();
    }

    public String getIp() {
        return this.bizComment.getIp();
    }

    public void setIp(String ip) {
        this.bizComment.setIp(ip);
    }

    public String getLng() {
        return this.bizComment.getLng();
    }

    public void setLng(String lng) {
        this.bizComment.setLng(lng);
    }

    public String getLat() {
        return this.bizComment.getLat();
    }

    public void setLat(String lat) {
        this.bizComment.setLat(lat);
    }

    public String getAddress() {
        return this.bizComment.getAddress();
    }

    public void setAddress(String address) {
        this.bizComment.setAddress(address);
    }

    public String getOs() {
        return this.bizComment.getOs();
    }

    public void setOs(String os) {
        this.bizComment.setOs(os);
    }

    public String getOsShortName() {
        return this.bizComment.getOsShortName();
    }

    public void setOsShortName(String osShortName) {
        this.bizComment.setOsShortName(osShortName);
    }

    public String getBrowser() {
        return this.bizComment.getBrowser();
    }

    public void setBrowser(String browser) {
        this.bizComment.setBrowser(browser);
    }

    public String getBrowserShortName() {
        return this.bizComment.getBrowserShortName();
    }

    public void setBrowserShortName(String browserShortName) {
        this.bizComment.setBrowserShortName(browserShortName);
    }

    public String getContent() {
        return this.bizComment.getContent();
    }

    public void setContent(String content) {
        this.bizComment.setContent(content);
    }

    /**
     * 简短内容
     *
     * @return
     */
    public String getBriefContent() {
        String content = getContent();
        if (!StringUtils.isEmpty(content)) {
            content = HtmlUtil.html2Text(content);
            if (content.length() > 15) {
                content = content.substring(0, 15) + "...";
            }
        }
        return content;
    }

    public String getRemark() {
        return this.bizComment.getRemark();
    }

    public void setRemark(String remark) {
        this.bizComment.setRemark(remark);
    }

    public Integer getSupport() {
        return this.bizComment.getSupport();
    }

    public void setSupport(Integer support) {
        this.bizComment.setSupport(support);
    }

    public Integer getOppose() {
        return this.bizComment.getOppose();
    }

    public void setOppose(Integer oppose) {
        this.bizComment.setOppose(oppose);
    }

    public Date getCreateTime() {
        return this.bizComment.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizComment.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTimeString() {
        return this.bizComment.getCreateTime();
    }

    public Date getUpdateTime() {
        return this.bizComment.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizComment.setUpdateTime(updateTime);
    }

    public BizComment getParent() {
        return this.bizComment.getParent();
    }

    public void setParent(BizComment parent) {
        this.bizComment.setParent(parent);
    }

    public BizArticle getArticle() {
        return this.bizComment.getArticle();
    }

    public void getArticle(BizArticle article) {
        this.bizComment.setArticle(article);
    }

}

