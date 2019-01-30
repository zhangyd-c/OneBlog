package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.business.enums.*;
import com.zyd.blog.framework.object.AbstractBO;
import com.zyd.blog.persistence.beans.SysUser;
import com.zyd.blog.util.PasswordUtil;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class User extends AbstractBO {
    private SysUser sysUser;

    public User() {
        this.sysUser = new SysUser();
    }

    public User(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public User(String loginname, String password) {
        this();
        setUsername(loginname);
        if (!StringUtils.isEmpty(password)) {
            try {
                setPassword(PasswordUtil.encrypt(password, loginname));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @JsonIgnore
    public SysUser getSysUser() {
        return this.sysUser;
    }

    public Long getId() {
        return this.sysUser.getId();
    }

    public void setId(Long id) {
        this.sysUser.setId(id);
    }

    public String getNickname() {
        return this.sysUser.getNickname();
    }

    public void setNickname(String nickname) {
        this.sysUser.setNickname(nickname);
    }

    public String getMobile() {
        return this.sysUser.getMobile();
    }

    public void setMobile(String mobile) {
        this.sysUser.setMobile(mobile);
    }

    public String getUsername() {
        return this.sysUser.getUsername();
    }

    public void setUsername(String username) {
        this.sysUser.setUsername(username);
    }

    public String getPassword() {
        return this.sysUser.getPassword();
    }

    public void setPassword(String password) {
        this.sysUser.setPassword(password);
    }

    public String getEmail() {
        return this.sysUser.getEmail();
    }

    public void setEmail(String email) {
        this.sysUser.setEmail(email);
    }

    public String getQq() {
        return this.sysUser.getQq();
    }

    public void setQq(String qq) {
        this.sysUser.setQq(qq);
    }

    public Date getBirthday() {
        return this.sysUser.getBirthday();
    }

    public void setBirthday(Date birthday) {
        this.sysUser.setBirthday(birthday);
    }

    public Integer getGender() {
        return this.sysUser.getGender();
    }

    public void setGender(UserGenderEnum gender) {
        if (gender != null && gender.getCode() != -1) {
            this.sysUser.setGender(gender.getCode());
        }
    }

    public void setGender(Integer Gender) {
        this.sysUser.setGender(Gender);
    }

    public String getAvatar() {
        return this.sysUser.getAvatar();
    }

    public void setAvatar(String avatar) {
        this.sysUser.setAvatar(avatar);
    }

    public Integer getPrivacy() {
        return this.sysUser.getPrivacy();
    }

    public void setPrivacy(Integer privacy) {
        this.sysUser.setPrivacy(privacy);
    }

    public UserPrivacyEnum getUserPrivacyEnum() {
        return UserPrivacyEnum.get(getPrivacy());
    }

    public Integer getNotification() {
        return this.sysUser.getNotification();
    }

    public void setNotification(UserNotificationEnum userNotificationEnum) {
        if (null != userNotificationEnum) {
            setNotification(userNotificationEnum.getCode());
        }
    }

    public void setNotification(Integer notification) {
        this.sysUser.setNotification(notification);
    }

    public UserNotificationEnum getUserNotificationEnum() {
        return UserNotificationEnum.get(getNotification());
    }

    public String getUserType() {
        return this.sysUser.getUserType();
    }

    public void setUserType(UserTypeEnum userTypeEnum) {
        if (null != userTypeEnum) {
            setUserType(userTypeEnum.toString());
        }
    }

    public void setUserType(String userType) {
        this.sysUser.setUserType(userType);
    }

    public UserTypeEnum getUserTypeEnum() {
        return UserTypeEnum.getByType(this.sysUser.getUserType());
    }

    public String getCompany() {
        return this.sysUser.getCompany();
    }

    public void setCompany(String company) {
        this.sysUser.setCompany(company);
    }

    public String getBlog() {
        return this.sysUser.getBlog();
    }

    public void setBlog(String blog) {
        this.sysUser.setBlog(blog);
    }

    public String getLocation() {
        return this.sysUser.getLocation();
    }

    public void setLocation(String location) {
        this.sysUser.setLocation(location);
    }

    public String getSource() {
        return this.sysUser.getSource();
    }

    public void setSource(String source) {
        this.sysUser.setSource(source);
    }

    public void setSource(UserSourceEnum source) {
        this.sysUser.setSource(source.toString());
    }

    public UserSourceEnum getSourceEnum() {
        return UserSourceEnum.valueOf(this.sysUser.getSource());
    }

    public Integer getScore() {
        return this.sysUser.getScore();
    }

    public void setScore(Integer score) {
        this.sysUser.setScore(score);
    }

    public Integer getExperience() {
        return this.sysUser.getExperience();
    }

    public void setExperience(Integer experience) {
        this.sysUser.setExperience(experience);
    }

    public String getRegIp() {
        return this.sysUser.getRegIp();
    }

    public void setRegIp(String regIp) {
        this.sysUser.setRegIp(regIp);
    }

    public String getLastLoginIp() {
        return this.sysUser.getLastLoginIp();
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.sysUser.setLastLoginIp(lastLoginIp);
    }

    public Date getLastLoginTime() {
        return this.sysUser.getLastLoginTime();
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.sysUser.setLastLoginTime(lastLoginTime);
    }

    public Integer getLoginCount() {
        return this.sysUser.getLoginCount();
    }

    public void setLoginCount(Integer loginCount) {
        this.sysUser.setLoginCount(loginCount);
    }

    public String getRemark() {
        return this.sysUser.getRemark();
    }

    public void setRemark(String remark) {
        this.sysUser.setRemark(remark);
    }

    public Integer getStatus() {
        return this.sysUser.getStatus();
    }

    public void setStatus(Integer status) {
        this.sysUser.setStatus(status);
    }

    public UserStatusEnum getStatusEnum() {
        return UserStatusEnum.get(this.sysUser.getStatus());
    }

    public Date getCreateTime() {
        return this.sysUser.getCreateTime();
    }

    public void setCreateTime(Date regTime) {
        this.sysUser.setCreateTime(regTime);
    }

    public Date getUpdateTime() {
        return this.sysUser.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysUser.setUpdateTime(updateTime);
    }

}
