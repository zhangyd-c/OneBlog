package com.zyd.blog.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 广告类型
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AdTypeEnum {

    /**
     *
     */
    IMG("图片"),
    POP("弹窗"),
    /**
     * 第三方的广告
     */
    JS("JS"),
    TXT("纯文本"),
    ;

    private String desc;

    public static List<Map<String, String>> toListMap() {
        AdTypeEnum[] adTypeEnums = AdTypeEnum.values();
        List<Map<String, String>> res = new LinkedList<>();
        Map<String, String> map = null;
        for (AdTypeEnum adTypeEnum : adTypeEnums) {
            map = new HashMap<>();
            map.put("name", adTypeEnum.name());
            map.put("desc", adTypeEnum.getDesc());
            res.add(map);
        }
        return res;
    }
}
