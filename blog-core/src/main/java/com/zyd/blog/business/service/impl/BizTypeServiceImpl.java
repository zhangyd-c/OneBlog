package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Type;
import com.zyd.blog.business.service.BizTypeService;
import com.zyd.blog.business.vo.TypeConditionVO;
import com.zyd.blog.framework.exception.ZhydException;
import com.zyd.blog.persistence.beans.BizArticle;
import com.zyd.blog.persistence.beans.BizType;
import com.zyd.blog.persistence.mapper.BizArticleMapper;
import com.zyd.blog.persistence.mapper.BizTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizTypeServiceImpl implements BizTypeService {

    @Autowired
    private BizTypeMapper bizTypeMapper;
    @Autowired
    private BizArticleMapper bizArticleMapper;

    @Override
    public PageInfo<Type> findPageBreakByCondition(TypeConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizType> list = bizTypeMapper.findPageBreakByCondition(vo);
        List<Type> boList = getTypes(list);
        if (boList == null) return null;
        PageInfo bean = new PageInfo<BizType>(list);
        bean.setList(boList);
        return bean;
    }

    @Override
    public List<Type> listParent() {
        List<BizType> list = bizTypeMapper.listParent();
        return getTypes(list);
    }

    @Override
    public List<Type> listTypeForMenu() {
        TypeConditionVO vo = new TypeConditionVO();
        vo.setPageNumber(1);
        vo.setPageSize(100);
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizType> entityList = bizTypeMapper.listTypeForMenu();
        return getTypes(entityList);
    }

    @Override
    public List<Type> listTypeByPosition(String position) {
        Example example = new Example(BizType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("position", StringUtils.isEmpty(position) ? "scrollmenu" : position)
                .andEqualTo("available", true);
        example.setOrderByClause("sort ASC");
        List<BizType> entityList = bizTypeMapper.selectByExample(example);
        return getTypes(entityList);
    }

    private List<Type> getTypes(List<BizType> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Type> boList = new ArrayList<>();
        for (BizType bizType : list) {
            boList.add(new Type(bizType));
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Type insert(Type entity) {
        Assert.notNull(entity, "Type不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizTypeMapper.insertSelective(entity.getBizType());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {

        BizArticle article = new BizArticle();
        article.setTypeId(primaryKey);
        List<BizArticle> articles = bizArticleMapper.select(article);
        if (!CollectionUtils.isEmpty(articles)) {
            throw new ZhydException("当前分类下存在文章信息，禁止删除！");
        }
        return bizTypeMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Type entity) {
        Assert.notNull(entity, "Type不可为空！");
        entity.setUpdateTime(new Date());
        return bizTypeMapper.updateByPrimaryKey(entity.getBizType()) > 0;
    }

    @Override
    public Type getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizType entity = bizTypeMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Type(entity);
    }

    @Override
    public List<Type> listAll() {
        TypeConditionVO vo = new TypeConditionVO();
        vo.setPageNumber(1);
        vo.setPageSize(100);
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizType> entityList = bizTypeMapper.selectAll();

        return getTypes(entityList);
    }
}
