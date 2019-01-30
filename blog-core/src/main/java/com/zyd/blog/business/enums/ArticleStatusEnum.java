package com.zyd.blog.business.enums;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public enum ArticleStatusEnum {
    PUBLISHED(1, "发布"),
    UNPUBLISHED(0, "草稿");
    private int code;
    private String desc;

    ArticleStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ArticleStatusEnum get(Integer code) {
        if (code == null) {
            return UNPUBLISHED;
        }
        ArticleStatusEnum[] statusEnums = ArticleStatusEnum.values();
        for (ArticleStatusEnum statusEnum : statusEnums) {
            if (statusEnum.getCode() == code) {
                return statusEnum;
            }
        }
        return UNPUBLISHED;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
