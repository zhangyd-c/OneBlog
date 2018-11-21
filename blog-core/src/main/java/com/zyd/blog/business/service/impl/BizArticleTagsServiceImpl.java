package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.ArticleTags;
import com.zyd.blog.business.service.BizArticleTagsService;
import com.zyd.blog.business.vo.ArticleTagsConditionVO;
import com.zyd.blog.persistence.beans.BizArticleTags;
import com.zyd.blog.persistence.mapper.BizArticleTagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章标签
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizArticleTagsServiceImpl implements BizArticleTagsService {

    @Autowired
    private BizArticleTagsMapper bizArticleTagsMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<ArticleTags> findPageBreakByCondition(ArticleTagsConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizArticleTags> list = bizArticleTagsMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<ArticleTags> boList = new ArrayList<>();
        for (BizArticleTags bizArticleTags : list) {
            boList.add(new ArticleTags(bizArticleTags));
        }
        PageInfo bean = new PageInfo<BizArticleTags>(list);
        bean.setList(boList);
        return bean;
    }

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

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Integer primaryKey) {
        return bizArticleTagsMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ArticleTags entity) {
        Assert.notNull(entity, "ArticleTags不可为空！");
        entity.setUpdateTime(new Date());
        return bizArticleTagsMapper.updateByPrimaryKey(entity.getBizArticleTags()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(ArticleTags entity) {
        Assert.notNull(entity, "ArticleTags不可为空！");
        entity.setUpdateTime(new Date());
        return bizArticleTagsMapper.updateByPrimaryKeySelective(entity.getBizArticleTags()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public ArticleTags getByPrimaryKey(Integer primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizArticleTags entity = bizArticleTagsMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new ArticleTags(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public ArticleTags getOneByEntity(ArticleTags entity) {
        Assert.notNull(entity, "ArticleTags不可为空！");
        BizArticleTags bo = bizArticleTagsMapper.selectOne(entity.getBizArticleTags());
        return null == bo ? null : new ArticleTags(bo);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<ArticleTags> listAll() {
        List<BizArticleTags> entityList = bizArticleTagsMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<ArticleTags> list = new ArrayList<>();
        for (BizArticleTags entity : entityList) {
            list.add(new ArticleTags(entity));
        }
        return list;
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public List<ArticleTags> listByEntity(ArticleTags entity) {
        Assert.notNull(entity, "ArticleTags不可为空！");
        List<BizArticleTags> entityList = bizArticleTagsMapper.select(entity.getBizArticleTags());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<ArticleTags> list = new ArrayList<>();
        for (BizArticleTags po : entityList) {
            list.add(new ArticleTags(po));
        }
        return list;
    }
}
