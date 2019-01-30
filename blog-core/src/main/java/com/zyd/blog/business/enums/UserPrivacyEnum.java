package com.zyd.blog.business.enums;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public enum UserPrivacyEnum {
    PUBLIC(1, "公开"),
    PRIVATE(2, "不公开"),;
    private Integer code;
    private String desc;

    UserPrivacyEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserPrivacyEnum get(Integer code) {
        if (null == code) {
            return PUBLIC;
        }
        UserPrivacyEnum[] enums = UserPrivacyEnum.values();
        for (UserPrivacyEnum anEnum : enums) {
            if (anEnum.getCode().equals(code)) {
                return anEnum;
            }
        }
        return PUBLIC;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
