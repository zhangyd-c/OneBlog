package com.zyd.blog.persistence.beans;

import com.zyd.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @date 2021/10/23 00:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizPage extends AbstractDO {
    private String url;
    private String content;
}
