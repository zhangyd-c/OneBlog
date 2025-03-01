package com.zyd.blog.business.service;


import com.zyd.blog.business.entity.BizArticleContentBo;
import com.zyd.blog.framework.object.AbstractService;

/**
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2025/02/26 23:48
 * @since 1.8
 */
public interface BizArticleContentService extends AbstractService<BizArticleContentBo, Long> {

    void removeByArticleId(long articleId);
}
