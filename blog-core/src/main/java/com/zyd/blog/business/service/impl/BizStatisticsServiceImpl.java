/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.entity.Statistics;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.business.service.BizStatisticsService;
import com.zyd.blog.persistence.beans.BizStatistics;
import com.zyd.blog.persistence.mapper.BizStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizStatisticsServiceImpl implements BizStatisticsService {

    @Autowired
    private BizStatisticsMapper statisticsMapper;
    @Autowired
    private BizArticleService articleService;

    /**
     * 获取热门文章
     *
     * @return
     */
    @Override
    public List<Article> listHotArticle(int pageSize) {
        return articleService.listHotArticle(pageSize);
    }

    /**
     * 获取爬虫统计
     *
     * @return
     */
    @Override
    public List<Statistics> listSpider(int pageSize) {
        PageHelper.startPage(1, pageSize);
        List<BizStatistics> entityList = statisticsMapper.listSpider();
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Statistics> list = new ArrayList<>();
        for (BizStatistics entity : entityList) {
            list.add(new Statistics(entity));
        }
        return list;
    }

    /**
     * 获取文章分类统计
     *
     * @return
     */
    @Override
    public List<Statistics> listType(int pageSize) {
        PageHelper.startPage(1, pageSize);
        List<BizStatistics> entityList = statisticsMapper.listType();
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Statistics> list = new ArrayList<>();
        for (BizStatistics entity : entityList) {
            list.add(new Statistics(entity));
        }
        return list;
    }

}
