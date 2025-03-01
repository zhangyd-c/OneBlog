
package com.zyd.blog.business.entity;

import com.zyd.blog.persistence.beans.BizArticleContent;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * 
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2025/02/26 23:48
 * @since 1.8
 */
public class BizArticleContentBo {
	private static final long serialVersionUID = 1L;
	private BizArticleContent bizArticleContent;

	public BizArticleContentBo () {
		this.bizArticleContent = new BizArticleContent();
	}

	public BizArticleContentBo (BizArticleContent bizArticleContent) {
		this.bizArticleContent = bizArticleContent;
	}

	@JsonIgnore
	public BizArticleContent getBizArticleContent(){
		return this.bizArticleContent;
	}

	public Long getId() {
		return this.bizArticleContent.getId();
	}

	public void setId(Long id) {
		this.bizArticleContent.setId(id);
	}

	public Long getArticleId() {
		return this.bizArticleContent.getArticleId();
	}

	public void setArticleId(Long articleId) {
		this.bizArticleContent.setArticleId(articleId);
	}

	public String getContent() {
		return this.bizArticleContent.getContent();
	}

	public void setContent(String content) {
		this.bizArticleContent.setContent(content);
	}

	public String getContentMd() {
		return this.bizArticleContent.getContentMd();
	}

	public void setContentMd(String contentMd) {
		this.bizArticleContent.setContentMd(contentMd);
	}

	public Date getCreateTime() {
		return this.bizArticleContent.getCreateTime();
	}

	public void setCreateTime(Date createTime) {
		this.bizArticleContent.setCreateTime(createTime);
	}

	public Date getUpdateTime() {
		return this.bizArticleContent.getUpdateTime();
	}

	public void setUpdateTime(Date updateTime) {
		this.bizArticleContent.setUpdateTime(updateTime);
	}
}

