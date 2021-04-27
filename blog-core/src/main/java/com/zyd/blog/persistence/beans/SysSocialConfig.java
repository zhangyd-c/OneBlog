package com.zyd.blog.persistence.beans;

import com.zyd.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yadong.zhang email:yadong.zhang(a)innodev.com.cn
 * @version 1.0
 * @date 2021/04/27 14:34
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysSocialConfig extends AbstractDO{
	private String clientId;
	private String clientSecret;
	private String redirectUri;
	private String platformName;
	private String platform;
	private String logo;
	private String alipayPublicKey;
	private Boolean unionId;
	private String stackOverflowKey;
	private String agentId;
	private String scope;
	private Boolean available;
	private Date createTime;
}
