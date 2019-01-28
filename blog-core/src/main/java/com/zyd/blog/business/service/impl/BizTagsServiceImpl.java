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
public class BizTagsServiceImpl implements BizTagsService {

    @Autowired
    private BizTagsMapper bizTagsMapper;

    @Override
    public PageInfo<Tags> findPageBreakByCondition(TagsConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizTags> list = bizTagsMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Tags> boList = new ArrayList<>();
        for (BizTags bizTags : list) {
            boList.add(new Tags(bizTags));
        }
        PageInfo bean = new PageInfo<BizTags>(list);
        bean.setList(boList);
        return bean;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public Tags insert(Tags entity) {
        Assert.notNull(entity, "Tags不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizTagsMapper.insertSelective(entity.getBizTags());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return bizTagsMapper.deleteByPrimaryKey(primaryKey) > 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @RedisCache(flush = true)
    public boolean updateSelective(Tags entity) {
        Assert.notNull(entity, "Tags不可为空！");
        entity.setUpdateTime(new Date());
        return bizTagsMapper.updateByPrimaryKeySelective(entity.getBizTags()) > 0;
    }

    @Override
    public Tags getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizTags entity = bizTagsMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Tags(entity);
    }

    @Override
    @RedisCache
    public List<Tags> listAll() {
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
}
