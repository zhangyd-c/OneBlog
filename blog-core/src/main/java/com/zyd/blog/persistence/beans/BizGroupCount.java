
package com.zyd.blog.persistence.beans;

import com.zyd.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 *
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2021/10/27 16:31
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizGroupCount extends AbstractDO{
	private Long id;
	private Long num;
}
