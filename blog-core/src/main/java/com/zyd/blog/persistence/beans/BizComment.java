package com.zyd.blog.persistence.beans;

import com.zyd.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizComment extends AbstractDO {
    @Transient
    BizComment parent;
    @Transient
    BizArticle article;
    private Long userId;
    private Long sid;
    private Long pid;
    private String qq;
    private String nickname;
    private String avatar;
    private String email;
    private String url;
    private String status;
    private String ip;
    private String lng;
    private String lat;
    private String address;
    private String os;
    private String osShortName;
    private String browser;
    private String browserShortName;
    private String content;
    private String remark;
    private Integer support;
    private Integer oppose;

    @Transient
    private SysUser user;
}
