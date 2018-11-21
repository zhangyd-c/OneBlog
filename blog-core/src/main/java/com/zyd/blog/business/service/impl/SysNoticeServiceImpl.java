package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.dto.SysNoticeDTO;
import com.zyd.blog.business.entity.Notice;
import com.zyd.blog.business.enums.NoticeStatusEnum;
import com.zyd.blog.business.service.SysNoticeService;
import com.zyd.blog.business.vo.NoticeConditionVO;
import com.zyd.blog.persistence.beans.SysNotice;
import com.zyd.blog.persistence.mapper.SysNoticeMapper;
import com.zyd.blog.util.BeanConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统通知
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    @Autowired
    private SysNoticeMapper sysNoticeMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Notice> findPageBreakByCondition(NoticeConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysNotice> list = sysNoticeMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Notice> boList = new ArrayList<>();
        for (SysNotice sysNotice : list) {
            boList.add(new Notice(sysNotice));
        }
        PageInfo bean = new PageInfo<SysNotice>(list);
        bean.setList(boList);
        return bean;
    }

    /**
     * 获取已发布的通知列表
     *
     * @return
     */
    @Override
    public List<SysNoticeDTO> listRelease() {
        NoticeConditionVO vo = new NoticeConditionVO();
        vo.setStatus(NoticeStatusEnum.RELEASE.toString());
        List<SysNotice> list = sysNoticeMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<SysNoticeDTO> boList = new ArrayList<>();
        for (SysNotice sysNotice : list) {
            boList.add(BeanConvertUtil.doConvert(sysNotice, SysNoticeDTO.class));
        }
        return boList;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Notice insert(Notice entity) {
        Assert.notNull(entity, "Notice不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysNoticeMapper.insertSelective(entity.getSysNotice());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<Notice> entities) {
        Assert.notNull(entities, "Notices不可为空！");
        List<SysNotice> list = new ArrayList<>();
        for (Notice entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            list.add(entity.getSysNotice());
        }
        sysNoticeMapper.insertList(list);
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
        return sysNoticeMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Notice entity) {
        Assert.notNull(entity, "Notice不可为空！");
        entity.setUpdateTime(new Date());
        return sysNoticeMapper.updateByPrimaryKey(entity.getSysNotice()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Notice entity) {
        Assert.notNull(entity, "Notice不可为空！");
        entity.setUpdateTime(new Date());
        return sysNoticeMapper.updateByPrimaryKeySelective(entity.getSysNotice()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public Notice getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysNotice entity = sysNoticeMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Notice(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public Notice getOneByEntity(Notice entity) {
        Assert.notNull(entity, "Notice不可为空！");
        SysNotice bo = sysNoticeMapper.selectOne(entity.getSysNotice());
        return null == bo ? null : new Notice(bo);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<Notice> listAll() {
        List<SysNotice> entityList = sysNoticeMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Notice> list = new ArrayList<>();
        for (SysNotice entity : entityList) {
            list.add(new Notice(entity));
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
    public List<Notice> listByEntity(Notice entity) {
        Assert.notNull(entity, "Notice不可为空！");
        List<SysNotice> entityList = sysNoticeMapper.select(entity.getSysNotice());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Notice> list = new ArrayList<>();
        for (SysNotice po : entityList) {
            list.add(new Notice(po));
        }
        return list;
    }
}
