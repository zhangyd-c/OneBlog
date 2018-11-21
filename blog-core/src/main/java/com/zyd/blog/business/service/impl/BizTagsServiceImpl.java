package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.business.entity.Tags;
import com.zyd.blog.business.service.BizTagsService;
import com.zyd.blog.business.vo.TagsConditionVO;
import com.zyd.blog.persistence.beans.BizTags;
import com.zyd.blog.persistence.mapper.BizTagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 标签
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizTagsServiceImpl implements BizTagsService{

    @Autowired
    private BizTagsMapper bizTagsMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Tags>findPageBreakByCondition(TagsConditionVO vo){
        PageHelper.startPage(vo.getPageNumber(),vo.getPageSize());
        List<BizTags>list=bizTagsMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Tags> boList = new ArrayList<>();
        for(BizTags bizTags : list){
            boList.add(new Tags(bizTags));
        }
        PageInfo bean = new PageInfo<BizTags>(list);
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
    @RedisCache(flush = true)
    public Tags insert(Tags entity){
        Assert.notNull(entity, "Tags不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizTagsMapper.insertSelective(entity.getBizTags());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    @RedisCache(flush = true)
    public void insertList(List<Tags> entities){
        Assert.notNull(entities, "Tagss不可为空！");
        List<BizTags> list = new ArrayList<>();
        for (Tags entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            list.add(entity.getBizTags());
        }
        bizTagsMapper.insertList(list);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean removeByPrimaryKey(Long primaryKey){
        return bizTagsMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean update(Tags entity){
        Assert.notNull(entity, "Tags不可为空！");
        entity.setUpdateTime(new Date());
        return bizTagsMapper.updateByPrimaryKey(entity.getBizTags()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean updateSelective(Tags entity){
        Assert.notNull(entity, "Tags不可为空！");
        entity.setUpdateTime(new Date());
        return bizTagsMapper.updateByPrimaryKeySelective(entity.getBizTags()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public Tags getByPrimaryKey(Long primaryKey){
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizTags entity = bizTagsMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Tags(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public Tags getOneByEntity(Tags entity){
        Assert.notNull(entity, "Tags不可为空！");
        BizTags bo = bizTagsMapper.selectOne(entity.getBizTags());
        return null == bo ? null : new Tags(bo);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    @RedisCache
    public List<Tags> listAll(){
        List<BizTags> entityList = bizTagsMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Tags> list = new ArrayList<>();
        for (BizTags entity : entityList) {
            list.add(new Tags(entity));
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
    @RedisCache
    public List<Tags> listByEntity(Tags entity){
        Assert.notNull(entity, "Tags不可为空！");
        List<BizTags> entityList = bizTagsMapper.select(entity.getBizTags());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Tags> list = new ArrayList<>();
        for (BizTags po : entityList) {
            list.add(new Tags(po));
        }
        return list;
    }
}
