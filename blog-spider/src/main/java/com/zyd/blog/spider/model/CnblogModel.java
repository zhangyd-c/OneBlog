package com.zyd.blog.spider.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.LinkedList;
import java.util.List;

/**
 * 本地跑测试用
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/7/23 15:58
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CnblogModel extends BaseModel {

    @NotEmpty(message = "必须指定待抓取的网址")
    @Override
    public String[] getEntryUrls() {
        List<String> urls = new LinkedList<>();
        String urlFormat = "https://www.cnblogs.com/%s/default.html?page=%s";
        for (int i = 1; i <= getTotalPage(); i++) {
            urls.add(String.format(urlFormat, getUid(), i));
        }
        return urls.toArray(new String[urls.size()]);
    }
}
