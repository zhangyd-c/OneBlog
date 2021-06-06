package com.zyd.blog.util;

import com.zyd.blog.business.consts.ApiUrlConst;

import java.text.MessageFormat;

/**
 * Url构建工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
public class UrlBuildUtil {

    private static final String GET_LOCATION_BY_IP = "{0}?ak={1}&coor=gcj02&ip={2}";
    private static final String BAIDU_PUSH_URL_PATTERN = "{0}{1}?site={2}&token={3}";

    /**
     * 根据ip获取定位信息的接口地址
     *
     * @param ip
     *         用户IP
     * @return
     */
    public static String getLocationByIp(String ip, String baiduApiAk) {
        return MessageFormat.format(GET_LOCATION_BY_IP, ApiUrlConst.BAIDU_API_GET_LOCATION_BY_IP, baiduApiAk, ip);
    }

    /**
     * 提交链接到百度的接口地址
     *
     * @param pushType
     *         urls: 推送, update: 更新, del: 删除
     * @param site
     *         待提交的站点
     * @param baiduPushToken
     *         百度推送的token，百度站长平台获取
     * @return
     */
    public static String getBaiduPushUrl(String pushType, String site, String baiduPushToken) {
        return MessageFormat.format(BAIDU_PUSH_URL_PATTERN, ApiUrlConst.BAIDU_PUSH_URL, pushType, site, baiduPushToken);
    }
}
