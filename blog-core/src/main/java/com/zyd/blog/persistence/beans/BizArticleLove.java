package com.zyd.blog.persistence.beans;

import com.zyd.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizArticleLove extends AbstractDO {
    private Long articleId;
    private Long userId;
    private String userIp;
    private Date loveTime;
}
