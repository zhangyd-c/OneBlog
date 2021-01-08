package com.zyd.blog.business.vo;

import com.zyd.blog.business.entity.Link;
import com.zyd.blog.framework.object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LinkConditionVO extends BaseConditionVO {
	private Link link;
	private Integer status;
	private Integer homePageDisplay;

	public LinkConditionVO() {
	}

	public LinkConditionVO(Integer status, Integer homePageDisplay) {
		this.status = status;
		this.homePageDisplay = homePageDisplay;
	}
}

