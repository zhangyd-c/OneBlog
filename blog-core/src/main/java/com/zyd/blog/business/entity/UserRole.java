package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.SysUserRole;

import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://docs.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class UserRole {

    private SysUserRole sysUserRole;

    public UserRole() {
        this.sysUserRole = new SysUserRole();
    }

    public UserRole(SysUserRole sysUserRole) {
        this.sysUserRole = sysUserRole;
    }

    @JsonIgnore
    public SysUserRole getSysUserRole() {
        return this.sysUserRole;
    }

    public Long getUserId() {
        return this.sysUserRole.getUserId();
    }

    public void setUserId(Long userId) {
        this.sysUserRole.setUserId(userId);
    }

    public Long getRoleId() {
        return this.sysUserRole.getRoleId();
    }

    public void setRoleId(Long roleId) {
        this.sysUserRole.setRoleId(roleId);
    }

    public Date getCreateTime() {
        return this.sysUserRole.getCreateTime();
    }

    public void setCreateTime(Date regTime) {
        this.sysUserRole.setCreateTime(regTime);
    }

    public Date getUpdateTime() {
        return this.sysUserRole.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysUserRole.setUpdateTime(updateTime);
    }
}
