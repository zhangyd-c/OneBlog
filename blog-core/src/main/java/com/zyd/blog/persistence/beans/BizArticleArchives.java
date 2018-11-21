package com.zyd.blog.persistence.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;

/**
 * 文章归档
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizArticleArchives {
    @Transient
    Long id;
    @Transient
    private String title;
    @Transient
    private String original;
    @Transient
    private String datetime;
}
