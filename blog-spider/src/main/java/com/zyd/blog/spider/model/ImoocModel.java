package com.zyd.blog.spider.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.List;

/**
 * 本地跑测试用
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/7/23 15:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImoocModel extends BaseModel {
    @Override
    public String[] getEntryUrls() {
        List<String> urls = new LinkedList<>();
        String urlFormat = "https://www.imooc.com/u/%s/articles?page=%s";
        for (int i = 1; i <= getTotalPage(); i++) {
            urls.add(String.format(urlFormat, getUid(), i));
        }
        return urls.toArray(new String[urls.size()]);
    }
}
