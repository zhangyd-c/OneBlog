package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.entity.BizArticleContentBo;
import com.zyd.blog.business.service.BizArticleContentService;
import com.zyd.blog.framework.exception.ZhydException;
import com.zyd.blog.persistence.beans.BizArticleContent;
import com.zyd.blog.persistence.beans.BizPage;
import com.zyd.blog.persistence.mapper.BizArticleContentMapper;
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
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2025/02/26 23:48
 * @since 1.8
 */
@Service
public class BizArticleContentServiceImpl implements BizArticleContentService {

    @Autowired
    private BizArticleContentMapper bizArticleContentMapper;

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizArticleContentBo insert(BizArticleContentBo entity) {
        Assert.notNull(entity, "BizArticleContent不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizArticleContentMapper.insertSelective(entity.getBizArticleContent());
        return entity;
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return bizArticleContentMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(BizArticleContentBo entity) {
        Assert.notNull(entity, "BizArticleContent不可为空！");
        Assert.notNull(entity.getArticleId(), "ArticleId不可为空！");
        long articleId = entity.getArticleId();
        Example example = new Example(BizArticleContent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        BizArticleContent content = bizArticleContentMapper.selectOneByExample(example);
        if (null == content) {
            throw new ZhydException("文章不存在或者已被删除");
        }
        content.setContent(entity.getContent());
        content.setContentMd(entity.getContentMd());
        content.setUpdateTime(new Date());
        return bizArticleContentMapper.updateByPrimaryKeySelective(content) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public BizArticleContentBo getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizArticleContent entity = bizArticleContentMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new BizArticleContentBo(entity);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<BizArticleContentBo> listAll() {
        List<BizArticleContent> entityList = bizArticleContentMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<BizArticleContentBo> list = new ArrayList<>();
        for (BizArticleContent entity : entityList) {
            list.add(new BizArticleContentBo(entity));
        }
        return list;
    }

    @Override
    public void removeByArticleId(long articleId) {
        Example example = new Example(BizArticleContent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        BizArticleContent content = bizArticleContentMapper.selectOneByExample(example);
        if (null == content) {
            throw new ZhydException("文章不存在或者已被删除");
        }
        this.removeByPrimaryKey(content.getId());
    }
}
