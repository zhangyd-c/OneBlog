package com.zyd.blog.spider.model;

import com.alibaba.fastjson.JSONObject;
import com.zyd.blog.spider.util.PlatformUtil;

import java.util.Map;
import java.util.Set;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/21 14:14
 * @since 1.8
 */
public enum ModelParser {
    INSTANCE;

    public BaseModel parseByUrl(String url, String spiderConfig) {
        if (url == null || spiderConfig == null) {
            return null;
        }
        boolean isIteye = false;
        String br = "\r\n";
        JSONObject spiderConfigObj = JSONObject.parseObject(spiderConfig);
        JSONObject platformObj = null;
        if (PlatformUtil.INSTANCE.isCnblogs(url)) {
            platformObj = spiderConfigObj.getJSONObject("csblogs");
        } else if (PlatformUtil.INSTANCE.isCsdn(url)) {
            platformObj = spiderConfigObj.getJSONObject("csdn");
        } else if (PlatformUtil.INSTANCE.isImooc(url)) {
            platformObj = spiderConfigObj.getJSONObject("imooc");
        } else if (isIteye = PlatformUtil.INSTANCE.isIteye(url)) {
            platformObj = spiderConfigObj.getJSONObject("iteye");
        }
        if (null == platformObj) {
            return null;
        }
        Set<Map.Entry<String, Object>> entries = platformObj.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            if ("header".equals(entry.getKey())) {
                String header = "Host=" + PlatformUtil.INSTANCE.getHostAndNoneProtocol(url) + br;
                header += "Referer=" + PlatformUtil.INSTANCE.getHost(url);
                entry.setValue(header);
            } else if ("entryUrls".equals(entry.getKey())) {
                entry.setValue(new String[]{url});
            } else {
                if (isIteye && "domain".equals(entry.getKey())) {
                    entry.setValue(PlatformUtil.INSTANCE.getHostAndNoneProtocol(url));
                }
            }
        }
        return platformObj.toJavaObject(BaseModel.class);
    }
}
