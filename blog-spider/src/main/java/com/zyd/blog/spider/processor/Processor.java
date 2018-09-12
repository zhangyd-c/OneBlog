package com.zyd.blog.spider.processor;

import com.zyd.blog.spider.model.BaseModel;
import us.codecraft.webmagic.Page;

/**
 * 页面解析接口
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/7/31 17:37
 */
public interface Processor {
    void process(Page page, BaseModel model);
}
