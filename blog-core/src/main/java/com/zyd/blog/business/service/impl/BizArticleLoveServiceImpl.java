package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.ArticleLove;
import com.zyd.blog.business.service.BizArticleLoveService;
import com.zyd.blog.business.vo.ArticleLoveConditionVO;
import com.zyd.blog.persistence.beans.BizArticleLove;
import com.zyd.blog.persistence.mapper.BizArticleLoveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章点赞
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizArticleLoveServiceImpl implements BizArticleLoveService{

    @Autowired
    private BizArticleLoveMapper bizArticleLoveMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<ArticleLove>findPageBreakByCondition(ArticleLoveConditionVO vo){
        PageHelper.startPage(vo.getPageNumber(),vo.getPageSize());
        List<BizArticleLove>list=bizArticleLoveMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<ArticleLove> boList = new ArrayList<>();
        for(BizArticleLove bizArticleLove : list){
            boList.add(new ArticleLove(bizArticleLove));
        }
        PageInfo bean = new PageInfo<BizArticleLove>(list);
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
    public ArticleLove insert(ArticleLove entity){
        Assert.notNull(entity, "ArticleLove不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizArticleLoveMapper.insertSelective(entity.getBizArticleLove());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<ArticleLove> entities){
        Assert.notNull(entities, "ArticleLoves不可为空！");
        List<BizArticleLove> list = new ArrayList<>();
        for (ArticleLove entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            list.add(entity.getBizArticleLove());
        }
        bizArticleLoveMapper.insertList(list);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Integer primaryKey){
        return bizArticleLoveMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ArticleLove entity){
        Assert.notNull(entity, "ArticleLove不可为空！");
        entity.setUpdateTime(new Date());
        return bizArticleLoveMapper.updateByPrimaryKey(entity.getBizArticleLove()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(ArticleLove entity){
        Assert.notNull(entity, "ArticleLove不可为空！");
        entity.setUpdateTime(new Date());
        return bizArticleLoveMapper.updateByPrimaryKeySelective(entity.getBizArticleLove()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public ArticleLove getByPrimaryKey(Integer primaryKey){
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizArticleLove entity = bizArticleLoveMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new ArticleLove(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public ArticleLove getOneByEntity(ArticleLove entity){
        Assert.notNull(entity, "ArticleLove不可为空！");
        BizArticleLove bo = bizArticleLoveMapper.selectOne(entity.getBizArticleLove());
        return null == bo ? null : new ArticleLove(bo);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<ArticleLove> listAll(){
        List<BizArticleLove> entityList = bizArticleLoveMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<ArticleLove> list = new ArrayList<>();
        for (BizArticleLove entity : entityList) {
            list.add(new ArticleLove(entity));
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
    public List<ArticleLove> listByEntity(ArticleLove entity){
        Assert.notNull(entity, "ArticleLove不可为空！");
        List<BizArticleLove> entityList = bizArticleLoveMapper.select(entity.getBizArticleLove());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<ArticleLove> list = new ArrayList<>();
        for (BizArticleLove po : entityList) {
            list.add(new ArticleLove(po));
        }
        return list;
    }
}
