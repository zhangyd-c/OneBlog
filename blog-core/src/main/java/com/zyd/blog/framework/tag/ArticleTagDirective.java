/**
 * MIT License
 *
 * Copyright (c) 2018 yadong.zhang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.framework.tag;

import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.enums.ArticleStatusEnum;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.business.vo.ArticleConditionVO;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 文章相关的自定义标签
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Component
public class ArticleTagDirective implements TemplateDirectiveModel {
    private static final String METHOD_KEY = "method";

    @Autowired
    private BizArticleService articleService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        if (map.containsKey(METHOD_KEY)) {
            String method = map.get(METHOD_KEY).toString();
            int pageSize = 10;
            if (map.containsKey("pageSize")) {
                String pageSizeStr = map.get("pageSize").toString();
                pageSize = Integer.parseInt(pageSizeStr);
            }
            long typeId = -1;
            if (map.containsKey("typeId")) {
                String typeStr = map.get("typeId").toString();
                typeId = Long.parseLong(typeStr);
            }

            switch (method) {
                case "recentArticles":
                    // 近期文章
                    environment.setVariable("recentArticles", builder.build().wrap(articleService.listRecent(pageSize)));
                    break;
                case "recommendedList":
                    // 站长推荐
                    environment.setVariable("recommendedList", builder.build().wrap(articleService.listRecommended(pageSize)));
                    break;
                case "randomList":
                    // 随机文章
                    environment.setVariable("randomList", builder.build().wrap(articleService.listRandom(pageSize)));
                    break;
                case "hotList":
                    // 热门文章
                    environment.setVariable("hotList", builder.build().wrap(articleService.listHotArticle(pageSize)));
                    break;
                case "typeList":
                    // 按文章分类查询
                    ArticleConditionVO vo = new ArticleConditionVO();
                    vo.setTypeId(typeId);
                    // 已发布状态
                    vo.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
                    vo.setPageSize(pageSize);
                    PageInfo<Article> pageInfo = articleService.findPageBreakByCondition(vo);
                    environment.setVariable("typeList", builder.build().wrap(null == pageInfo ? null : pageInfo.getList()));
                    break;
                default:
                    break;
            }
        }
        templateDirectiveBody.render(environment.getOut());
    }
}
