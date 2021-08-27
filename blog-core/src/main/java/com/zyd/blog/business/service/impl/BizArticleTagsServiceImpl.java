package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.entity.ArticleTags;
import com.zyd.blog.business.service.BizArticleTagsService;
import com.zyd.blog.persistence.beans.BizArticleTags;
import com.zyd.blog.persistence.mapper.BizArticleTagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章标签
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizArticleTagsServiceImpl implements BizArticleTagsService {

    @Autowired
    private BizArticleTagsMapper bizArticleTagsMapper;

    /**
     * 通过文章id删除文章-标签关联数据
     *
     * @param articleId
     * @return
     */
    @Override
    public int removeByArticleId(Long articleId) {
        // 删除 文章-标签关联数据
        Example loveExample = new Example(BizArticleTags.class);
        Example.Criteria loveCriteria = loveExample.createCriteria();
        loveCriteria.andEqualTo("articleId", articleId);
        return bizArticleTagsMapper.deleteByExample(loveExample);
    }

    /**
     * 批量添加
     *
     * @param tagIds
     * @param articleId
     */
    @Override
    public void insertList(Long[] tagIds, Long articleId) {
        if (tagIds == null || tagIds.length == 0) {
            return;
        }
        List<ArticleTags> list = new ArrayList<>();
        ArticleTags articleTags = null;
        for (Long tagId : tagIds) {
            articleTags = new ArticleTags();
            articleTags.setTagId(tagId);
            articleTags.setArticleId(articleId);
            list.add(articleTags);
        }
        this.insertList(list);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleTags insert(ArticleTags entity) {
        Assert.notNull(entity, "ArticleTags不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizArticleTagsMapper.insertSelective(entity.getBizArticleTags());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<ArticleTags> entities) {
        Assert.notNull(entities, "ArticleTagss不可为空！");
        List<BizArticleTags> list = new ArrayList<>();
        for (ArticleTags entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            list.add(entity.getBizArticleTags());
        }
        bizArticleTagsMapper.insertList(list);
    }
}
