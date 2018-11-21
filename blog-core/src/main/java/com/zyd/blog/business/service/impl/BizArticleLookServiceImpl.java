package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.ArticleLook;
import com.zyd.blog.business.service.BizArticleLookService;
import com.zyd.blog.business.vo.ArticleLookConditionVO;
import com.zyd.blog.persistence.beans.BizArticleLook;
import com.zyd.blog.persistence.mapper.BizArticleLookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章浏览记录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizArticleLookServiceImpl implements BizArticleLookService {

    @Autowired
    private BizArticleLookMapper bizArticleLookMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<ArticleLook> findPageBreakByCondition(ArticleLookConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizArticleLook> list = bizArticleLookMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<ArticleLook> boList = new ArrayList<>();
        for (BizArticleLook bizArticleLook : list) {
            boList.add(new ArticleLook(bizArticleLook));
        }
        PageInfo bean = new PageInfo<BizArticleLook>(list);
        bean.setList(boList);
        return bean;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleLook insert(ArticleLook entity) {
        Assert.notNull(entity, "ArticleLook不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizArticleLookMapper.insertSelective(entity.getBizArticleLook());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<ArticleLook> entities) {
        Assert.notNull(entities, "ArticleLooks不可为空！");
        List<BizArticleLook> list = new ArrayList<>();
        for (ArticleLook entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            list.add(entity.getBizArticleLook());
        }
        bizArticleLookMapper.insertList(list);
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
        return bizArticleLookMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ArticleLook entity) {
        Assert.notNull(entity, "ArticleLook不可为空！");
        entity.setUpdateTime(new Date());
        return bizArticleLookMapper.updateByPrimaryKey(entity.getBizArticleLook()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(ArticleLook entity) {
        Assert.notNull(entity, "ArticleLook不可为空！");
        entity.setUpdateTime(new Date());
        return bizArticleLookMapper.updateByPrimaryKeySelective(entity.getBizArticleLook()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public ArticleLook getByPrimaryKey(Integer primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizArticleLook entity = bizArticleLookMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new ArticleLook(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public ArticleLook getOneByEntity(ArticleLook entity) {
        Assert.notNull(entity, "ArticleLook不可为空！");
        BizArticleLook bo = bizArticleLookMapper.selectOne(entity.getBizArticleLook());
        return null == bo ? null : new ArticleLook(bo);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<ArticleLook> listAll() {
        List<BizArticleLook> entityList = bizArticleLookMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<ArticleLook> list = new ArrayList<>();
        for (BizArticleLook entity : entityList) {
            list.add(new ArticleLook(entity));
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
    public List<ArticleLook> listByEntity(ArticleLook entity) {
        Assert.notNull(entity, "ArticleLook不可为空！");
        List<BizArticleLook> entityList = bizArticleLookMapper.select(entity.getBizArticleLook());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<ArticleLook> list = new ArrayList<>();
        for (BizArticleLook po : entityList) {
            list.add(new ArticleLook(po));
        }
        return list;
    }
}
