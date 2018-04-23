/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Type;
import com.zyd.blog.business.service.BizTypeService;
import com.zyd.blog.business.vo.TypeConditionVO;
import com.zyd.blog.persistence.beans.BizType;
import com.zyd.blog.persistence.mapper.BizTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 分类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizTypeServiceImpl implements BizTypeService {

    @Autowired
    private BizTypeMapper bizTypeMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Type> findPageBreakByCondition(TypeConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizType> list = bizTypeMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Type> boList = new ArrayList<>();
        for (BizType bizType : list) {
            boList.add(new Type(bizType));
        }
        PageInfo bean = new PageInfo<BizType>(list);
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
    public Type insert(Type entity) {
        Assert.notNull(entity, "Type不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizTypeMapper.insertSelective(entity.getBizType());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<Type> entities) {
        Assert.notNull(entities, "Types不可为空！");
        List<BizType> list = new ArrayList<>();
        for (Type entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            list.add(entity.getBizType());
        }
        bizTypeMapper.insertList(list);
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
        return bizTypeMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Type entity) {
        Assert.notNull(entity, "Type不可为空！");
        entity.setUpdateTime(new Date());
        return bizTypeMapper.updateByPrimaryKey(entity.getBizType()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Type entity) {
        Assert.notNull(entity, "Type不可为空！");
        entity.setUpdateTime(new Date());
        return bizTypeMapper.updateByPrimaryKeySelective(entity.getBizType()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public Type getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizType entity = bizTypeMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Type(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public Type getOneByEntity(Type entity) {
        Assert.notNull(entity, "Type不可为空！");
        BizType bo = bizTypeMapper.selectOne(entity.getBizType());
        return null == bo ? null : new Type(bo);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<Type> listAll() {
        TypeConditionVO vo = new TypeConditionVO();
        vo.setPageNumber(1);
        vo.setPageSize(100);
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizType> entityList = bizTypeMapper.findPageBreakByCondition(null);

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Type> list = new ArrayList<>();
        for (BizType entity : entityList) {
            list.add(new Type(entity));
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
    public List<Type> listByEntity(Type entity) {
        Assert.notNull(entity, "Type不可为空！");
        List<BizType> entityList = bizTypeMapper.select(entity.getBizType());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Type> list = new ArrayList<>();
        for (BizType po : entityList) {
            list.add(new Type(po));
        }
        return list;
    }
}
