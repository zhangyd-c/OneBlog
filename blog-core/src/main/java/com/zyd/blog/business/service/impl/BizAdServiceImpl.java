package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.BizAdBo;
import com.zyd.blog.business.enums.AdPositionEnum;
import com.zyd.blog.business.service.BizAdService;
import com.zyd.blog.business.vo.BizAdConditionVO;
import com.zyd.blog.persistence.beans.BizAd;
import com.zyd.blog.persistence.mapper.BizAdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author generate by HouTu Generator
 * @version 1.0
 * @date 2021/10/27 16:45
 * @since 1.8
 */
@Service
public class BizAdServiceImpl implements BizAdService {

    @Autowired
    private BizAdMapper bizAdMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<BizAdBo> findPageBreakByCondition(BizAdConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizAd> list = bizAdMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<BizAdBo> boList = new ArrayList<>();
        for (BizAd bizAd : list) {
            boList.add(new BizAdBo(bizAd));
        }
        PageInfo bean = new PageInfo<BizAd>(list);
        bean.setList(boList);
        return bean;
    }

    @Override
    public BizAdBo getByPosition(AdPositionEnum positionEnum) {
        if (null == positionEnum) {
            return null;
        }
        BizAd ad = new BizAd();
        ad.setPosition(positionEnum.name());
        ad = this.bizAdMapper.selectOne(ad);
        return null == ad ? null : new BizAdBo(ad);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BizAdBo insert(BizAdBo entity) {
        Assert.notNull(entity, "BizAd不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizAdMapper.insertSelective(entity.getBizAd());
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
        return bizAdMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(BizAdBo entity) {
        Assert.notNull(entity, "BizAd不可为空！");
        entity.setUpdateTime(new Date());
        return bizAdMapper.updateByPrimaryKeySelective(entity.getBizAd()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public BizAdBo getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizAd entity = bizAdMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new BizAdBo(entity);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<BizAdBo> listAll() {
        List<BizAd> entityList = bizAdMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<BizAdBo> list = new ArrayList<>();
        for (BizAd entity : entityList) {
            list.add(new BizAdBo(entity));
        }
        return list;
    }
}
