package com.zyd.blog.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 广告位置
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AdPositionEnum {

    /**
     * 首页顶部
     */
    HOMEPAGE_TOP("首页顶部"),
    /**
     * 每次刷新页面都弹窗显示，AdType 必须为 POP
     */
    HOMEPAGE_OPEN_SCREEN("首页开屏广告"),
    SIDEBAR_TOP("侧边栏顶部"),
    SIDEBAR_BOTTOM("侧边栏底部"),
    /**
     * 文章详情底部，分页（上一篇下一篇）上方，标签和版权的下方
     */
    ARTICLE_BOTTOM("文章详情底部"),
    /**
     * 适用于文章详情页、留言板、等存在评论框的页面
     */
    COMMENT_BOX_TOP("评论框顶部"),
    ;

    private String desc;

    public static List<Map<String, String>> toListMap() {
        AdPositionEnum[] adPositionEnums = AdPositionEnum.values();
        List<Map<String, String>> res = new LinkedList<>();
        Map<String, String> map = null;
        for (AdPositionEnum adPositionEnum : adPositionEnums) {
            map = new HashMap<>();
            map.put("name", adPositionEnum.name());
            map.put("desc", adPositionEnum.getDesc());
            res.add(map);
        }
        return res;
    }
}
