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
package com.zyd.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.vo.ArticleConditionVO;
import com.zyd.blog.framework.object.AbstractService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章列表
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface BizArticleService extends AbstractService<Article, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Article> findPageBreakByCondition(ArticleConditionVO vo);

    /**
     * 站长推荐
     *
     * @param pageSize
     * @return
     */
    List<Article> listRecommended(int pageSize);

    /**
     * 近期文章
     *
     * @param pageSize
     * @return
     */
    List<Article> listRecent(int pageSize);

    /**
     * 随机文章
     *
     * @param pageSize
     * @return
     */
    List<Article> listRandom(int pageSize);

    /**
     * 获取热门文章
     *
     * @return
     */
    List<Article> listHotArticle(int pageSize);

    /**
     * 根据某篇文章获取与该文章相关的文章
     *
     * @return
     */
    List<Article> listRelatedArticle(int pageSize, Article article);

    /**
     * 获取上一篇和下一篇
     *
     * @param insertTime
     * @return
     */
    Map<String, Article> getPrevAndNextArticles(Date insertTime);

    /**
     * 文章点赞
     *
     * @param id
     */
    void doPraise(Long id);

    /**
     * 是否存在文章
     *
     * @param id
     * @return
     */
    boolean isExist(Long id);

    /**
     * 获取素材库
     *
     * @return
     */
    List<String> listMaterial();

    /**
     * 发布文章
     *a
     * @param article
     * @param tags
     * @param file
     * @return
     */
    boolean publish(Article article, Long[] tags, MultipartFile file);

    /**
     * 修改置顶、推荐
     *
     * @return
     */
    boolean updateTopOrRecommendedById(String type, Long id);

}
