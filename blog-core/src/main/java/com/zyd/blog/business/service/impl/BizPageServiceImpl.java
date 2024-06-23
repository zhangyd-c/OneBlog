/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2021
 */
package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Page;
import com.zyd.blog.business.service.BizPageService;
import com.zyd.blog.business.vo.PageConditionVO;
import com.zyd.blog.persistence.beans.BizPage;
import com.zyd.blog.persistence.mapper.BizPageMapper;
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
 * @author yadong.zhang email:yadong.zhang(a)innodev.com.cn
 * @version 1.0
 * @date 2021/10/23 00:19
 * @since 1.0
 */
@Service
public class BizPageServiceImpl implements BizPageService {

    @Autowired
    private BizPageMapper bizPageMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Page> findPageBreakByCondition(PageConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizPage> list = bizPageMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Page> boList = new ArrayList<>();
        for (BizPage bizPage : list) {
            boList.add(new Page(bizPage));
        }
        PageInfo bean = new PageInfo<BizPage>(list);
        bean.setList(boList);
        return bean;
    }

    @Override
    public Page getByUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        Example example = new Example(BizPage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("url", url);
        List<BizPage> list = bizPageMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : new Page(list.get(0));
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page insert(Page entity) {
        Assert.notNull(entity, "Page不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizPageMapper.insertSelective(entity.getBizPage());
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
        return bizPageMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Page entity) {
        Assert.notNull(entity, "Page不可为空！");
        entity.setUpdateTime(new Date());
        return bizPageMapper.updateByPrimaryKeySelective(entity.getBizPage()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public Page getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizPage entity = bizPageMapper.selectByPrimaryKey(primaryKey);
        return null == entity ? null : new Page(entity);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
    @Override
    public List<Page> listAll() {
        List<BizPage> entityList = bizPageMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Page> list = new ArrayList<>();
        for (BizPage entity : entityList) {
            list.add(new Page(entity));
        }
        return list;
    }
}
