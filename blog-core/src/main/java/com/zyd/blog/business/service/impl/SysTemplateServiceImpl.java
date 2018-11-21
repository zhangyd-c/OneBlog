package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Template;
import com.zyd.blog.business.enums.TemplateKeyEnum;
import com.zyd.blog.business.service.SysTemplateService;
import com.zyd.blog.business.vo.TemplateConditionVO;
import com.zyd.blog.persistence.beans.SysTemplate;
import com.zyd.blog.persistence.mapper.SysTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统模板
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysTemplateServiceImpl implements SysTemplateService{

    @Autowired
    private SysTemplateMapper sysTemplateMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Template>findPageBreakByCondition(TemplateConditionVO vo){
        PageHelper.startPage(vo.getPageNumber(),vo.getPageSize());
        List<SysTemplate>list=sysTemplateMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Template> boList = new ArrayList<>();
        for(SysTemplate sysTemplate : list){
            boList.add(new Template(sysTemplate));
        }
        PageInfo bean = new PageInfo<SysTemplate>(list);
        bean.setList(boList);
        return bean;
    }

    /**
     * 通过key获取模板信息
     *
     * @param key
     * @return
     */
    @Override
    public Template getTemplate(TemplateKeyEnum key) {
        Template entity = new Template();
        entity.setRefKey(key.toString());
        return this.getOneByEntity(entity);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Template insert(Template entity){
        Assert.notNull(entity, "Template不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysTemplateMapper.insertSelective(entity.getSysTemplate());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<Template> entities){
        Assert.notNull(entities, "Templates不可为空！");
        List<SysTemplate> list = new ArrayList<>();
        for (Template entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            list.add(entity.getSysTemplate());
        }
        sysTemplateMapper.insertList(list);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey){
        return sysTemplateMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Template entity){
        Assert.notNull(entity, "Template不可为空！");
        entity.setUpdateTime(new Date());
        return sysTemplateMapper.updateByPrimaryKey(entity.getSysTemplate()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Template entity){
        Assert.notNull(entity, "Template不可为空！");
        entity.setUpdateTime(new Date());
        return sysTemplateMapper.updateByPrimaryKeySelective(entity.getSysTemplate()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public Template getByPrimaryKey(Long primaryKey){
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysTemplate entity = sysTemplateMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Template(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public Template getOneByEntity(Template entity){
        Assert.notNull(entity, "Template不可为空！");
        SysTemplate bo = sysTemplateMapper.selectOne(entity.getSysTemplate());
        return null == bo ? null : new Template(bo);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<Template> listAll(){
        List<SysTemplate> entityList = sysTemplateMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Template> list = new ArrayList<>();
        for (SysTemplate entity : entityList) {
            list.add(new Template(entity));
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
    public List<Template> listByEntity(Template entity){
        Assert.notNull(entity, "Template不可为空！");
        List<SysTemplate> entityList = sysTemplateMapper.select(entity.getSysTemplate());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Template> list = new ArrayList<>();
        for (SysTemplate po : entityList) {
            list.add(new Template(po));
        }
        return list;
    }
}
