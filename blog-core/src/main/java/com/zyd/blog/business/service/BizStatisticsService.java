package com.zyd.blog.business.service;

import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.entity.Statistics;

import java.util.List;

/**
 * 统计
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface BizStatisticsService {
    /**
     * 获取热门文章
     *
     * @return
     */
    List<Article> listHotArticle(int pageSize);

    /**
     * 获取爬虫统计
     *
     * @return
     */
    List<Statistics> listSpider(int pageSize);

    /**
     * 获取文章分类统计
     *
     * @return
     */
    List<Statistics> listType(int pageSize);

}
