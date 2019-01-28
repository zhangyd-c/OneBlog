package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Log;
import com.zyd.blog.business.service.SysLogService;
import com.zyd.blog.business.vo.LogConditionVO;
import com.zyd.blog.persistence.beans.SysLog;
import com.zyd.blog.persistence.mapper.SysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/01/09 17:50
 * @since 1.0
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public PageInfo<Log> findPageBreakByCondition(LogConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysLog> list = sysLogMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Log> boList = new ArrayList<>();
        for (SysLog sysLog : list) {
            boList.add(new Log(sysLog));
        }
        PageInfo bean = new PageInfo<SysLog>(list);
        bean.setList(boList);
        return bean;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Log insert(Log entity) {
        Assert.notNull(entity, "Log不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysLogMapper.insertSelective(entity.getSysLog());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Integer primaryKey) {
        return sysLogMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Log entity) {
        Assert.notNull(entity, "Log不可为空！");
        entity.setUpdateTime(new Date());
        return sysLogMapper.updateByPrimaryKeySelective(entity.getSysLog()) > 0;
    }

    @Override
    public Log getByPrimaryKey(Integer primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysLog entity = sysLogMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Log(entity);
    }
}
