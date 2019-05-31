package com.zyd.blog.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.business.enums.UserTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 评论详情，用于页面传输
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizCommentDTO {
    @JsonIgnore
    BizCommentDTO parentDTO;
    private Long id;
    @JsonIgnore
    private Date createTime;
    private Long sid;
    private Long pid;
    private String nickname;
    private String avatar;
    private String url;
    private String address;
    private String os;
    private String osShortName;
    private String browser;
    private String browserShortName;
    private String content;
    private Integer support;
    private Integer oppose;

    @JsonIgnore
    private UserTypeEnum userType;

    public boolean isRoot() {
        return null != userType && userType == UserTypeEnum.ROOT;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTimeString() {
        return this.getCreateTime();
    }

    public BizCommentDTO getParent() {
        return this.parentDTO;
    }
}
