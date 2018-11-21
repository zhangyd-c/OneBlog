package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.UpdateRecorde;
import com.zyd.blog.business.service.SysUpdateRecordeService;
import com.zyd.blog.business.vo.UpdateRecordeConditionVO;
import com.zyd.blog.persistence.beans.SysUpdateRecorde;
import com.zyd.blog.persistence.mapper.SysUpdateRecordeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 更新记录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysUpdateRecordeServiceImpl implements SysUpdateRecordeService{

    @Autowired
    private SysUpdateRecordeMapper sysUpdateRecordeMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<UpdateRecorde>findPageBreakByCondition(UpdateRecordeConditionVO vo){
        PageHelper.startPage(vo.getPageNumber(),vo.getPageSize());
        List<SysUpdateRecorde>list=sysUpdateRecordeMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<UpdateRecorde> boList = new ArrayList<>();
        for(SysUpdateRecorde sysUpdateRecorde : list){
            boList.add(new UpdateRecorde(sysUpdateRecorde));
        }
        PageInfo bean = new PageInfo<SysUpdateRecorde>(list);
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
    public UpdateRecorde insert(UpdateRecorde entity){
        Assert.notNull(entity, "UpdateRecorde不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysUpdateRecordeMapper.insertSelective(entity.getSysUpdateRecorde());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<UpdateRecorde> entities){
        Assert.notNull(entities, "UpdateRecordes不可为空！");
        List<SysUpdateRecorde> list = new ArrayList<>();
        for (UpdateRecorde entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            list.add(entity.getSysUpdateRecorde());
        }
        sysUpdateRecordeMapper.insertList(list);
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
        return sysUpdateRecordeMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UpdateRecorde entity){
        Assert.notNull(entity, "UpdateRecorde不可为空！");
        entity.setUpdateTime(new Date());
        return sysUpdateRecordeMapper.updateByPrimaryKey(entity.getSysUpdateRecorde()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(UpdateRecorde entity){
        Assert.notNull(entity, "UpdateRecorde不可为空！");
        entity.setUpdateTime(new Date());
        return sysUpdateRecordeMapper.updateByPrimaryKeySelective(entity.getSysUpdateRecorde()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public UpdateRecorde getByPrimaryKey(Long primaryKey){
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysUpdateRecorde entity = sysUpdateRecordeMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new UpdateRecorde(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public UpdateRecorde getOneByEntity(UpdateRecorde entity){
        Assert.notNull(entity, "UpdateRecorde不可为空！");
        SysUpdateRecorde bo = sysUpdateRecordeMapper.selectOne(entity.getSysUpdateRecorde());
        return null == bo ? null : new UpdateRecorde(bo);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<UpdateRecorde> listAll(){
        List<SysUpdateRecorde> entityList = sysUpdateRecordeMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<UpdateRecorde> list = new ArrayList<>();
        for (SysUpdateRecorde entity : entityList) {
            list.add(new UpdateRecorde(entity));
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
    public List<UpdateRecorde> listByEntity(UpdateRecorde entity){
        Assert.notNull(entity, "UpdateRecorde不可为空！");
        List<SysUpdateRecorde> entityList = sysUpdateRecordeMapper.select(entity.getSysUpdateRecorde());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<UpdateRecorde> list = new ArrayList<>();
        for (SysUpdateRecorde po : entityList) {
            list.add(new UpdateRecorde(po));
        }
        return list;
    }
}
