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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统模板
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysTemplateServiceImpl implements SysTemplateService {

    @Autowired
    private SysTemplateMapper sysTemplateMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Template> findPageBreakByCondition(TemplateConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysTemplate> list = sysTemplateMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Template> boList = new ArrayList<>();
        for (SysTemplate sysTemplate : list) {
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
        return getTemplate(key.toString());
    }

    @Override
    public Template getTemplate(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        SysTemplate entity = new SysTemplate();
        entity.setRefKey(key);
        entity = this.sysTemplateMapper.selectOne(entity);
        return null == entity ? null : new Template(entity);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Template insert(Template entity) {
        Assert.notNull(entity, "Template不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysTemplateMapper.insertSelective(entity.getSysTemplate());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysTemplateMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Template entity) {
        Assert.notNull(entity, "Template不可为空！");
        entity.setUpdateTime(new Date());
        return sysTemplateMapper.updateByPrimaryKeySelective(entity.getSysTemplate()) > 0;
    }

    @Override
    public Template getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysTemplate entity = sysTemplateMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Template(entity);
    }

    @Override
    public List<Template> listAll() {
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
}
