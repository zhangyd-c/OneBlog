package com.zyd.blog.business.entity;

import com.zyd.blog.persistence.beans.SysSocialConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author yadong.zhang email:yadong.zhang(a)innodev.com.cn
 * @version 1.0
 * @date 2021/04/27 14:34
 * @since 1.0
 */
public class SocialConfig {
	private static final long serialVersionUID = 1L;
	private SysSocialConfig sysSocialConfig;
	
	public SocialConfig() {
		this.sysSocialConfig = new SysSocialConfig();
	}

	public SocialConfig(SysSocialConfig sysSocialConfig) {
		this.sysSocialConfig = sysSocialConfig;
	}

	public SysSocialConfig getSysSocialConfig(){
		return this.sysSocialConfig;
	}
	
	public Long getId() {
		return this.sysSocialConfig.getId();
	}

	public void setId(Long id) {
		this.sysSocialConfig.setId(id);
	}
	
	public String getClientId() {
		return this.sysSocialConfig.getClientId();
	}

	public void setClientId(String clientId) {
		this.sysSocialConfig.setClientId(clientId);
	}
	
	public String getClientSecret() {
		return this.sysSocialConfig.getClientSecret();
	}

	public void setClientSecret(String clientSecret) {
		this.sysSocialConfig.setClientSecret(clientSecret);
	}
	
	public String getRedirectUri() {
		return this.sysSocialConfig.getRedirectUri();
	}

	public void setRedirectUri(String redirectUri) {
		this.sysSocialConfig.setRedirectUri(redirectUri);
	}
	
	public String getPlatformName() {
		return this.sysSocialConfig.getPlatformName();
	}

	public void setPlatformName(String platformName) {
		this.sysSocialConfig.setPlatformName(platformName);
	}
	
	public String getPlatform() {
		return this.sysSocialConfig.getPlatform();
	}

	public void setPlatform(String platform) {
		this.sysSocialConfig.setPlatform(platform);
	}
	
	public String getLogo() {
		return this.sysSocialConfig.getLogo();
	}

	public void setLogo(String logo) {
		this.sysSocialConfig.setLogo(logo);
	}
	
	public String getAlipayPublicKey() {
		return this.sysSocialConfig.getAlipayPublicKey();
	}

	public void setAlipayPublicKey(String alipayPublicKey) {
		this.sysSocialConfig.setAlipayPublicKey(alipayPublicKey);
	}
	
	public boolean isUnionId() {
		Boolean value = this.sysSocialConfig.getUnionId();
		return value != null ? value : false;
	}

	public void setUnionId(boolean unionId) {
		this.sysSocialConfig.setUnionId(unionId);
	}
	
	public String getStackOverflowKey() {
		return this.sysSocialConfig.getStackOverflowKey();
	}

	public void setStackOverflowKey(String stackOverflowKey) {
		this.sysSocialConfig.setStackOverflowKey(stackOverflowKey);
	}
	
	public String getAgentId() {
		return this.sysSocialConfig.getAgentId();
	}

	public void setAgentId(String agentId) {
		this.sysSocialConfig.setAgentId(agentId);
	}
	
	public String getScope() {
		return this.sysSocialConfig.getScope();
	}

	public void setScope(String scope) {
		this.sysSocialConfig.setScope(scope);
	}
	
	public boolean isAvailable() {
		Boolean value = this.sysSocialConfig.getAvailable();
		return value != null ? value : false;
	}

	public void setAvailable(boolean available) {
		this.sysSocialConfig.setAvailable(available);
	}
	
	public Date getCreateTime() {
		return this.sysSocialConfig.getCreateTime();
	}

	public void setCreateTime(Date createTime) {
		this.sysSocialConfig.setCreateTime(createTime);
	}
	
	public Date getUpdateTime() {
		return this.sysSocialConfig.getUpdateTime();
	}

	public void setUpdateTime(Date updateTime) {
		this.sysSocialConfig.setUpdateTime(updateTime);
	}
}

