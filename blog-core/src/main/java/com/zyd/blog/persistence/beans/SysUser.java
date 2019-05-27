package com.zyd.blog.persistence.beans;

import com.zyd.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends AbstractDO {
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private String qq;
    private Date birthday;
    private Integer gender;
    private String avatar;
    private String userType;
    private String company;
    private String blog;
    private String location;
    private String source;
    private String uuid;
    private Integer privacy;
    private Integer notification;
    private Integer score;
    private Integer experience;
    private String regIp;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Integer loginCount;
    private String remark;
    private Integer status;
}
