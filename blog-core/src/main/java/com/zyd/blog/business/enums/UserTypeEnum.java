package com.zyd.blog.business.enums;

import org.springframework.util.StringUtils;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public enum UserTypeEnum {
    ROOT("超级管理员"),
    ADMIN("管理员"),
    USER("系统会员"),
    UNKNOW("未知");
    private String desc;

    UserTypeEnum(String desc) {
        this.desc = desc;
    }

    public static UserTypeEnum getByType(String type) {
        if (StringUtils.isEmpty(type)) {
            return UserTypeEnum.UNKNOW;
        }
        for (UserTypeEnum ut : UserTypeEnum.values()) {
            if (ut.toString().equalsIgnoreCase(type)) {
                return ut;
            }
        }
        return UserTypeEnum.UNKNOW;
    }

    public static String getByTypeDesc(String type) {
        if (StringUtils.isEmpty(type)) {
            return UserTypeEnum.UNKNOW.getDesc();
        }

        for (UserTypeEnum ut : UserTypeEnum.values()) {
            if (ut.toString().equalsIgnoreCase(type)) {
                return ut.getDesc();
            }
        }

        return UserTypeEnum.UNKNOW.getDesc();
    }

    public String getDesc() {
        return desc;
    }
}
