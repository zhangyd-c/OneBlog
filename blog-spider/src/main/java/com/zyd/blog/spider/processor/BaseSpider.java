package com.zyd.blog.spider.processor;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/7/31 16:24
 * @since 1.0
 */
public interface BaseSpider<T> {

    /**
     * 运行爬虫并返回结果
     *
     * @return
     */
    CopyOnWriteArrayList<T> run();


}
